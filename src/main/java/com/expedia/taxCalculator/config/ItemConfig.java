package com.expedia.taxCalculator.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.expedia.taxCalculator.Item.ItemType;
import com.expedia.taxCalculator.constants.CalculatorConstants;

@Component
@PropertySource("classpath:itemConfig.properties")
public class ItemConfig {

	@Autowired
	private Environment env;

	/**
	 * Extracts information from property file making it flexible and non hardcoded
	 * and returns a mapping of all items loaded @bean creation in memory
	 * 
	 * @return Map
	 */
	public Map<String, String> initConnReadTimeoutMap() {
		Map<String, String> connReadTimeMap;
		String value = null;
		connReadTimeMap = new HashMap<String, String>();
		for (ItemType enumAPiName : ItemType.values()) {

			try {

				value = env.getProperty(CalculatorConstants.PREFIX + enumAPiName.name() + CalculatorConstants.SUFFIX);
				if (Objects.nonNull(value)) {
					connReadTimeMap.put(enumAPiName.name(), value);
				}
				;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		connReadTimeMap.put(CalculatorConstants.DISCOUNT,
				env.getProperty(CalculatorConstants.PREFIX + CalculatorConstants.TAX_PERCENTAGE));
		return connReadTimeMap;

	}
}
