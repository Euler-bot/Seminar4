package se.newpos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ItemTest {

	private Item item;

	@BeforeEach
	public void setup() {
		this.item = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 10);
	}
	@AfterEach
	public void tearDown(){
		this.item = null;
	}

	@Test
	public void testSubstractQuantity() {
		int amount = 5;
		int expected = 5;
		item.substractQuantity(amount);
		assertEquals(expected, item.getQuantity(), "Substraction not as suspected");
	}
	@Test
	public void testSubstractQuantityBelowZero() {
		int amount = 12;
		int expected = -2;
		item.substractQuantity(amount);
		assertEquals(expected, item.getQuantity(), "Substraction not as suspected");
	}

}
