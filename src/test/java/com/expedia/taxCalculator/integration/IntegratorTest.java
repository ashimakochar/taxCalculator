package com.expedia.taxCalculator.integration;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.expedia.taxCalculator.collection.ItemCollection;
import com.expedia.taxCalculator.responseModel.ResponseModel;
import com.expedia.taxCalculator.service.CalculatorService;

public class IntegratorTest {
	@Mock
	private ItemCollection itemCollection;
	@Mock
	private CalculatorService calculatorService;
	@InjectMocks
	private Integrator integrator;
	@Before
	public void init() {
	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getTaxCalculationTest() {
		ResponseModel model=new ResponseModel();
		model.setSalesTax(new BigDecimal("45.56"));
		model.setTotalPrice(new BigDecimal("29.9"));
		Mockito.doNothing().when(itemCollection).addToList(Mockito.anyString(), Mockito.any());
		Mockito.when(calculatorService.fetchTaxCalculation(Mockito.any())).thenReturn(model);
		ResponseModel response=integrator.getTaxCalculation(new String[]{"1 book: 29.49","1 music CD: 15.99","1 chocolate snack: 0.75"});
		Assert.assertNotNull(response);
		Assert.assertEquals(new BigDecimal("29.9"), response.getTotalPrice());
	}
}
