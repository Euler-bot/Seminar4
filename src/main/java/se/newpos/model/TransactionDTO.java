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
    public Sale getCurrentSale() {
        return currentSale;
    }
    public Payment getPayment() {
        return payment;
    }
    public CashRegister getCurrentRegister() {
        return currentRegister;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    
}
