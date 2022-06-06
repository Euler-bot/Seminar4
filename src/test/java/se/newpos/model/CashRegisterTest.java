package se.newpos.model;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashRegisterTest {

	private CashRegister cashRegister;

	@BeforeEach
	public void setup() {
		this.cashRegister = new CashRegister();
	}
	@AfterEach
	public void tearDown(){
		this.cashRegister = null;
	}

	@Test
	public void testAddPayment() {
		Payment paid = new Payment(100);
		cashRegister.addPayment(paid);
		double expected = 1100;
		assertEquals(expected, this.cashRegister.getBalance(), "Balance not as expected");
	}
}
