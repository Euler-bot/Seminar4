package se.newpos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PaymentTest {

	private double amount = 200;

	private Payment payment;

	@BeforeEach
	public void setup() {
		this.payment = new Payment(amount);
	}
	@AfterEach
	public void tearDown(){
		this.payment = null;
	}
	@Test
	public void testCalculateChange() {
		
		Sale currentSale = new Sale();
		currentSale.getRunningTotal().setTotalPrice(100);
		payment.calculateChange(currentSale);
		double expectedChange = 100;
		double actual = payment.getChange();
		assertEquals(expectedChange, actual, "Change not calculated correctly");

	}
	@Test
	public void testCalculateChangeWhenPaymentNotEnough() {
		
		Sale currentSale = new Sale();
		currentSale.getRunningTotal().setTotalPrice(300);
		payment.calculateChange(currentSale);
		double expectedChange = 0;
		double actual = payment.getChange();
		assertEquals(expectedChange, actual, "Change not calculated correctly");
	}

}
