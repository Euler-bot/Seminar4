package se.newpos.model;
/**
 * This is a data transfer objec used for transferering information of an item.
 */
public final class ItemDTO {
    private final int itemID;
    private String name;
    private String itemDescription;
    private double price;
    private double vatRate;
    /**
     * This is used when creating a ItemDTO with only an ID from ex a scanner or manual input so this then can be searhed in database with.
     * @param itemID this is from the identifier of choice.
     */
    public ItemDTO(int itemID){
        this.itemID = itemID;
    }
 
    /**
     * This creates an ItemDTO from an item in inventorySystem to be send to sale. 
     * @param item this holds all information to create an DTO.
     */
    public ItemDTO(Item item){
        this.itemID = item.getItemID();
        this.name = item.getName();
        this.itemDescription = item.getItemDescription();
        this.price = item.getPrice();
        this.vatRate = item.getVatRate();
    }
    /**
     * @return int return the itemID
     */
    public int getItemID() {
        return itemID;
    }
    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return String return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }
    /**
     * @return double return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @return double return the vatRate
     */
    public double getVatRate() {
        return vatRate;
    }


}
