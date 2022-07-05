package se.newpos.model;
/**
 * A interface for recieving the total from every completed sale.
 */
public interface SaleObserver {

    /**
     * Called to update the total when a sale is made.
     * @param saleAmount contains the and sum that was completed
     */
    void updateTotal(RunningTotal runningTotal);
	
}