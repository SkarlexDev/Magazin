package com.shop.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.services.CategoryService;
import com.shop.services.ProductService;
import com.shop.struct.Product;

@Controller
public class AdminController {

	private final ProductService productService;

	private final CategoryService categoryService;

	public AdminController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping("/admin")
	public String adminPanel(Model model) {
		model.addAttribute("categorys", categoryService.getAll());
		model.addAttribute("products", productService.getAll());
		model.addAttribute("pageTitle", "Admin");
		return "admin";
	}

	@GetMapping("/admin/product/new")
	public String newProduct(Model model) {
		model.addAttribute("categorys", categoryService.getAll());
		model.addAttribute("productInfo", new Product());
		model.addAttribute("method", "new");
		model.addAttribute("pageTitle", "New product");
		return "product";
	}

	@PostMapping("/admin/product/new")
	public String newProduct(@ModelAttribute Product productInfo, Model model) throws Exception {
		productInfo.setProductlink(productInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "")
				.replaceAll(",", "").replaceAll("/", "."));

		Optional<Product> product = productService.getByProductLink(productInfo.getProductlink());
		if (!product.isPresent()) {
			productService.saveProduct(productInfo);
			return "redirect:/admin";
		} else {
			model.addAttribute("productInfo", productInfo);
			model.addAttribute("categorys", categoryService.getAll());
			model.addAttribute("method", "new");
			model.addAttribute("error", "true");
			model.addAttribute("pageTitle", "New product");
			return "product";
		}

	}

	@GetMapping("admin/product/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		Optional<Product> product = productService.getById(id);
		if (product.isPresent()) {
			model.addAttribute("categorys", categoryService.getAll());
			model.addAttribute("productInfo", product.get());
			model.addAttribute("method", "edit");
			model.addAttribute("pageTitle", "Edit");
		}
		return "product";
	}

	@PostMapping("admin/product/edit/{id}")
	public String postEditProduct(@PathVariable Long id, Model model,
			@ModelAttribute("productInfo") Product productInfo) {
		try {
			productInfo.setProductlink(productInfo.getProductName().replaceAll(" ", "_").replaceAll("-", "")
					.replaceAll(",", "").replaceAll("/", "."));
			productService.saveProduct(productInfo);
			return "redirect:/admin";
		} catch (Exception e) {
			Optional<Product> product = productService.getById(id);
			if (product.isPresent()) {
				model.addAttribute("productInfo", product.get());
				model.addAttribute("method", "edit");
				model.addAttribute("pageTitle", "Edit");
				model.addAttribute("error", "true");
			}
			return "product";
		}
	}

	@GetMapping("admin/product/disable/d/{id}")
	public String disableProduct(@PathVariable Long id) {
		productService.disableProduct(id);
		return "redirect:/admin";
	}

	@GetMapping("admin/product/enable/e/{id}")
	public String enableProduct(@PathVariable Long id) {
		productService.enableProduct(id);
		return "redirect:/admin";
	}

	@PostMapping("admin/category/new/{name}")
	public ResponseEntity<Void> newCategory(@PathVariable String name) {
		if (categoryService.getByName(name) != null) {
			return ResponseEntity.badRequest().build();
		}

		categoryService.saveCategory(name);
		return ResponseEntity.ok().build();
	}

	@GetMapping("admin/category/delete/{id}")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin";

	}

}
