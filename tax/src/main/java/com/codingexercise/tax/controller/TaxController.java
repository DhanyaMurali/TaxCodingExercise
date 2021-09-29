package com.codingexercise.tax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	//To view all the items to be purchased
	
	@GetMapping("/shoppinglist")
	public String shoppingList(Model theModel) {
		theModel.addAttribute("shoppinglist", theShoppinglist);
		return "shopping-list";
	}
	
	//Mapping to adding the items detail
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Item theItem = new Item();
		theModel.addAttribute("item", theItem);
		return "items-add";

	}
	
	//To save all the item details to the shopping list
	
	@PostMapping("/save")
	public String saveItems(@Valid @ModelAttribute("item") Item theItem,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {       
	         
	        return "items-add";
	    } else {
	    	theShoppinglist.add(theItem);
	        return "success";
	    }


	}

	@GetMapping("/receipt")
	public String calculateCost(Model theModel) {
		double totalsalesTax = 0.0;
		double totalCost = 0.0;

		theReceipt = new ArrayList<>();
		for (Item theItem : theShoppinglist) {
			double itemSalesTax = 0.0;
			double importedTax = 0.0;
			double itemTotalPrice = 0.0;
			double itemPrice = theItem.getPrice();

			Receipt receipt = new Receipt();

			if (theItem.getImported().equals("Yes")) {

				importedTax = taxCalculator.getTax(itemPrice, importedTaxRate);

			}

			if (theItem.getCategory().equals("Others")) {

				itemSalesTax = taxCalculator.getTax(itemPrice, salesTaxRate);
			}

			totalsalesTax = totalsalesTax + itemSalesTax + importedTax;

			itemTotalPrice = (itemPrice * theItem.getQuantity()) + itemSalesTax + importedTax;

			totalCost = totalCost + itemTotalPrice;

			// Set output object with the required values

			receipt.setItemName(theItem.getItemName());
			receipt.setImported(theItem.getImported());
			receipt.setPrice((String.format("%.2f", itemTotalPrice)));
			receipt.setQuantity(theItem.getQuantity());

			theReceipt.add(receipt);
		}

		// Add values to the model attribute to display in the view page

		theModel.addAttribute("totalSalesTax", String.format("%.2f", totalsalesTax));
		theModel.addAttribute("totalCost", String.format("%.2f", totalCost));
		theModel.addAttribute("receipt", theReceipt);
		return "receipt";

	}

	// For a new customer,the shopping list is reset to a new list
	@GetMapping("/newCustomer")
	public String addNewCustomer(Model theModel) {
		theShoppinglist = new ArrayList<>();
		Item theItem = new Item();
		theModel.addAttribute("item", theItem);
		return "items-add";

	}
}
