package com.codingexercise.tax.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingexercise.tax.model.Item;
import com.codingexercise.tax.model.Receipt;
import com.codingexercise.tax.utility.TaxCalculator;

@Controller
@RequestMapping("/tax")
public class TaxController {
	
	private static List<Item> theShoppinglist = new ArrayList<>();
	private List<Receipt> theReceipt;
	private static double salesTaxRate = 0.10;
	private static double importedTaxRate = 0.05;
	TaxCalculator taxCalculator = new TaxCalculator();
	@GetMapping("/shoppinglist")
	public String shoppingList(Model theModel) {
//		Item it1 = new Item("chocolate","Food",false,10.5,1);
//		Item it2 = new Item("textbook","Book",false,100,1);
//		Item it3 = new Item("perfume","others",false,25,1);
//		Item it4 = new Item("perfume","others",true,45,1);
//		
//		theShoppinglist = new ArrayList<>();
//		theShoppinglist.add(it1);
//		theShoppinglist.add(it2);
//		theShoppinglist.add(it3);
//		theShoppinglist.add(it4);
		theModel.addAttribute("shoppinglist",theShoppinglist);
		return "shopping-list";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Item theItem = new Item();
		theModel.addAttribute("item", theItem);
		return "items-add";
		
	}
	
	@PostMapping("/save")
	public String saveItems(@ModelAttribute("item") Item theItem) {
		theShoppinglist.add(theItem);
		return "success";
		
	}
	
	@GetMapping("/receipt")
	public String calculateCost(Model theModel) {
		double totalsalesTax = 0.0;
		double totalCost = 0.0;
		
		theReceipt = new ArrayList<>();
		for(Item theItem : theShoppinglist) {
			double itemSalesTax = 0.0;
			double importedTax = 0.0;
			double itemTotalPrice = 0.0;
			
			double itemPrice = theItem.getPrice();
			
			System.out.println("Item price-------- " + itemPrice);
			
			Receipt receipt = new Receipt();
			
			if(theItem.getImported().equals("Yes")){
				importedTax = taxCalculator.getTax(itemPrice , importedTaxRate);
				//importedTax = (itemPrice * importedTaxRate);
			}
			
			if(theItem.getCategory().equals("Others")) {
			//	itemSalesTax = (itemPrice * salesTaxRate);
				itemSalesTax = taxCalculator.getTax(itemPrice, salesTaxRate);
			}
			
			totalsalesTax = totalsalesTax + itemSalesTax + importedTax;
			
			
			itemTotalPrice = ( itemPrice * theItem.getQuantity()) + itemSalesTax + importedTax;
			
			
			totalCost = totalCost +itemTotalPrice;
			
			
			receipt.setItemName(theItem.getItemName());
			receipt.setImported(theItem.getImported());
			receipt.setPrice(taxCalculator.getRoundedValue(itemTotalPrice));
			receipt.setQuantity(theItem.getQuantity());
			
			System.out.println("itemTotalPrice...."+taxCalculator.getRoundedValue(itemTotalPrice));

			System.out.println("importedTax-------- " + importedTax);
			System.out.println(" item salesTax-------- " + itemSalesTax);
			System.out.println("totalCost-------- " + totalCost);
			
			theReceipt.add(receipt);
		}
		theModel.addAttribute("totalSalesTax", taxCalculator.getRoundedValue(totalsalesTax));
		theModel.addAttribute("totalCost", taxCalculator.getRoundedValue(totalCost));
		theModel.addAttribute("receipt", theReceipt);
		return "receipt";
		
	}
	
	@GetMapping("/newCustomer")
	public String addNewCustomer(Model theModel){
		theShoppinglist = new ArrayList<>();
		Item theItem = new Item();
		theModel.addAttribute("item", theItem);
		return "items-add";
	
		
	}
}
