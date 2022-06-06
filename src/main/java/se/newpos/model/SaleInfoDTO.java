package se.newpos.model;

import java.util.ArrayList;
/**
 * This is the data transfer object that holds the saleinformation that is used to display total price when all items have been registered.
 */
public final class SaleInfoDTO {
    private RunningTotal runningTotal;
    private ArrayList<Item> items;
    private double discount;
    /**
     * Creates one instance of saleinfoDTO.
     * @param currentsale contains all information of currentsale
     */
    public SaleInfoDTO(Sale currentsale){
        this.runningTotal = currentsale.getRunningTotal();
        this.items = currentsale.getItems();
        this.discount = currentsale.getDiscountAmount();
    }
    /**
     * getter for runningtotal
     * @return RunningTotal
     */
    public RunningTotal getRunningTotal() {
        return runningTotal;
    }
    /**
     * Prints the price to pay for all items to cashier.
     */
    public void printRunningTotal(){
        System.out.format("Totalprice to pay: %.2f%n", this.runningTotal.getTotalPrice());
        System.out.format("TotalVat: %.2f%n", this.runningTotal.getTotalVat());
        System.out.format("Total discount: %.2f%n", this.discount);
        System.out.println("Total items: " + this.runningTotal.getTotalNumberOfItems() + "\n");
    }
    /**
     * Getter for discount in sale
     * @return a double
     */
    public double getDiscount(){
        return discount;
    }
    
}
