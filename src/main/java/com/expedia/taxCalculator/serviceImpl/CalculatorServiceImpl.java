package com.expedia.taxCalculator.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.expedia.taxCalculator.Item.Item;
import com.expedia.taxCalculator.responseModel.ResponseModel;
import com.expedia.taxCalculator.service.CalculatorService;
import com.expedia.taxCalculator.utilities.Utilities;

/**
 * @author MMT5987
 *
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

	/*
	 * (non-Javadoc)
	 * Returns response  Model
	 * @see
	 * com.expedia.taxCalculator.service.CalculatorService#fetchTaxCalculation(java.
	 * util.List)
	 */
	@Override
	public ResponseModel fetchTaxCalculation(List<Item> items) {
		ResponseModel response = new ResponseModel();
		items.stream().forEach(item -> {
			getTaxedPriced(item, response);
		});
		response.setItems(items);
		return response;
	}

	/**Adds data to ResponseModel based on Items supplied
	 * @param item
	 * @param model
	 */
	private void getTaxedPriced(Item item, ResponseModel model) {
		BigDecimal originalPrice = item.getPrice();
		BigDecimal price = getTaxPlusPrice(item);
		model.setSalesTax(Utilities.roundingOff(
				Objects.nonNull(model.getSalesTax()) ? model.getSalesTax().add(price.subtract(originalPrice))
						: price.subtract(originalPrice)));
		model.setTotalPrice(Objects.nonNull(model.getTotalPrice()) ? model.getTotalPrice().add(price) : price);
		item.setPrice(price);
	}

	/**
	 * Calculates taxes based on upper 0.05 rounding though this does not match
	 * expected output but nearby with an error of 1.5
	 * 
	 * @param item
	 * @return
	 */
	private BigDecimal getTaxPlusPrice(Item item) {
		BigDecimal originalPrice = item.getPrice();
		BigDecimal quantitativePrice = originalPrice.multiply(new BigDecimal(item.getQuantity()));
		BigDecimal price = item.isTaxEligible() ? quantitativePrice.multiply(new BigDecimal(item.getTaxPercentage()))
				.divide(new BigDecimal(100)).add(quantitativePrice) : quantitativePrice;
		price = price.add(item.getAdditionalAmount());
		price = Utilities.roundingOff(price);
		return price;
	}

}
