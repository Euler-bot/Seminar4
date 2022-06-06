package se.newpos.model;
/**
 * This is used for sending to external systems.
 */
public final class TransactionDTO {
    private final Sale currentSale;
    private final Payment payment;
    private final CashRegister currentRegister;
    private final int orderNumber;
    /**
     * Contains data transfer object of a transaction.
     * @param transaction the transaction copied to a DTO.
     */
    public TransactionDTO (Transaction transaction){
        this.currentSale = new Sale(transaction.getCurrentSale());
        this.payment = transaction.getPayment();
        this.currentRegister = transaction.getCurrentRegister();
        this.orderNumber = Transaction.getOrderNumber();
    }
    /**
     * Getter for sale from this DTO
     * @return Sale
     */
    public Sale getCurrentSale() {
        return currentSale;
    }
    /**
     * Getter for payment
     * @return payment
     */
    public Payment getPayment() {
        return payment;
    }
    /**
     * Getter for register
     * @return CashRegister
     */
    public CashRegister getCurrentRegister() {
        return currentRegister;
    }
    /**
     * Getter for this ordernumber
     * @return an int
     */
    public int getOrderNumber() {
        return orderNumber;
    }
    
}
