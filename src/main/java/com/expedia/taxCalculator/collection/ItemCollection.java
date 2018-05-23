package com.expedia.taxCalculator.collection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expedia.taxCalculator.Item.Item;
import com.expedia.taxCalculator.Item.ItemType;
import com.expedia.taxCalculator.config.ItemConfig;
import com.expedia.taxCalculator.constants.CalculatorConstants;
import com.expedia.taxCalculator.facade.ItemFacade;

@Service
public class ItemCollection {

	@Autowired
	private ItemConfig config;

	private Map<String, String> configMap;


	@PostConstruct
	public void init() {
		configMap = config.initConnReadTimeoutMap();
	}

	/**Adds Items to list supplied in parameter
	 * @param s
	 * @param list
	 */
	public void addToList(String s,List<Item> list) {
		try {
		String s1 = s.split(":")[0];
		int quantity = Integer.parseInt(s1.split(" ")[0].trim());
		ItemType type = ItemType.fromString(s1.split(" ")[1].trim());
		double price = Double.parseDouble(s.split(":")[1].trim());
		Item item = ItemFacade.getItem(type);
		item.setQuantity(quantity);
		item.setPrice(new BigDecimal(price));

		boolean taxEligible = Boolean.parseBoolean(configMap.get(type.name()).split(":")[0]);
		BigDecimal additionalPrice = configMap.get(type.name()).split(":").length>=2?new BigDecimal(Double.parseDouble(configMap.get(type.name()).split(":")[1])):new BigDecimal("0.0");
		double taxPercentage=configMap.get(type.name()).split(":").length>=3?Double.parseDouble(configMap.get(type.name()).split(":")[2]):Double.parseDouble(configMap.get(CalculatorConstants.DISCOUNT));
		item.setAdditionalAmount(additionalPrice);
		item.setTaxEligible(taxEligible);
		item.setTaxPercentage(taxPercentage);
		list.add(item);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
