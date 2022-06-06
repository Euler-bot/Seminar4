package se.newpos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.newpos.integration.ExternalCreator;
import se.newpos.integration.InventorySystem;
import se.newpos.integration.Printer;
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

		CurrentItemDTO actualItem = controller.enterItem(enteredItemDTO);
		assertEquals("Basturöktskinka", actualItem.getCurrentItemName(),  "EnteredItem is not correct"); 
	}
	@Test
	public void testEnterItemFail() {

		controller.startNewSale();
		ItemDTO enteredItemDTO = new ItemDTO(126);

		CurrentItemDTO actualItem = controller.enterItem(enteredItemDTO);
		assertNull(actualItem, "Expected null but was not null."); 
	}
	@Test
	public void testAddCurrentItemByNegativeAmount() {
		controller.startNewSale();
		CurrentItemDTO expectedItem = controller.enterItem(new ItemDTO(1267));
		int amount = -2;
		CurrentItemDTO resultedItem = controller.addCurrentItemByAmount(expectedItem, amount);
		assertEquals(1, resultedItem.getCurrentItemQuantity(), "Expected amount of items not correct");
	}
	@Test
	public void testAddCurrentItemByAmount() {
		controller.startNewSale();
		CurrentItemDTO expectedItem = controller.enterItem(new ItemDTO(1267));
		int amount = 2;
		CurrentItemDTO resultedItem = controller.addCurrentItemByAmount(expectedItem, amount);
		
		assertEquals(3, resultedItem.getCurrentItemQuantity(), "Expected amount of items not correct");
	}
	@Test
	public void testAddDiscountNoMember() {
		
		controller.startNewSale();
		double actual;
		ItemDTO enteredItemDTO = new ItemDTO(2098);
		controller.enterItem(enteredItemDTO);
		String socialNumber = "198610070195";
		controller.addDiscount(socialNumber);
		actual = controller.getSale().getDiscountAmount();
		double expected = 0;
		assertEquals(expected, actual, "Discount applied even when not member");
	}

	@Test
	public void testAddDiscountMember() {
		
		controller.startNewSale();
		double actual;
		ItemDTO enteredItemDTO = new ItemDTO(2098);
		controller.enterItem(enteredItemDTO);
		String socialNumber = "198610070198";
		controller.addDiscount(socialNumber);
		actual = controller.getSale().getDiscountAmount();
		assertNotEquals(0, actual, "Discount not applied correctly");
	}

	@Test
	public void testEnterPaymentEnough() {
		
		controller.startNewSale();
		ItemDTO enteredItemDTO = new ItemDTO(1267);
		controller.enterItem(enteredItemDTO);
		double expectedAmount = 500;
		controller.enterPayment(expectedAmount);
		assertEquals(expectedAmount, controller.getSale().gPayment().getAmount(), 
						"Payment not applied as argument");
	}
	@Test
	public void testEnterPaymentNotEnough() {
		
		controller.startNewSale();
		ItemDTO enteredItemDTO = new ItemDTO(1267);
		CurrentItemDTO  currentItemDTO = controller.enterItem(enteredItemDTO);
		controller.addCurrentItemByAmount(currentItemDTO, 10);
		controller.enterPayment(100);
		assertEquals(0, controller.getSale().gPayment().getAmount(), 
						"Program added payment even tho payment not enough");

	}
	
	@Test
	public void testEndSales() {
		controller.startNewSale();
		ExternalCreator ext = new ExternalCreator();
		InventorySystem inv = ext.getInventorySystem();
		CurrentItemDTO actualPrice = controller.enterItem(new ItemDTO(1267));
		ItemDTO expectedPrice = inv.findItemWithIDIdentifier(new ItemDTO(1267));
		assertEquals(actualPrice.getRunningTotalPrice(), (expectedPrice.getPrice() * (1 + expectedPrice.getVatRate())), "Not same totalPrice at Endsale");

	}
}
