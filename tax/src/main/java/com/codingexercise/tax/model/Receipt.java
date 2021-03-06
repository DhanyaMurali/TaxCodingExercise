package com.codingexercise.tax.model;

public class Receipt {
	
	private String itemName;
	private String imported;
	private String price;
	private int quantity;
	
	public Receipt() {
		
	}

	public Receipt(String itemName, String imported, String price, int quantity
			) {
		super();
		this.itemName = itemName;
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

	public String getImported() {
		return imported;
	}

	public void setImported(String imported) {
		this.imported = imported;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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
		return "Receipt [itemName=" + itemName + ", imported=" + imported + ", price=" + price + ", quantity="
				+ quantity + "]";
	}


	
	
}
