package com.codingexercise.tax.utility;

public class TaxCalculator {

	public double getTax(double itemPrice , double taxRate) {
		
		double tax = (itemPrice * taxRate);
		
		return tax;
		
	}
	
	public double getRoundedValue(double value) {
		
		double roundedValue = Math.ceil(value * 20.0) / 20.0;
		System.out.println("Value ......" + value);
		System.out.println("Rounded Value ......" + roundedValue);
		return roundedValue;
	}
}
