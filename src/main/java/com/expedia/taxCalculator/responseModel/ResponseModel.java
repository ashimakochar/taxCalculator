package com.expedia.taxCalculator.responseModel;

import java.math.BigDecimal;
import java.util.List;

import com.expedia.taxCalculator.Item.Item;

/**
 * Return object 
 *
 */
public class ResponseModel {
	private List<Item> items;
	private BigDecimal salesTax;
	private BigDecimal totalPrice;
	public BigDecimal getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
