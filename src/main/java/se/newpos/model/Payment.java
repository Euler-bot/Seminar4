package se.newpos.model;
/**
 * Contains the payment for a sale
 */
public class Payment {
    private double amount;
    private double change;
    /**
     * creates an instance of payment
     */
    public Payment(){
        this.amount = 0;
        this.change = 0;
    }
    /**
     * another contructor but here you chose your amount directly
     * @param amount to be added in payment
     */
    public Payment(double amount){
        if(amount <= 0){
            this.amount = 0;
        }
        this.amount = amount;
        this.change = 0;
    }
    /**
     * Calculates how much change there is considering to the sale and payment
     * @param currentSale contains totalprice of this sale
     */
    public void calculateChange(Sale currentSale){
        if(getTotal(currentSale) < this.amount){
            setChange(this.amount - getTotal(currentSale));
        }
    }
    /**
     * getter for totalprice in sale
     * @param currentSale contains totalprice of sale
     * @return double with totalprice
     */
    private double getTotal(Sale currentSale){
        return currentSale.getRunningTotal().getTotalPrice();
    }
    /**
     * getter for payment amount
     * @return double with amount
     */
    public double getAmount() {
        return amount;
    }
    /**
     * increases amount in payment with a double
     * @param amount how much to increase with
     */
    public void addAmount(double amount){
        if(amount > 0){
            this.amount += amount;
        }
    }
    /**
     * Getter for change
     * @return double with change
     */
    public double getChange() {
        return change;
    }
    /**
     * setter for change
     * @param change how much to set change witrh
     */
    public void setChange(double change) {
        this.change = change;
    }
    /**
     * getter for calculating the actual amount paid regarding to recieved change
     * @return a double
     */
    public double getTotal(){
        return (amount - change);
    }
}
