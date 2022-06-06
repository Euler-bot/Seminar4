package se.newpos.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.newpos.integration.DiscountInfo;
/**
 * 
 */
public class Reciept {
    private final SaleTimePeriod saleTimePeriod;
    private final RunningTotal runningTotal;
    private List<Item> items = new ArrayList<>();
    private final DiscountInfo totalDiscounts;
    private final Payment payment;
    private final CashRegister currentRegister;
    private final int orderNumber;
    /**
     * Creates an instance of Reciept.
     */
    public Reciept(TransactionDTO transactionDTO){
        this.saleTimePeriod = transactionDTO.getCurrentSale().getSaleTimePeriod();
        this.runningTotal = transactionDTO.getCurrentSale().getRunningTotal();
        this.items = transactionDTO.getCurrentSale().getItems();
        this.totalDiscounts = transactionDTO.getCurrentSale().getDiscountInfo();
        this.payment = transactionDTO.getPayment();
        this.currentRegister = transactionDTO.getCurrentRegister();
        this.orderNumber = transactionDTO.getOrderNumber();
    }
    /**
     * 
     * @return
     */
    public String createRecieptString() {
        StringBuilder recieptBuilder = new StringBuilder();
        appendLine(recieptBuilder, "------------------RECEIPT-----------------");
        appendLine(recieptBuilder, "Date: " + currentRegister.getDate());
        appendLine(recieptBuilder, "POS: " + currentRegister.getRegisterNumber() + "\tOrdernr: " + this.orderNumber );
        appendLine(recieptBuilder, "Items: ");
        appendLine(recieptBuilder, itemToString());
        appendLine(recieptBuilder, "Total amount of items: " + runningTotal.getTotalNumberOfItems());
        appendLine(recieptBuilder, "Totalprice: " + String.format("%.2f", runningTotal.getTotalPrice()));
        appendLine(recieptBuilder, "Total vat: " + String.format("%.2f", runningTotal.getTotalVat()));
        appendLine(recieptBuilder, "Total discount: " + String.format("%.2f", totalDiscounts.getTotalDiscount()));
        appendLine(recieptBuilder, "Paid in cash: " + String.format("%.2f", payment.getAmount()));
        appendLine(recieptBuilder, "Change recieved: " + String.format("%.2f", payment.getChange()));
        appendLine(recieptBuilder, "\nSale starttime: " + saleTimePeriod.getStartSaleTime()  
                           + "\nSale completed: " + saleTimePeriod.getEndSaleTime());
        appendLine(recieptBuilder, "--------------------End-------------------");
                            return recieptBuilder.toString();
    }
    private void appendLine(StringBuilder recieptBuilder, String line){
        recieptBuilder.append(line);
        recieptBuilder.append("\n");
    }
    private String itemToString(){
        Iterator<Item> itemIterator = items.iterator(); 
        String lineItem;
        String lineItems = "";
        while(itemIterator.hasNext()){
            Item next = new Item(itemIterator.next());
            lineItem = String.format("%s\tprice: %.2f\tAmount: %d\n", 
                                    next.getName(), (next.getPrice() * next.getQuantity()), next.getQuantity());
            lineItems = lineItems.concat(lineItem);
        }
        return lineItems;
    }
}
