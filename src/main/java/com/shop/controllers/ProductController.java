package com.shop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.services.ProductService;
import com.shop.struct.Product;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	Product product;
	
	
	@GetMapping("/admin")
	public String adminPanel(Model model) {
		List<Product> products = productService.findAll();		
		model.addAttribute("products", products);
		model.addAttribute("pageTitle", "Admin");
		return "admin";
	}
		
	@GetMapping("/admin/product/new")
	public String newtproduct(Model model) {
		model.addAttribute("productInfo", new Product());
		model.addAttribute("method", "new");
		model.addAttribute("pageTitle", "New product");
		return "product";
	}
	@PostMapping("/admin/product/new")
	public String postproduct(@ModelAttribute Product productInfo , Model model) throws Exception {
		try {
			boolean allowpost = true;
			productInfo.setProductlink(productInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "").replaceAll("/", "."));
			List<Product> products = new ArrayList<Product>();
			products.addAll(productService.findAll());
			for(Product product:products) {
				if(product.getProductlink().equals(productInfo.getProductlink())) {
					allowpost = false;
				}
			}
			System.out.println(allowpost);
			
			if(allowpost){
				productService.addproduct(productInfo);
				return "redirect:/admin";
			}else {
				throw new Exception();
			}			
			
		} catch (Exception e) {
			model.addAttribute("productInfo", new Product());
			model.addAttribute("method", "new");
			model.addAttribute("error","true");
			model.addAttribute("pageTitle", "New product");
			return "product";
		}
		
	}
	
	@GetMapping("admin/product/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		Optional<Product> productCall =productService.findById(id);
		Product product = productCall.get();
		System.out.println(product);
		model.addAttribute("productInfo", product);
		model.addAttribute("method", "edit");
		model.addAttribute("pageTitle", "Edit");
		return "product";
	}
	
	@PostMapping("admin/product/edit/{id}")
	public String postEditProduct(@PathVariable Long id, Model model, @ModelAttribute("productInfo") Product productInfo) {
		try {
			productInfo.setProductlink(productInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "").replaceAll(",", "").replaceAll("/", "."));
			productService.editproduct(productInfo);
			return "redirect:/admin";			
		} catch (Exception e) {
			Optional<Product> productCall =productService.findById(id);
			Product product = productCall.get();
			System.out.println(product);
			model.addAttribute("productInfo", product);
			model.addAttribute("method", "edit");
			model.addAttribute("pageTitle", "Edit");
			model.addAttribute("error","true");
			return "product";
		}
	}
	
	@GetMapping("admin/product/delete/{id}")
	public String deleteproduct(@PathVariable Long id){
		productService.deleteproduct(id);
		return "redirect:/admin";
	}
	
}
