package se.newpos.model;
/**
 * Contains all information from one completed sale.
 */
public class Transaction {
    private Sale currentSale;
    private final Payment payment;
    private final CashRegister currentRegister;
    private static int orderNumber = 0;

    /**
     * This is one instances of a completed sale.
     * @param currentSale this is the completed sale 
     * @param payment this contains the payment
     * @param currentRegister this is the register operated by the cashier.
     */
    public Transaction(Sale currentSale, Payment payment, CashRegister currentRegister){
        this.currentSale = new Sale(currentSale);
        this.payment = payment;
        this.currentRegister = currentRegister;
        orderNumber++;
        setEndTimeOfSale();
    }
    /**
     * Sets the endTime of this sale.
     */
    private void setEndTimeOfSale(){
        this.currentSale.getSaleTimePeriod().setEndTimeOfSale();
    }
    public Sale getCurrentSale() {
        return currentSale;
    }
    public Payment getPayment() {
        return payment;
    }
    public CashRegister getCurrentRegister() {
        return currentRegister;
    }
    public static int getOrderNumber() {
        return orderNumber;
    }
}
