package se.newpos.integration;
/**
 * ItemNotFoundException, this handles the exception when an item is not found from the external inventory system.
 */
public class ItemNotFoundException extends Exception {
    /**
     * this method creates a new instance
     * @param message The message to be shown.
     */
    public ItemNotFoundException(String message){
        super(message);
    }
}