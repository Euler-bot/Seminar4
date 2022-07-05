package se.newpos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.newpos.model.Item;
import se.newpos.model.ItemDTO;


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
		try {
			ItemDTO actualValue = inventorySystem.findItemWithIDIdentifier(enteredItem);
			assertEquals("Mjölk", actualValue.getName(), "Didnt return the right item.");
		} catch (ItemNotFoundException | ServerDownException e) {
			fail("Exception was thrown on a valid item: " + e.getMessage());
		}
		

	}
	@Test
	public void testNotFindItemWithIDIdentifier() {
		
		ItemDTO enteredItemDTO = new ItemDTO(126);

		try {
			inventorySystem.findItemWithIDIdentifier(enteredItemDTO);
			fail("Found an item that it should not have done.");
		} catch (ItemNotFoundException | ServerDownException e) {
			assertTrue(e.getMessage().contains(String.valueOf(enteredItemDTO.getItemID())), "Wrong exception message, does not contain entered item: " +
			e.getMessage());

		}
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
}
