package com.codingexercise.tax.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Item {
	
	@NotNull(message = "Please enter item name")
    @NotEmpty(message = "Please enter item name")
	private String itemName;
	
	@NotNull
	private String category;
	
	@NotNull
	private String imported;
	
	@NotNull(message = "Item Price cannot be null!!")
	@DecimalMin(value = "0.01",message="Item Price should be greater than or equal to 0.1")
	private double price;
	
	@NotNull(message = "Quantity cannot be null!!")
	@Min(value = 1,message = "Quantity should be greater than or equal to 1" )
	private int quantity;
	
	public Item() {
		
	}

	public Item(String itemName, String category, String imported, double price, int quantity) {
		super();
		this.itemName = itemName;
		this.category = category;
		this.imported = imported;
		this.price = price;
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	public String getImported() {
		return imported;
	}

	public void setImported(String imported) {
		this.imported = imported;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", category=" + category + ", imported=" + imported + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
}
