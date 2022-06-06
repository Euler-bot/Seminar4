package se.newpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import se.newpos.integration.DiscountInfo;
import se.newpos.integration.Printer;


public class SaleTest {


	private Sale sale;


	@BeforeEach
	public void setup() {
		this.sale = new Sale();
	}
	@AfterEach
	public void tearDown(){
		this.sale = null;
	}

	@Test
	public void testAddEnteredItem() {
		Item expectedItem = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		ItemDTO expectedItemDTO = new ItemDTO(expectedItem);
		CurrentItemDTO actualValue = sale.addEnteredItem(expectedItemDTO);
		assertEquals(expectedItemDTO.getName(), actualValue.getCurrentItemName(), "Expecteditem not matching actual when adding item in sale.");
		}
	@Test
	public void testAddDuplicateReturnNull() {
		Item itemToDuplicate = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		sale.addEnteredItem(new ItemDTO(itemToDuplicate));
		CurrentItemDTO actualItem = sale.addDuplicate(new ItemDTO(11));
		assertNull(actualItem, "Didnt returned null.");
	}
	@Test
	public void testAddDuplicateQuantityIncreased() {
		Item itemToDuplicate = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		sale.addEnteredItem(new ItemDTO(itemToDuplicate));
		sale.addDuplicate(new ItemDTO(12));
		assertEquals(2, sale.getItems().get(0).getQuantity(), "Duplication failed, quantity not increased.");
	}
	@Test
	public void testAddCurrentItemWithAmount() {
		Item expectedItem = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		CurrentItemDTO currentItemDTO = sale.addEnteredItem(new ItemDTO(expectedItem));
		int amount = 3;
		sale.addCurrentItem(currentItemDTO, amount);
		assertEquals(4, sale.getItems().get(0).getQuantity(), "Expected quantity not as expected");
	}
	@Test
	public void testAddCurrentItemWithNegativeAmount() {
		Item expectedItem = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		CurrentItemDTO currentItemDTO = sale.addEnteredItem(new ItemDTO(expectedItem));
		int amount = -3;
		CurrentItemDTO actualValue = sale.addCurrentItem(currentItemDTO, amount);
		assertEquals(currentItemDTO, actualValue, "Recieved another itemDTO");
	}
	@Test
	public void testNotInSale() {
		Item expectedItem = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		sale.addEnteredItem(new ItemDTO(expectedItem));
		ItemDTO enteredItemDTO = new ItemDTO(11);
		boolean actualValue = sale.inSale(enteredItemDTO);
		assertTrue(!actualValue, "Wrong boolean returned");
	}
	@Test
	public void testInSale() {
		Item expectedItem = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		sale.addEnteredItem(new ItemDTO(expectedItem));
		ItemDTO enteredItemDTO = new ItemDTO(12);
		boolean actualValue = sale.inSale(enteredItemDTO);
		assertTrue(actualValue, "EnteredItem not found in sale.");
	}

	@Test
	public void testGetSaleTimePeriod() {
		SaleTimePeriod actualValue = sale.getSaleTimePeriod();
		assertNotNull(actualValue, "SaletimePeriod not created correct");

	}

	@Test
	public void testGetRunningTotal() {
		Item expectedItem = new Item(12, "Bröd", "Pågen Limpa", 80.00, 0.25, 1);
		sale.addEnteredItem(new ItemDTO(expectedItem));
		double expectedTotalPrice = (80.00 * 1.25);
		double expectedVat = (80.00 * 1.25) - 80.00; 
		RunningTotal actualRunningTotal = sale.getRunningTotal();
		assertEquals(expectedTotalPrice, actualRunningTotal.getTotalPrice(), "Expected total price not correct.");
		assertEquals(expectedVat, actualRunningTotal.getTotalVat(), "Expected vat not correct");
		
	}

	@Test
	public void testGetItems() {
		Item expectedFirstItem = new Item(12, "Bröd", "Pågen Limpa", 80.00, 0.25, 1);
		Item expectedSecondItem = new Item(13, "Skinka", "Skinka", 20.00, 0.25, 1);
		sale.addEnteredItem(new ItemDTO(expectedFirstItem));
		sale.addEnteredItem(new ItemDTO(expectedSecondItem));
		ArrayList<Item> actualValue = sale.getItems();
		assertNotNull(actualValue, "List not containing any items.");
		assertEquals(expectedFirstItem.getName(), actualValue.get(0).getName(), "First item not as expected");
		assertEquals(expectedSecondItem.getName(), actualValue.get(1).getName(), "Second item not as expected");
	
	}

	@Test
	public void testAddDiscountForItemsInSale() {
		Item expectedFirstItem = new Item(12, "Bröd", "Pågen Limpa", 80.00, 0.25, 1);
		Item expectedSecondItem = new Item(13, "Skinka", "Skinka", 20.00, 0.25, 1);
		sale.addEnteredItem(new ItemDTO(expectedFirstItem));
		sale.addEnteredItem(new ItemDTO(expectedSecondItem));
		Discount discountForItem = new Discount(expectedFirstItem, 0.25);
		DiscountInfo discountToAddTestInSale = new DiscountInfo();
		discountToAddTestInSale.addDiscountToList(discountForItem);
		
		sale.addDiscountForItemsInSale(discountToAddTestInSale);
		double expectedDiscount = 20.00;
		assertEquals(expectedDiscount, sale.getDiscountAmount(), "Discount not added correctly");

	}


	@Test
	public void testEnteredPayment() {
		Item expectedFirstItem = new Item(12, "Bröd", "Pågen Limpa", 80.00, 0.25, 1);
		sale.addEnteredItem(new ItemDTO(expectedFirstItem));
		double amount = -50;
		double expectedChange = 0;
		double expectedPayment = 0;
		sale.enteredPayment(amount);
		assertEquals(expectedPayment, sale.gPayment().getAmount(), "Payment amount not as expected");
		assertEquals(expectedChange, sale.gPayment().getChange(), "Change not as expected");
		expectedChange = 0;
		expectedPayment = 50;
		amount = 50;
		sale.enteredPayment(amount);
		assertEquals(expectedPayment, sale.gPayment().getAmount(), "Payment amount not as expected");
		assertEquals(expectedChange, sale.gPayment().getChange(), "Change not as expected");
		amount = 100;
		sale.enteredPayment(amount);
		expectedChange = 50;
		expectedPayment = 150;
		assertEquals(expectedPayment, sale.gPayment().getAmount(), "Payment amount not as expected");
		assertEquals(expectedChange, sale.gPayment().getChange(), "Change not as expected");

	}


	@Test
	public void testPrintReciept() {
		Item expectedFirstItem = new Item(12, "Bröd", "Pågen Limpa", 80.00, 0.25, 1);
		sale.addEnteredItem(new ItemDTO(expectedFirstItem));
		sale.enteredPayment(200);
		Printer printer = new Printer();
		CashRegister cashRegister = new CashRegister();
		cashRegister.addPayment(sale.gPayment());
		
		TransactionDTO actualValue = sale.printReciept(printer, cashRegister);
		TransactionDTO expected = new TransactionDTO(new Transaction(this.sale, this.sale.gPayment(), cashRegister));
		assertEquals(expected.getCurrentSale().getRunningTotal().getTotalPrice(), actualValue.getCurrentSale().getRunningTotal().getTotalPrice(),
										 "Creation of TransactionDTO was not same as expected");

	}

}
