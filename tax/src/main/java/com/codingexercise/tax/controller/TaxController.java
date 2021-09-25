package com.codingexercise.tax.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingexercise.tax.model.Item;

@Controller
@RequestMapping("/tax")
public class TaxController {
	
	private List<Item> theShoppinglist;
	
	@GetMapping("/shoppinglist")
	public String shoppingList(Model theModel) {
		Item it1 = new Item("chocolate","Food",false,10.5,1);
		Item it2 = new Item("textbook","Book",false,100,1);
		Item it3 = new Item("perfume","others",false,25,1);
		Item it4 = new Item("perfume","others",true,45,1);
		
		theShoppinglist = new ArrayList<>();
		theShoppinglist.add(it1);
		theShoppinglist.add(it2);
		theShoppinglist.add(it3);
		theShoppinglist.add(it4);
		theModel.addAttribute("shoppinglist",theShoppinglist);
		return "shopping-list";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Item theItem = new Item();
		theModel.addAttribute("item", theItem);
		return "items-add";
		
	}

}
