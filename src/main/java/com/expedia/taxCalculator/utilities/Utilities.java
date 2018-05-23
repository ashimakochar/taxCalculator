package com.expedia.taxCalculator.utilities;

import java.math.BigDecimal;

import com.expedia.taxCalculator.constants.CalculatorConstants;

public class Utilities {
	
	/**Utility to round off price to upper 0.05
	 * @param price
	 * @return
	 */
	public static BigDecimal roundingOff(BigDecimal price) {
		return price.divide(new BigDecimal(CalculatorConstants.ROUNDING_FACTOR)).setScale(0, BigDecimal.ROUND_UP).multiply(new BigDecimal(CalculatorConstants.ROUNDING_FACTOR));
	}
	

}
