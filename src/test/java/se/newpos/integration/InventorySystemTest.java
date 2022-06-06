package se.newpos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.newpos.model.CashRegister;
import se.newpos.model.CurrentItemDTO;
import se.newpos.model.Item;
import se.newpos.model.ItemDTO;
import se.newpos.model.Sale;
import se.newpos.model.Transaction;
import se.newpos.model.TransactionDTO;


public class InventorySystemTest {

	private InventorySystem inventorySystem;

	@BeforeEach
	public void setup() {
		this.inventorySystem = new InventorySystem();
	}
	@AfterEach
	public void tearDown(){
		this.inventorySystem = null;
	}

	@Test
	public void testFindItemWithIDIdentifier() {
		
		ItemDTO enteredItem = new ItemDTO(2098);

		ItemDTO actualValue = inventorySystem.findItemWithIDIdentifier(enteredItem);
		assertEquals("Mjölk", actualValue.getName(), "Didnt return the right item.");

	}
	@Test
	public void testNotFindItemWithIDIdentifier() {
		
		ItemDTO enteredItem = new ItemDTO(126);

		ItemDTO actualValue = inventorySystem.findItemWithIDIdentifier(enteredItem);
		assertNull(actualValue, "Didnt return null.");

	}

	@Test
	public void testGetItems() {
		ArrayList<ItemDTO> expected = new ArrayList<>();
		expected.add(new ItemDTO(new Item(1267, "Basturöktskinka", "Basturökt 200g",
                            25.00, 0.12, 50)));
        expected.add(new ItemDTO(new Item(2098, "Mjölk", "ArlaMjölk 3.5%", 
                            17.50, 0.12, 200)));
        expected.add(new ItemDTO(new Item(3213, "Paprika 3-pack", "Paprika olika färger",
                            40.90, 0.12,16)));
        expected.add(new ItemDTO(new Item(1344, "Sopborste", "Sopborste 120cm", 
                            55.40, 0.12,28)));
        expected.add(new ItemDTO(new Item(5643, "Yoggi Vanilj", "Yoggi Vanilj 0.1%", 
                            22.50, 0.12, 94)));
        expected.add(new ItemDTO(new Item(6098, "Makaroner", "Kungsörnen Makaroner 1kg",
                            25.00, 0.12, 15)));
		ArrayList<ItemDTO> actualValue = inventorySystem.getItems();
		for(int i = 0; i < expected.size(); i++){
			assertEquals(expected.get(i).getName(), actualValue.get(i).getName(), "List of items not collected properly");
		}
	}
	@Test
	public void testGetItemsSize() {
		ArrayList<ItemDTO> expected = new ArrayList<>();
		expected.add(new ItemDTO(new Item(1267, "Basturöktskinka", "Basturökt 200g",
                            25.00, 0.12, 50)));
        expected.add(new ItemDTO(new Item(2098, "Mjölk", "ArlaMjölk 3.5%", 
                            17.50, 0.12, 200)));
        expected.add(new ItemDTO(new Item(3213, "Paprika 3-pack", "Paprika olika färger",
                            40.90, 0.12,16)));
        expected.add(new ItemDTO(new Item(1344, "Sopborste", "Sopborste 120cm", 
                            55.40, 0.12,28)));
        expected.add(new ItemDTO(new Item(5643, "Yoggi Vanilj", "Yoggi Vanilj 0.1%", 
                            22.50, 0.12, 94)));
        expected.add(new ItemDTO(new Item(6098, "Makaroner", "Kungsörnen Makaroner 1kg",
                            25.00, 0.12, 15)));
		ArrayList<ItemDTO> actualValue = inventorySystem.getItems();
		assertEquals(expected.size(), actualValue.size(), "List of items not collected properly, different size");
	}
	@Test
	public void testUpdateStorage() {
		Sale sale = new Sale();
		ItemDTO addItemInSale = inventorySystem.findItemWithIDIdentifier(new ItemDTO(1267));
		CurrentItemDTO currentItem = sale.addEnteredItem(addItemInSale);
		sale.addCurrentItem(currentItem, 5);
		addItemInSale = inventorySystem.findItemWithIDIdentifier(new ItemDTO(2098));
		currentItem = sale.addEnteredItem(addItemInSale);
		sale.addCurrentItem(currentItem, 10);
		sale.enteredPayment(1500);
		TransactionDTO transactionDTO = new TransactionDTO(new Transaction(sale, sale.gPayment(), new CashRegister()));
		inventorySystem.updateStorage(transactionDTO);
		
		int expected = 44;
		int actual = inventorySystem.items.get(0).getQuantity();
		assertEquals(expected, actual, "Update not as expected.");
		expected = 189;
		actual = inventorySystem.items.get(1).getQuantity();
		assertEquals(expected, actual, "Update not as expected.");
	}
	@Test
	public void testUpdateStorageFail() {
		Sale sale = new Sale();
		Item addItemInSale = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		ItemDTO itemToAdd = new ItemDTO(addItemInSale);
		CurrentItemDTO currentItem = sale.addEnteredItem(itemToAdd);
		sale.addCurrentItem(currentItem, 5);
		sale.enteredPayment(1500);
		TransactionDTO transactionDTO = new TransactionDTO(new Transaction(sale, sale.gPayment(), new CashRegister()));
		int expected = 0;
		for(Item item : inventorySystem.items){
			expected += item.getQuantity();
		}
		int actual = 0;
		inventorySystem.updateStorage(transactionDTO);
		for(Item item : inventorySystem.items){
			actual += item.getQuantity();
		}
		assertEquals(expected, actual, "Inventorylist has been changed");
	
	}
}
