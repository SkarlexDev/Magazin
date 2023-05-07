package com.shop.controllers;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.services.CartItemService;
import com.shop.services.CategoryService;
import com.shop.services.ProductService;
import com.shop.services.UserService;
import com.shop.struct.Product;
import com.shop.struct.User;

@Controller
public class HomeController {

    private final ProductService productService;

    private final CartItemService cartItemService;

    private final UserService userService;

    private final CategoryService categoryService;

    public HomeController(ProductService productService, CartItemService cartItemService, UserService userService,
            CategoryService categoryService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping({ "/home", "/" })
    public String getAllProducts(Model model, @AuthenticationPrincipal Authentication authentication) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("categorys", categoryService.getAll());
        return "index";
    }

    @GetMapping("/shop/{category}")
    public String getCategory(@PathVariable String category, Model model,
            @AuthenticationPrincipal Authentication authentication) {
        model.addAttribute("products", productService.getAllByCategory(category));
        model.addAttribute("pageTitle", category);
        model.addAttribute("categorys", categoryService.getAll());
        return "index";
    }

    @GetMapping("/shop/{category}/{productLink}")
    public String productPage(@PathVariable String productLink, Model model) {
        Optional<Product> oProduct = productService.getByProductLink(productLink);
        if (oProduct.isEmpty()) {
            return "error";
        }
        Product product = oProduct.get();
        // replace new lines
        String description = product.getDescription();
        String html = description.replaceAll("\r\n", "<br>");
        product.setDescription(html);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", (product).getProductName());
        return "item";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id,
            @AuthenticationPrincipal Authentication authentication) {
        Optional<User> user = userService.getLoggedUser(authentication);
        Optional<Product> product = productService.getById(id);
        user.ifPresent(user1 -> product.ifPresent(value -> cartItemService.addProduct(value, user1)));
        return "redirect:/home";
    }

}