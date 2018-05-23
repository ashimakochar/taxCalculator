package com.expedia.taxCalculator.facade;


import com.expedia.taxCalculator.Item.Item;
import com.expedia.taxCalculator.Item.ItemType;
import com.expedia.taxCalculator.Item.SubType.Books;
import com.expedia.taxCalculator.Item.SubType.CD;
import com.expedia.taxCalculator.Item.SubType.Music;
import com.expedia.taxCalculator.Item.SubType.Pills;
import com.expedia.taxCalculator.Item.SubType.Pins;
import com.expedia.taxCalculator.Item.SubType.Snack;
import com.expedia.taxCalculator.Item.SubType.Wine;

public class ItemFacade {

	/**
	 * Returns Items Subtypes based on the type specified
	 * @param type
	 * @return
	 */
	public static Item getItem(ItemType type)
	{
		switch(type) {
		case BOOK:
			return new Books();
		case CD:
			return new CD();
		case SNACK:
			return new Snack();
		case PILLS:
			return new Pills();
		case PINS:
			return new Pins();
		case WINE:
			return new Wine();
		case MUSIC:
			return new Music();
		default:
			return null;
		}
	}
}
