package se.newpos.model;
/**
 * This is a data transfer object used for transfering information about the last entered item.
 */
public final class CurrentItemDTO {
    private final int currentItemID;
    private final String currentItemName;
    private final String currentItemDescription;
    private final double currentItemPrice;
    private final int currentItemQuantity;
    private final double runningTotalPrice;
    private final double runningVat;
    /**
     * This creates an instance of runningTotal and last entered item.
     * @param item The last entered item with choosen identifier.
     * @param runningTotal This is current sales runningtotal.
     */
    public CurrentItemDTO(Item item, RunningTotal runningTotal){
        this.currentItemID = item.getItemID();
        this.currentItemName = item.getName();
        this.currentItemDescription = item.getItemDescription();
        this.currentItemPrice = item.getPrice();
        this.currentItemQuantity = item.getQuantity();
        this.runningTotalPrice = runningTotal.getTotalPrice();
        this.runningVat = runningTotal.getTotalVat();
    }
    public int getCurrentItemID() {
        return currentItemID;
    }
    public String getCurrentItemName() {
        return currentItemName;
    }
    public String getCurrentItemDescription() {
        return currentItemDescription;
    }
    public double getCurrentItemPrice() {
        return currentItemPrice;
    }
    public int getCurrentItemQuantity() {
        return currentItemQuantity;
    }
    public double getRunningTotalPrice() {
        return runningTotalPrice;
    }
    public double getRunningVat() {
        return runningVat;
    }
    
}
