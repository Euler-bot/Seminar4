package se.newpos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.newpos.model.Item;
import se.newpos.model.ItemDTO;

public class DiscountRegistryTest {

	private DiscountRegistry discountRegistry;

	@BeforeEach
	public void setup() {
		this.discountRegistry = new DiscountRegistry();
	}
	@AfterEach
	public void tearDown(){
		this.discountRegistry = null;
	}

	@Test
	public void shouldAddDiscount() {
		
		ItemDTO item = new ItemDTO(new Item(1267, "Basturöktskinka", "Basturökt 200g",
													25.00, 0.12, 50));
		double discountRate = 0.25;
		discountRegistry.addDiscount(item, discountRate);
		assertEquals("Basturöktskinka", discountRegistry.getDiscount(0).getItemsWithDiscount().getName(), "Discount not added");		
	}

	@Test
	public void shouldHasDiscount() {
		String socialNumber = "199610180192";
		ItemDTO item = new ItemDTO(new Item(1267, "Basturöktskinka", "Basturökt 200g",
													25.00, 0.12, 50));
		double discountRate = 0.10;

		discountRegistry.addDiscount(item, discountRate);
		boolean actualValue = discountRegistry.hasDiscount(socialNumber);
		assertTrue(actualValue, "Discount not found for this socialnumber");
	}
	@Test
	public void shouldNotHaveDiscount() {
		String socialNumber = "199610180192";
		ItemDTO item = new ItemDTO(new Item(1267, "Basturöktskinka", "Basturökt 200g",
													25.00, 0.12, 50));
		double discountRate = 0.32;
		discountRegistry.addDiscount(item, discountRate);
		boolean actual = discountRegistry.hasDiscount(socialNumber);
		boolean expected = false;
		assertEquals(expected, actual, "Expected false but returned true.");
	}
	@Test
	public void shouldConvertToInfo() {
		ItemDTO item = new ItemDTO(new Item(1267, "Basturöktskinka", "Basturökt 200g",
													25.00, 0.12, 50));
		double discountRate = 0.10;
		discountRegistry.addDiscount(item, discountRate);
		String socialNumber = "199610180192";

		DiscountInfo actualValue = discountRegistry.convertToInfo(socialNumber);
		String actual = actualValue.getDiscount().get(0).getItemsWithDiscount().getName();
		String expected = "Basturöktskinka";
		assertEquals(expected, actual, "Expected conversion failed.");
	}
	@Test
	public void shouldNotContainConvertedDiscount() {
		ItemDTO item = new ItemDTO(new Item(1267, "Basturöktskinka", "Basturökt 200g",
													25.00, 0.12, 50));
		double discountRate = 0.10;
		discountRegistry.addDiscount(item, discountRate);
		String socialNumber = "198910070195";
		DiscountInfo actualValue = discountRegistry.convertToInfo(socialNumber);
		assertEquals(0, actualValue.getDiscount().size(), "Expected the list to be empty.");
	}

}
