package com.expedia.taxCalculator.service;

import java.util.List;

import com.expedia.taxCalculator.Item.Item;
import com.expedia.taxCalculator.responseModel.ResponseModel;

public interface CalculatorService {

	/**
	 * @param items
	 * @return
	 */
	public ResponseModel fetchTaxCalculation(List<Item> items);
}
