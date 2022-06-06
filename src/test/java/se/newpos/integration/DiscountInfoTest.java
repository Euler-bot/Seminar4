package se.newpos.integration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


import se.newpos.model.Discount;
import se.newpos.model.Item;


public class DiscountInfoTest {

	private DiscountInfo discountInfo;

	@BeforeEach
	public void setup() {
		this.discountInfo = new DiscountInfo();
	}

	@Test
	public void shouldAddDiscountToList() {
		Item itemWithDiscount = new Item(12, "Bröd", "Pågen Limpa", 27.50, 0.12, 1);
		Discount discount = new Discount(itemWithDiscount, 0.10);
		discountInfo.addDiscountToList(discount);

		assertTrue(discountInfo.getDiscount() != null, "Discountinfo list is empty.");
	
	}

	
}
