package se.newpos.controller;

import java.util.ArrayList;
import java.util.List;

import se.newpos.integration.*;
import se.newpos.model.*;


/**
 * This is the controller that communicates with all other layers.
 */
public class Controller {
    private final AccountSystem accountSystem;
    private InventorySystem inventorySystem;
    private final TransactionRegistry transactionRegistry;
    private DiscountRegistry discountRegistry;
    private Printer printer;
    private CashRegister cashRegister;
    private Sale sale;
    private ExternalCreator creator;
    private List<SaleObserver> saleObservers = new ArrayList<>();
    /**
     * Creates a new instance.
     * HardCoded discounts in the last methodcall.
     * @param creator this is used to get all classes that is externalsystems.
     * @param printer this is the printer that prints reciepts
     */
    public Controller(ExternalCreator creator, Printer printer){
        this.accountSystem = creator.getAccountSystem();
        this.inventorySystem = creator.getInventorySystem();
        this.transactionRegistry = creator.getTransactionRegistry();
        this.discountRegistry = creator.getDiscountRegistry();
        this.printer = printer;
        this.cashRegister = new CashRegister();
        this.creator = creator;
        addDiscountToRegistry();
    
    }
    /**
     * Hardcoded discountregistry for testing when discount is added to sale. 
     */
    private void addDiscountToRegistry(){
        ArrayList<ItemDTO> items = new ArrayList<>();
        items = inventorySystem.getItems();
        int i;
        int j;
        for(i = 0; i < items.size()/2; i++){
            discountRegistry.addDiscount(items.get(i), 0.10);
        }
        i++;
        for(j = items.size() - 1; j > i; j--){
            discountRegistry.addDiscount(items.get(j), 0.20);
        }
    }
    /**
     * Starts a new sale. This method must be the FIRST to call during a sale.
     */
    public void startNewSale(){
        this.sale = new Sale();        
    }
    /**
     * Adds the enteredItemDTO to sale if it exist in db othervise it does not do anything.
     * @param enteredItemDTO This is the entered item with a identifier ex scanning a bar code.
     * @return CurrentItemDTO the entered item exist in database then it will return this item to display this. View has to extract runningTotal from
     * controllers currentsale.
     * @throws ItemNotFoundException When scanned item not found in inventory system 
     * @throws ServerDownException When server is not connected.
     */
    public CurrentItemDTO enterItem(final ItemDTO enteredItemDTO) throws ItemNotFoundException, ServerDownException{
        if(sale.inSale(enteredItemDTO)){
            return sale.addDuplicate(enteredItemDTO);
        }
        try {
            ItemDTO foundItem = inventorySystem.findItemWithIDIdentifier(enteredItemDTO);
            return sale.addEnteredItem(foundItem);
        } catch (ItemNotFoundException | ServerDownException exception) {
            System.out.println("[For Programmer]: " + exception.getMessage());
            throw exception;
        }
    }
    /**
     * Adds a choosen number of the last identified item to sale. 
     * @param currentItemDTO Has the current item information. This must be a complete itemDTO.
     * @param amount User can enter a number on screen to add that many of the last identified item to sale.
     * @return Information for display.
     */
    public CurrentItemDTO addCurrentItemByAmount(final CurrentItemDTO currentItemDTO, final int amount){
        return sale.addCurrentItem(currentItemDTO, amount);
    }


    /**
     * Creates saleinformation to show to user.
     * 
     */
    public void endSale(){
        final SaleInfoDTO end = new SaleInfoDTO(this.sale);
        end.printRunningTotal();
    }
    /**
     * Adds discount if entered customer is eligeble, otherwise it prints out an errorMessage
     * @param socialNumber Entered socialnumber by the cashier.
     */
    public void addDiscount(final String socialNumber){
        DiscountInfo discount = new DiscountInfo(socialNumber);
        if(discountRegistry.hasDiscount(socialNumber)){
            discount = discountRegistry.convertToInfo(socialNumber);
            sale.addDiscountForItemsInSale(discount);
        }
        else{
            System.out.println("No discount found, try another socialnumber.");
        }
    }
    /**
     * This method is the payment secvence.
     * @param amount It is the entered amount recieved in cash for payment.
     */
    public String enterPayment(double amount){
        if(amount < sale.getRunningTotal().getTotalPrice()){
            return "Payment entered not enough to complete sale." + " Enter new sum.";
        }
        TransactionDTO transactionDTO;
        sale.enteredPayment(amount);
        sale.addSaleObserver(saleObservers);
        cashRegister.addPayment(sale.gPayment());
        transactionDTO = sale.printReciept(printer, cashRegister);
        
        creator.updateAllSystems(transactionDTO);
        return "Change: " + String.valueOf(transactionDTO.getPayment().getChange());
    }
    
    public void addSaleObserver(SaleObserver saleObserver){
        saleObservers.add(saleObserver);
    }
    /**
     * This method creates a SaleInfo to display total to pay when
     * cashier enters a button to end this sale.
     * @return information to display for the view
     */
    public SaleInfoDTO endSales(){
        return new SaleInfoDTO(this.sale);
    }
    /**
     * Getter for sale.
     * @return sale
     */
    public Sale getSale(){
        return sale;
    }

}
