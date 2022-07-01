package se.newpos.model;

import java.util.ArrayList;
import java.util.List;

import se.newpos.integration.Customer;

/**
 * This is used to see which customer that have an discount on which items. Hardcoded for this seminar.
 */
public class Discount {
    private Item itemsWithDiscount;
    private double discountRate;
    private List<Customer> customers = new ArrayList<>(); 
    /**
     * Creates an instance of discount with item valid for discount and at which rate.
     * @param itemEligebleForDiscount item to be eligeble for discount 
     * @param discountRate the rate of discount for this item.
     */
    public Discount(Item itemEligebleForDiscount, double discountRate){
        this.itemsWithDiscount = itemEligebleForDiscount;
        this.discountRate = discountRate;
        addCustomers(discountRate);
    }
    /**
     * Copies an instance to another.
     * @param discount to be copied to another instance.
     */
    public Discount(Discount discount){
        this.itemsWithDiscount = discount.itemsWithDiscount;
        this.discountRate = discount.discountRate;
        this.customers = discount.customers;
    }
    /**
     * Hardcoded Customers for discount
     * @param discountRate This is the percent that the item is eligeble for discount.
     */
    private void addCustomers(double discountRate){
        if(discountRate == 0.10){
            customers.add(new Customer("199610180192", "Robert Tomson"));
            customers.add(new Customer("198510091022", "Anders Andersson"));
            customers.add(new Customer("200101251506", "Johan Person"));
            customers.add(new Customer("199812250989", "Anna Johansson"));
        }
        if(discountRate == 0.20){
            customers.add(new Customer("199610180192", "Robert Tomson"));
            customers.add(new Customer("198805311229", "Alma Pettersson"));
            customers.add(new Customer("199901251906", "Mike Litoris"));
            customers.add(new Customer("199812250989", "Anna Johansson"));
        }
        
    }
    /**
     * Used for searching if a customer has a discount
     * @param socialNumber to be searched after.
     * @return
     */
    public boolean searchSocialNumber(String socialNumber){
        for(Customer customer : customers){
            if(customer.getSocialNumber().equals(socialNumber)){
                return true;
            }
        }
        return false;
    }
    /**
     * Getter for which item that have discount.
     * @return a item with discount
     */
    public Item getItemsWithDiscount() {
        return new Item(itemsWithDiscount);
    }
    /**
     * Getter for discountrate
     * @return discountrate
     */
    public double getDiscountRate() {
        return discountRate;
    }


}
