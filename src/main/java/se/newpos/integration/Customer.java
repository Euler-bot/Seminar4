package se.newpos.integration;
/**
 * This is a club member of the store.
 */
public class Customer {
    private String name;
    private String socialNumber;
    /**
     * Creates a customer as a club member
     * @param socialNumber of the customer
     * @param name should contain first name and surname.
     */
    public Customer(String socialNumber, String name){
        this.socialNumber = socialNumber;
        this.name = name;
    }
    /**
     * Getter for socialnumber
     * @return
     */
    public String getSocialNumber(){
        return socialNumber;
    }
    /**
     * Getter for name
     * @return
     */
    public String getName(){
        return name;
    }
}
