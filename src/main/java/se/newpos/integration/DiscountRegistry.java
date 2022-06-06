package se.newpos.integration;
/**
 * This is the discount registry where customers eligeble for discount are stored and have all calls for 
 * dicsounted items and eligeble customers. All discounts are hardcoded
 */

import java.util.ArrayList;
import java.util.List;

import se.newpos.model.Discount;
import se.newpos.model.Item;
import se.newpos.model.ItemDTO;
import se.newpos.model.TransactionDTO;
/**
 * Contains all calls to make an discountRegistry
 */
public class DiscountRegistry {
    private List<Discount> discounts = new ArrayList<>();
    /**
     * Creates a DiscountRegistry
     */
    DiscountRegistry(){
        this.discounts = new ArrayList<>();
    }
    /**
     * Adds an Item with discount in array. used for hardcoded items that have discount
     */
    public void addDiscount(ItemDTO item, double discountRate){
        this.discounts.add(new Discount(new Item(item), discountRate));
    }
    /**
     * Checks to se if Customer has an eligeble discount.
     * @param socialNumber The entered socialnumber from cashier.
     * @return
     */
    public boolean hasDiscount(String socialNumber){
        for(Discount discount : discounts){
            if(discount.searchSocialNumber(socialNumber)){
                return true;
            }
        }
        return false;
    }

    /**
     * Collects all valid discounts for this customer that matches.
     * @param socialNumber This is used for verifie that customer has valid discount.
     * @return
     */   
    public DiscountInfo convertToInfo(String socialNumber){
        DiscountInfo eligebleDiscounts = new DiscountInfo(socialNumber);
        for(Discount discount : discounts){
            if(discount.searchSocialNumber(socialNumber)){
                eligebleDiscounts.addDiscountToList(new Discount(discount));
            }
        }
        return eligebleDiscounts;
    }
    /**
     * Updates and removes all used discount.
     * @param transactionDTO Contains all informatioin about the last sale.
     */
    public void updateCustomerDiscount(TransactionDTO transactionDTO){
      // Add code here when a real DB is used.
    }
    public Discount getDiscount (int index){
        return discounts.get(index);
    }
}
