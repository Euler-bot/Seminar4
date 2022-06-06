package se.newpos.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DiscountTest {
	private Discount discount;

	@BeforeEach
	public void setup() {
		Item itemWithDiscount = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		this.discount = new Discount(itemWithDiscount, 0.10);
	}

	@Test
	public void testSearchSocialNumber() {
		String socialNumber = "198610070198";

		boolean actualValue = discount.searchSocialNumber(socialNumber);
		assertTrue(actualValue, "Socialnumber not found, so discount not applied correctly");
	}
}
