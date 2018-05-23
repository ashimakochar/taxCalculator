package com.expedia.taxCalculator.Item;

import java.math.BigDecimal;

public abstract class Item {
	
	private BigDecimal price;
	private boolean taxEligible;
	private BigDecimal additionalAmount;
	private ItemType type;
	private double taxPercentage=17.5;
	private int quantity;
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public boolean isTaxEligible() {
		return taxEligible;
	}
	public void setTaxEligible(boolean taxEligible) {
		this.taxEligible = taxEligible;
	}
	public ItemType getType() {
		return type;
	}
	public void setType(ItemType type) {
		this.type = type;
	}
	
	public BigDecimal getAdditionalAmount() {
		return additionalAmount;
	}
	public void setAdditionalAmount(BigDecimal additionalAmount) {
		this.additionalAmount = additionalAmount;
	}

}
