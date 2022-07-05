package se.newpos.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.newpos.integration.ExternalCreator;
import se.newpos.integration.ItemNotFoundException;
import se.newpos.integration.Printer;
import se.newpos.integration.ServerDownException;
import se.newpos.model.CurrentItemDTO;
import se.newpos.model.ItemDTO;


public class ControllerTest {
	private Controller controller;


	@BeforeEach
	public void setup() {
		this.controller = new Controller(new ExternalCreator(), new Printer());
	
	}
	@AfterEach
	public void tearDown(){
		this.controller = null;
	}
	@Test
	public void testEnterItem() {

		controller.startNewSale();
		ItemDTO enteredItemDTO = new ItemDTO(1267);

		CurrentItemDTO actualItem;
		try {
			actualItem = controller.enterItem(enteredItemDTO);
			assertEquals("Basturöktskinka", actualItem.getCurrentItemName(),  "EnteredItem is not correct");
		} catch (ItemNotFoundException | ServerDownException e) {
			fail("An exception was thrown on a valid item: " + e.getMessage());
		} 
		 
	}
	@Test
	public void testEnterItemFail() {
		controller.startNewSale();
		ItemDTO enteredItemDTO = new ItemDTO(126);
		try {
			
			controller.enterItem(enteredItemDTO);
			fail("Found an item that it should not have done.");
		}catch (ItemNotFoundException | ServerDownException e) {
			assertTrue(e.getMessage().contains(String.valueOf(enteredItemDTO.getItemID())), "Wrong exception message, does not contain entered item: " +
			e.getMessage());
		}		 
	}
	@Test
	public void testServerException() {
		controller.startNewSale();
		ItemDTO enteredItemDTO = new ItemDTO(0);
		try {
			controller.enterItem(enteredItemDTO);
			fail("No exception was made.");
		}catch (ServerDownException | ItemNotFoundException e) {
			assertTrue(e.getMessage().contains("server"), "Wrong exception message was made: " +
			e.getMessage());
		}
		 
	}
	@Test
	public void testAddCurrentItemByNegativeAmount() {
		controller.startNewSale();
		CurrentItemDTO expectedItem;
		try {
			expectedItem = controller.enterItem(new ItemDTO(1267));
			int amount = -2;
			CurrentItemDTO resultedItem = controller.addCurrentItemByAmount(expectedItem, amount);
			assertEquals(1, resultedItem.getCurrentItemQuantity(), "Expected amount of items not correct");	
		} catch (ItemNotFoundException | ServerDownException e) {
			fail("An valid item caused an exception: " + e.getMessage());
		}
		
	}
	@Test
	public void testAddCurrentItemByAmount() {
		controller.startNewSale();
		CurrentItemDTO expectedItem;
		try {
			expectedItem = controller.enterItem(new ItemDTO(1267));
			int amount = 2;
			CurrentItemDTO resultedItem = controller.addCurrentItemByAmount(expectedItem, amount);
			assertEquals(3, resultedItem.getCurrentItemQuantity(), "Expected amount of items not correct");
		} catch (ItemNotFoundException | ServerDownException e) {
			fail("An valid item caused an exception: " + e.getMessage());
		}
		
	}
	@Test
	public void testAddDiscountNoMember() {
		
		controller.startNewSale();
		double actual;
		ItemDTO enteredItemDTO = new ItemDTO(2098);
		try {
			controller.enterItem(enteredItemDTO);
			String socialNumber = "199610180195";
			controller.addDiscount(socialNumber);
			actual = controller.getSale().getDiscountAmount();
			double expected = 0;
			assertEquals(expected, actual, "Discount applied even when not member");
		} catch (ItemNotFoundException | ServerDownException e) {
			fail("An valid item caused an exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddDiscountMember() {
		
		controller.startNewSale();
		double actual;
		ItemDTO enteredItemDTO = new ItemDTO(2098);
		try {
			controller.enterItem(enteredItemDTO);
			String socialNumber = "199610180192";
			controller.addDiscount(socialNumber);
			actual = controller.getSale().getDiscountAmount();
			assertNotEquals(0, actual, "Discount not applied correctly");
		} catch (ItemNotFoundException | ServerDownException e) {
			fail("An valid item caused an exception: " + e.getMessage());
		}
	}
}
