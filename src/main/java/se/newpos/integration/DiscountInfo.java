package se.newpos.integration;

import java.util.ArrayList;
import java.util.List;
import se.newpos.model.Discount;


/**
 * Represents a collection of valid discounts in a List during a sale.
 */
public class DiscountInfo {
    private String socialNumber;
    private List<Discount> discounts = new ArrayList<>();
    private double totalDiscount;
    
    public DiscountInfo(){}

    public DiscountInfo(String socialNumber){
        this.socialNumber = socialNumber;
    }
    /**
     * add discount in a list with all other discounts.
     * @param discount
     */
    public void addDiscountToList(Discount discount){
        this.discounts.add(discount);
    }

    public List<Discount> getDiscount() {
        List<Discount> cloneDiscount = new ArrayList<>();
        for(Discount discount : discounts){
            cloneDiscount.add(new Discount(discount));
        }
        return cloneDiscount;
    }
    /**
     * Increases discountsum with recieved discount amount.
     * @param discount contains what should be added to totalDiscount
     */
    public void addTotalDiscount(double discount){
        this.totalDiscount += discount;
    }
    public double getTotalDiscount(){
        return totalDiscount;
    }
 
}

