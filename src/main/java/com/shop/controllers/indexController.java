package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.items.item;
import com.shop.items.itemService;


@Controller
public class indexController {
	
	@Autowired
	private itemService itemService;
	
		
	@RequestMapping(value="/shop/{category}")
	public String getCategory(@PathVariable String category, Model model) {
		List<item> items = itemService.getAllItemsByCategory(category);
		model.addAttribute("items", items);	
		model.addAttribute("category",category);
		return "index";	
	}
		
	@RequestMapping(value={"/home","/"})
	public String getAllItems(Model model) {		
		List<item> items = itemService.getAllItems();		
		model.addAttribute("items", items);		
		return "index";
	}

}
