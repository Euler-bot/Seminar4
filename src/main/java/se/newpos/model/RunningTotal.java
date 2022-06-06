package se.newpos.model;
/**
 * The runningtotal of one sale which is used to view after every entered item.
 */
public class RunningTotal {
    private double totalNetPrice;
    private double totalVat;
    private double totalPrice;
    private int totalNumberOfItems;
    /**
     * 
     */
    public RunningTotal(){
        this.totalNetPrice = 0;
        this.totalVat = 0;
        this.totalPrice = 0;
        this.totalNumberOfItems = 0;
    }
    /**
     * Adds the specified amount to this current runningTotal from the current sale.
     * @param amount this is specified from the item that adds in sale.
     */
    public void addTotalVat(double amount){
        this.totalVat += amount;
    }
    /**
     * Adds the specified amount to this current runningTotal from the current sale.
     * @param amount this is specified from the item that adds in sale.
     */
    public void addTotalPrice(double amount){
        this.totalPrice += amount;
    }
    /**
     * Adds the specified amount to this current runningTotal from the current sale.
     * @param amount this is specified from the item that adds in sale.
     */
    public void addTotalNetPrice(double amount){
        this.totalNetPrice += amount;
    }
    public void addNumberOfItems(int amount){
        this.totalNumberOfItems += amount;
    }
    public int getTotalNumberOfItems(){
        return totalNumberOfItems;
    }
    /**
     * @return double return the totalNetPrice
     */
    public double getTotalNetPrice() {
        return totalNetPrice;
    }

    /**
     * @param totalNetPrice the totalNetPrice to set
     */
    public void setTotalNetPrice(double totalNetPrice) {
        this.totalNetPrice = totalNetPrice;
    }

    /**
     * @return double return the totalVat
     */
    public double getTotalVat() {
        return totalVat;
    }

    /**
     * @param totalVat the totalVat to set
     */
    public void setTotalVat(double totalVat) {
        this.totalVat = totalVat;
    }
    /**
     * @return double return the runningTotal
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
