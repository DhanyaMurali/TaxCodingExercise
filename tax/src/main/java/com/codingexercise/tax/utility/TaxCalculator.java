package com.codingexercise.tax.utility;


public class TaxCalculator {

	//To calculate the tax amount and round it to the nearest 0.05
	
	public double getTax(double itemPrice , double taxRate) {
		
		double tax = (itemPrice * taxRate);
		double roundedTaxAmount = Math.ceil(tax * 20.0) / 20.0;
		return roundedTaxAmount;
		
	}
	
}