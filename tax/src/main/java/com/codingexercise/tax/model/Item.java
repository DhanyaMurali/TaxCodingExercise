package com.codingexercise.tax.model;

public class Item {
	
	private String itemName;
	private String category;
	private String imported;
	private double price;
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
