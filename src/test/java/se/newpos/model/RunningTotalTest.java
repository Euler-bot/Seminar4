package se.newpos.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RunningTotalTest {

	private RunningTotal runningTotal;

	@BeforeEach
	public void setup() {
		this.runningTotal = new RunningTotal();
	}
	@AfterEach
	public void tearDown(){
		this.runningTotal = null;
	}

	@Test
	public void testAddTotalVat() {
		
		double expected = 12.33;
		runningTotal.addTotalVat(expected);
		assertEquals(expected, runningTotal.getTotalVat(), "Vat not added correctly");
	}

	@Test
	public void testAddTotalPrice() {
		double expected = 50.14;
		runningTotal.addTotalPrice(expected);
		assertEquals(expected, runningTotal.getTotalPrice(), "TotalPrice not added correctly");
	}

	@Test
	public void testAddTotalNetPrice() {
		double expected = 18.99;

		runningTotal.addTotalNetPrice(expected);
		assertEquals(expected, runningTotal.getTotalNetPrice(), "Total net price not added correctly");
	}

	@Test
	public void testAddNumberOfItems() {
		int expected = 5;
		runningTotal.addNumberOfItems(expected);
		assertEquals(expected, runningTotal.getTotalNumberOfItems(), "Number of items not added correctly");
	}

}
