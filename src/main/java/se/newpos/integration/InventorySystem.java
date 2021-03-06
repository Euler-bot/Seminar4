package se.newpos.integration;

import java.util.ArrayList;
import java.util.List;

import se.newpos.model.Item;
import se.newpos.model.ItemDTO;
import se.newpos.model.TransactionDTO;
/**
 * This is the inventorySystem that has the stores inventoryRegister.
 */
public class InventorySystem {
    List<Item> items = new ArrayList<>();
    InventorySystem(){
        addItems();
    }

    /**
     * This function searches in the inventorysystem after the entered item.
     * @param enteredItem This is the entered item with a identifier to be matched.
     * @return a complete ItemDTO to controller so it can add it to sale.
     * @throws ItemNotFoundException when an item is not found in the inventory.
     * @throws ServerDownException When server is not connected.
     */
    public ItemDTO findItemWithIDIdentifier (ItemDTO enteredItem)throws ItemNotFoundException, ServerDownException{
        if(enteredItem.getItemID() == 0){
            throw new ServerDownException("Check connection to server!");
        }

        for (Item item : items){
            if (item.getItemID() == enteredItem.getItemID()){
                return new ItemDTO(item);
            }
        }
        throw new ItemNotFoundException("An item with this ID was not found in inventory. Searched ID was:  " + enteredItem.getItemID());
    } 
    /**
     * Hardcoded items for testing of system
     */
    private void addItems(){
        items.add(new Item(1267, "Basturöktskinka", "Basturökt 200g",
                            25.00, 0.12, 50));
        items.add(new Item(2098, "Mjölk", "ArlaMjölk 3.5%", 
                            17.50, 0.12, 200));
        items.add(new Item(3213, "Paprika 3-pack", "Paprika olika färger",
                            40.90, 0.12,16));
        items.add(new Item(1344, "Sopborste", "Sopborste 120cm", 
                            55.40, 0.12,28));
        items.add(new Item(5643, "Yoggi Vanilj", "Yoggi Vanilj 0.1%", 
                            22.50, 0.12, 94));
        items.add(new Item(6098, "Makaroner", "Kungsörnen Makaroner 1kg",
                            25.00, 0.12, 15));
                     
    }
    /**
     * Getter
     * @return a list of ItemDTO from inventory
     */
    public ArrayList<ItemDTO> getItems(){
        ArrayList<ItemDTO> getItems = new ArrayList<>();
        for(Item item : this.items){
            getItems.add(new ItemDTO(item));
        }
        return getItems;
    }
    /**
     * Updates inventoryamounts of the sold items from gathered from transactionDTO
     * @param transactionDTO is the hoilder of the sold items to update the inventory
     */
    public void updateStorage(TransactionDTO transactionDTO){
        int i = 0;
        int j = 0;
        while(i < transactionDTO.getCurrentSale().getItems().size() ){
            j = 0;
            while(j < this.items.size()){
                String itemToUpdate = transactionDTO.getCurrentSale().getItems().get(i).getName();
                if(itemToUpdate.contains(items.get(j).getName())){
                    int amountToUpdate = transactionDTO.getCurrentSale().getItems().get(i).getQuantity();
                    this.items.get(j).substractQuantity(amountToUpdate);
                }
                j++;
            }
            i++;
        } 
    }
    
}
