package com.shop.controllers;

import java.util.ArrayList;
import java.util.List;
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

	@GetMapping("/admin")
	public String adminPanel(Model model) {
		model.addAttribute("products", products());
		model.addAttribute("pageTitle", "Admin");
		model.addAttribute("totalproducts", productService.GET_TOTAL_PRODUCTS());
		model.addAttribute("totalproductsdisabled", productService.GET_TOTAL_PRODUCTS_DISABLED());
		System.out.println(productService.GET_TOTAL_PRODUCTS());
		System.out.println(productService.GET_TOTAL_PRODUCTS_DISABLED());
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
	public String postproduct(@ModelAttribute Product productInfo, Model model) throws Exception {
		try {
			boolean allowpost = true;
			productInfo.setProductlink(productInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "")
					.replaceAll(",", "").replaceAll("/", "."));
			List<Product> products = new ArrayList<Product>();
			products().addAll(productService.getAll());
			for (Product product : products) {
				if (product.getProductlink().equals(productInfo.getProductlink())) {
					allowpost = false;
				}
			}

			if (allowpost) {
				productService.addproduct(productInfo);
				return "redirect:/admin";
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			model.addAttribute("productInfo", new Product());
			model.addAttribute("method", "new");
			model.addAttribute("error", "true");
			model.addAttribute("pageTitle", "New product");
			return "product";
		}

	}

	@GetMapping("admin/product/edit/{id}")
	public String editProduct(@PathVariable int id, Model model) {
		model.addAttribute("productInfo", productId(id));
		model.addAttribute("method", "edit");
		model.addAttribute("pageTitle", "Edit");
		return "product";
	}

	@PostMapping("admin/product/edit/{id}")
	public String postEditProduct(@PathVariable int id, Model model,
			@ModelAttribute("productInfo") Product productInfo) {
		try {
			productInfo.setProductlink(productInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "")
					.replaceAll(",", "").replaceAll("/", "."));
			productService.editproduct(productInfo);
			return "redirect:/admin";
		} catch (Exception e) {
			model.addAttribute("productInfo", productId(id));
			model.addAttribute("method", "edit");
			model.addAttribute("pageTitle", "Edit");
			model.addAttribute("error", "true");
			return "product";
		}
	}

	@GetMapping("admin/product/disable/d/{id}")
	public String disableProduct(@PathVariable int id) {
		productService.disableProduct(id);
		return "redirect:/admin";
	}

	@GetMapping("admin/product/enable/e/{id}")
	public String enableProduct(@PathVariable int id) {
		productService.enableProduct(id);
		return "redirect:/admin";
	}

	//////////////////////////////////////

	public List<Product> products() {
		return productService.getAll();
	}

	public Product productId(int id) {
		return productService.getById(id).get();
	}

}
