package se.newpos.model;

import java.util.ArrayList;
import java.util.List;

import se.newpos.integration.DiscountInfo;
import se.newpos.integration.Printer;

/**
 * One instance of sale made by one customer with one payment.
 */
public class Sale {
    private SaleTimePeriod saleTimePeriod;
    private RunningTotal runningTotal;
    private List<Item> items;
    private DiscountInfo totalDiscounts;
    private Payment paid;
    /**
    * Creates a new instance of Sale and saves the runningtotal for easy display, and timeperiod.
    */
    public Sale(){
        saleTimePeriod = new SaleTimePeriod();
        runningTotal = new RunningTotal();
        items = new ArrayList<>();
        totalDiscounts = new DiscountInfo();
        paid = new Payment();
    }
    /**
     * Copies one instance of sale to another used when creating a transaction when the sale is complete.
     * @param sale
     */
    public Sale(Sale sale){
        this.saleTimePeriod = sale.saleTimePeriod;
        this.runningTotal = sale.runningTotal;
        this.items = sale.items;
        this.totalDiscounts = sale.totalDiscounts;
        this.paid = sale.paid;
    }
    /**
     * Add an item to sale.
     * @param enteredItemDTO The object/item that has been entered and found in inventorysystem.
     */
    public CurrentItemDTO addEnteredItem(ItemDTO enteredItemDTO){  
        Item currentItem = new Item(enteredItemDTO);      
        items.add(currentItem);
        updateRunningTotal(enteredItemDTO);
        return new CurrentItemDTO(currentItem, this.runningTotal);
    }
    /**
     * Increases the quantity of the specified item that already exist in sale.
     * @param enteredItemDTO The identified item.
     */
    public CurrentItemDTO addDuplicate(ItemDTO enteredItemDTO){
        for (Item item : items){
            if(item.getItemID() == enteredItemDTO.getItemID()){
                item.increaseQuantity(1);
                updateRunningTotal(enteredItemDTO);
                return new CurrentItemDTO(item, this.runningTotal);
            }
        }
        return null;
    }
    /**
     * Adds a choosen amount to existing sale of the last identified item.
     * @param currentItemDTO Holds information about the last identified item.
     * @param amount This is the amount that user enters.
     * @return the information about the last identified item and runningtotal.
     */
    public CurrentItemDTO addCurrentItem(CurrentItemDTO currentItemDTO, int amount){
        if(amount < 0){
            return currentItemDTO;
        }
        for(Item item : items){
            if(item.getItemID() == currentItemDTO.getCurrentItemID()){
                item.increaseQuantity(amount);
                updateRunningTotalWithNumberOfItems(new ItemDTO(item), amount);
                return new CurrentItemDTO(item, this.runningTotal);
            }
        }
        return null;
    }
    /**
     * Updates runningTotal in current sale with a choosen amount of items.
     * @param currentItem is the item to be added to runningtotal
     * @param amount From this price and vat are extracted from.
     */
    private void updateRunningTotalWithNumberOfItems(ItemDTO currentItem, int amount){
        this.runningTotal.addTotalNetPrice(currentItem.getPrice() * amount);
        this.runningTotal.addTotalVat(calculateVat(currentItem) * amount);
        this.runningTotal.addTotalPrice(calculateTotalPrice(currentItem) * amount);
        this.runningTotal.addNumberOfItems(amount);
    }
    /**
     * Updates runningTotal in current sale with one item.
     * @param currentItem From this price and vat are extracted from.
     */
    private void updateRunningTotal(ItemDTO currentItem){
        this.runningTotal.addTotalNetPrice(currentItem.getPrice());
        this.runningTotal.addTotalVat(calculateVat(currentItem));
        this.runningTotal.addTotalPrice(calculateTotalPrice(currentItem));
        this.runningTotal.addNumberOfItems(1);
    }
    /**
     * Method for calculating Vat for an item
     * @param currentItem wich price to be calculated
     * @return a bouble with the sum of vat for this item
     */
    private double calculateVat(ItemDTO currentItem){
        return (currentItem.getPrice() * currentItem.getVatRate());
    }
    /**
     * Method for calculating totalPrice with vat for an item
     * @param currentItem wich price to be calculated
     * @return a double with totalprice for an item
     */
    private double calculateTotalPrice(ItemDTO currentItem){
        return currentItem.getPrice() * (1 + currentItem.getVatRate());
    }
    /**
     * Checks if entereditem already exist in the currentsale.
     * @param enteredItemDTO The identified item.
     * @return
     */
    public boolean inSale(ItemDTO enteredItemDTO){
        for (Item item : items){
            if (item.getItemID() == enteredItemDTO.getItemID()){
                return true;
            }
        }
        return false;
    }
    /**
     * getter for saletimeperiod
     * @return SaleTimePeriod return the saleTimePeriod
     */
    public SaleTimePeriod getSaleTimePeriod() {
        return saleTimePeriod;
    }
    /**Getter for runningtotal
     * @return RunningTotal return the runningTotal
     */
    public RunningTotal getRunningTotal() {
        return runningTotal;
    }
    
    /**
     * Getter for all items in sale
     * @return List<Item> return the items
     */
    public ArrayList<Item> getItems() {
        ArrayList<Item> cloneItems = new ArrayList<>();
        for(Item item : this.items){
            cloneItems.add(new Item(item));
        }
        return cloneItems;
    }
    /**
     * this is called from controller to add discount to sale and updates discount and runningtotal sums.
     * Adding all eligeble discount in a list in totalDiscount
     * @param discount This is all of the eligeble discount for the specified customer.
     */
    public void addDiscountForItemsInSale(DiscountInfo discount){
        int j;
        for(int i = 0; i < items.size(); i++){
            j = 0;
            for(j = 0; j < discount.getDiscount().size(); j++){
                Discount discountToAdd = discount.getDiscount().get(j);
                Item itemToCompare = discountToAdd.getItemsWithDiscount();
                if(items.get(i).getName().equals(itemToCompare.getName())){
                    setQuantityOfItem(items.get(i), itemToCompare);
                    this.totalDiscounts.addDiscountToList(new Discount(discountToAdd));
                }
            }
        }
        updateDiscountAmount();
        updateRunningTotalWithDiscount(this.totalDiscounts);
    }
    /**
     * Updates the discount sum that was eligeble to sale.
     */
    private void updateDiscountAmount(){
        for(Discount discount : this.totalDiscounts.getDiscount()){
            double discountRate = discount.getDiscountRate();
            double price = discount.getItemsWithDiscount().getPrice();
            int quantity = discount.getItemsWithDiscount().getQuantity();
            this.totalDiscounts.addTotalDiscount(discountRate * price * quantity);
        }
    }
    
    private void setQuantityOfItem(Item item, Item eligebleItem){
        eligebleItem.setQuantity(item.getQuantity());
    }
    /**
     * Gets the discount sum of the sale.
     * @return is a double that represents the discount.
     */
    public double getDiscountAmount(){
        return totalDiscounts.getTotalDiscount();
    }
    private void updateRunningTotalWithDiscount(DiscountInfo discountInfo){
        double vatRate = this.runningTotal.getTotalVat()/ this.runningTotal.getTotalNetPrice();
        double totalNetPrice = this.runningTotal.getTotalNetPrice() - discountInfo.getTotalDiscount();
        double vat = totalNetPrice * vatRate;
        double totalPrice = totalNetPrice + vat;
        this.runningTotal.setTotalNetPrice(totalNetPrice);
        this.runningTotal.setTotalPrice(totalPrice);
        this.runningTotal.setTotalVat(vat);
    }
    /**
     * Updates and calculates Payment.
     * @param paid Is the amount entered by cashier.
     */
    public void enteredPayment(double amount){
        this.paid.addAmount(amount);
        this.paid.calculateChange(this);
    }
    /**
     * Getter method for discountinformation.
     * @return DiscountInfo from this sale
     */
    public DiscountInfo getDiscountInfo(){
        return totalDiscounts;
    }
    /**
     * Method to create an transactionDTO and print the recipt of this sale.
     * @param printer the printer where the reciept will print on. 
     * @param cashRegister Current Register opererated on.
     * @return a TransactionDTO
     */
    public TransactionDTO printReciept(Printer printer, CashRegister cashRegister){
        TransactionDTO transactionDTO;
        transactionDTO = new TransactionDTO(new Transaction(this, this.paid , cashRegister ));
        Reciept reciept = new Reciept(transactionDTO);
        printer.printReciept(reciept);
        return transactionDTO;
    }
    /**
     * Getter method for Payment
     * @return payment for this sale
     */
    public Payment gPayment(){
        return paid;
    }

}


