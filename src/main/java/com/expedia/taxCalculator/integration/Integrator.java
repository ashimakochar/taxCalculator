package com.expedia.taxCalculator.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.expedia.taxCalculator.Item.Item;
import com.expedia.taxCalculator.collection.ItemCollection;
import com.expedia.taxCalculator.responseModel.ResponseModel;
import com.expedia.taxCalculator.service.CalculatorService;

public class Integrator {
	
	@Autowired
	private ItemCollection itemCollection;
	@Autowired
	private CalculatorService calculatorService;
	
	/**
	 * Takes string array to returns list of items after tax applied
	 * @param input
	 * @return
	 */
	public ResponseModel getTaxCalculation(String[] input)
	{
		List<Item> itemList=new ArrayList<>();
		for(String s:input)
		{
			itemCollection.addToList(s,itemList);
		}
		return calculatorService.fetchTaxCalculation(itemList);
	}

}
