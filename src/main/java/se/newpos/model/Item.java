package se.newpos.model;
/**
 * Contains information of a particular item
 */
public class Item {
    private int itemID;
    private String name;
    private String itemDescription;
    private double price;
    private double vatRate;
    private int quantity;
    /**
     * Creates a new instances representing a particular item.
     * @param itemID The identifier id used for a scanner or manual search from bar code.
     * @param name The name of item
     * @param itemDescription Specifies the item.
     * @param price The price of the item.
     * @param vatRate The vatRate as percent to be added to the price to get full price
     * @param quantity The amount of this particular item
     */
    public Item (int itemID, String name, String itemDescription, double price, double vatRate, int quantity){
        this.itemID = itemID;
        this.name = name;
        this.itemDescription = itemDescription;
        this.price = price;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }
    /**
     * This creates a new item from a itemDTO
     * @param itemDTO the item to be copied from.
     */
    public Item (ItemDTO itemDTO){
        this.itemID = itemDTO.getItemID();
        this.name = itemDTO.getName();
        this.itemDescription = itemDTO.getItemDescription();
        this.price = itemDTO.getPrice();
        this.vatRate = itemDTO.getVatRate();
        this.quantity = 1;
    }
    /**
     * This creates copies of the item.
     * @param item the item to be copied.
     */
    public Item (Item item){
        this.itemID = item.itemID;
        this.name = item.name;
        this.itemDescription = item.itemDescription;
        this.price = item.price;
        this.vatRate = item.vatRate;
        this.quantity = item.quantity;
    }
    
    public void substractQuantity(int amount){
        if(this.quantity - amount < 0){
            System.out.printf("Check inventory for %s. Expected amount in storage is below 0.\n", name);
        }
        this.quantity -= amount;
    }
    /**
     * getter for itemID
     * @return int return the itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Setter for ITemID
     * @param itemID the itemID to set
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Getter for item name
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for item name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for item description
     * @return String return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * setter for itemdesctrion
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * getter for price
     * @return double return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter
     * @return double return the vatRate
     */
    public double getVatRate() {
        return vatRate;
    }

    /**
     * Setter
     * @param vatRate the vatRate to set
     */
    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    /**
     * getter
     * @return int return the quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Setter
     * @param qyantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Increases qyantity by amount
     * @param amount quantity increases by a choosen amount. Ex if the current quantity is 2 and you vant the total to
     * be 5 then you should enter 3 as amount
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

}
