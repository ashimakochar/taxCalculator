package com.expedia.taxCalculator.Item;

public enum ItemType {
	BOOK("book"),MUSIC("music"),PILLS("pills"),SNACK("snack"),CD("cd"),PINS("pins"),WINE("wine");
	private String name;
	private ItemType(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public static ItemType fromString(String name) {
		for(ItemType type:ItemType.values())
		{
			if(type.getName().equalsIgnoreCase(name))
			{
				return type;
			}
			
		}
		return null;
	}
}
