package com.expedia.taxCalculator.config;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import com.expedia.taxCalculator.Item.ItemType;
import com.expedia.taxCalculator.constants.CalculatorConstants;

public class ItemConfigTest {
	@Mock
	private Environment env;
	@InjectMocks
	private ItemConfig config;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(env.getProperty(CalculatorConstants.PREFIX + ItemType.BOOK+CalculatorConstants.SUFFIX)).thenReturn("true:0.0");
		Mockito.when(env.getProperty(CalculatorConstants.PREFIX + ItemType.CD+CalculatorConstants.SUFFIX)).thenReturn("true:1.25");
		Mockito.when(env.getProperty(CalculatorConstants.PREFIX + ItemType.MUSIC+CalculatorConstants.SUFFIX)).thenReturn("true:0.0");
		Mockito.when(env.getProperty(CalculatorConstants.PREFIX + ItemType.PILLS+CalculatorConstants.SUFFIX)).thenReturn("false:0.0");
	}
	
	@Test
	public void initConnReadTimeoutMapTest() {
		Mockito.when(env.getProperty(CalculatorConstants.PREFIX+CalculatorConstants.TAX_PERCENTAGE)).thenReturn("17.5");
		Map<String,String> map=config.initConnReadTimeoutMap();
		Assert.assertNotNull(map);
		Assert.assertEquals(map.get("MUSIC"),"true:0.0");
		Assert.assertEquals(map.get("BOOK"),"true:0.0");
		Assert.assertEquals(map.get("DISCOUNT"),"17.5");
		Assert.assertEquals(map.get("PILLS"),"false:0.0");
		Assert.assertEquals(map.get("CD"),"true:1.25");
	}
}
