package com.expedia.taxCalculator.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.expedia.taxCalculator.Item.Item;
import com.expedia.taxCalculator.Item.ItemType;
import com.expedia.taxCalculator.Item.SubType.Books;
import com.expedia.taxCalculator.Item.SubType.CD;
import com.expedia.taxCalculator.Item.SubType.Pills;
import com.expedia.taxCalculator.Item.SubType.Pins;
import com.expedia.taxCalculator.Item.SubType.Snack;
import com.expedia.taxCalculator.Item.SubType.Wine;
import com.expedia.taxCalculator.responseModel.ResponseModel;
import com.expedia.taxCalculator.serviceImpl.CalculatorServiceImpl;
import com.expedia.taxCalculator.utilities.Utilities;

public class CalculatorServiceTest {

	@InjectMocks
	private CalculatorServiceImpl service;

	private List<Item> item;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fetchTaxCalculationTest() {
		item = new ArrayList<>();
		Item item1 = new Books();
		item1.setPrice(new BigDecimal(29.49));
		item1.setQuantity(1);
		item1.setType(ItemType.BOOK);
		item1.setTaxEligible(true);
		item1.setAdditionalAmount(new BigDecimal(0.0));
		Item item2 = new CD();
		item2.setPrice(new BigDecimal(15.99));
		item2.setQuantity(1);
		item2.setType(ItemType.CD);
		item2.setTaxEligible(true);
		item2.setAdditionalAmount(new BigDecimal(1.25));
		Item item3 = new Snack();
		item3.setPrice(new BigDecimal(0.75));
		item3.setQuantity(1);
		item3.setType(ItemType.SNACK);
		item3.setAdditionalAmount(new BigDecimal(0.0));
		item3.setTaxEligible(true);
		item.add(item1);
		item.add(item2);
		item.add(item3);

		ResponseModel res = service.fetchTaxCalculation(item);
		Assert.assertNotNull(res);
		Assert.assertEquals(res.getTotalPrice(), Utilities.roundingOff(new BigDecimal(55.65)));
		Assert.assertEquals(res.getSalesTax(), Utilities.roundingOff(new BigDecimal(9.50)));
	}
	
	@Test
	public void fetchTaxCalculationTest2() {
		item = new ArrayList<>();
		Item item1 = new Wine();
		item1.setPrice(new BigDecimal(20.99));
		item1.setQuantity(1);
		item1.setType(ItemType.WINE);
		item1.setTaxEligible(true);
		item1.setAdditionalAmount(new BigDecimal(0.0));
		Item item2 = new Pills();
		item2.setPrice(new BigDecimal(4.15));
		item2.setQuantity(1);
		item2.setType(ItemType.PILLS);
		item2.setTaxEligible(false);
		item2.setAdditionalAmount(new BigDecimal(0.0));
		Item item3 = new Pins();
		item3.setPrice(new BigDecimal(11.25));
		item3.setQuantity(1);
		item3.setType(ItemType.PINS);
		item3.setAdditionalAmount(new BigDecimal(0.0));
		item3.setTaxEligible(true);
		Item item4 = new CD();
		item4.setPrice(new BigDecimal(14.99));
		item4.setQuantity(1);
		item4.setType(ItemType.PINS);
		item4.setAdditionalAmount(new BigDecimal(1.25));
		item4.setTaxEligible(true);
		item.add(item1);
		item.add(item2);
		item.add(item3);
		item.add(item4);

		ResponseModel res = service.fetchTaxCalculation(item);
		Assert.assertNotNull(res);
		Assert.assertEquals(res.getTotalPrice(), Utilities.roundingOff(new BigDecimal(61.05)));
		Assert.assertEquals(res.getSalesTax(), Utilities.roundingOff(new BigDecimal(9.75)));
	}

}
