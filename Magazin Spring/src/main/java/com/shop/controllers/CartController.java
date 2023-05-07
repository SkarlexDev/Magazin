package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.services.CartItemService;
import com.shop.services.ProductService;
import com.shop.services.UserService;
import com.shop.struct.CartItem;
import com.shop.struct.Product;
import com.shop.struct.User;

@Controller
public class CartController {

    private final ProductService productService;

    private final CartItemService cartItemService;

    private final UserService userService;

    public CartController(ProductService productService, CartItemService cartItemService, UserService userService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String getCart(Model model, @AuthenticationPrincipal Authentication authentication) {
        Optional<User> user = userService.getLoggedUser(authentication);
        if (user.isPresent()) {
            List<CartItem> productsInCart = cartItemService.getCartItems(user.get());
            model.addAttribute("listCartItems", productsInCart);
            model.addAttribute("totalprice", cartItemService.totalPrice(productsInCart));
        }
        return "cart";
    }

    @PostMapping("/cart/remove/{productID}")
    public String removeFromCart(@PathVariable Long productID,
            @AuthenticationPrincipal Authentication authentication) {
        Optional<User> user = userService.getLoggedUser(authentication);
        Optional<Product> product = productService.getById(productID);
        user.ifPresent(user1 -> product.ifPresent(value -> cartItemService.removeProduct(value, user1)));
        return "redirect:/cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id,
            @AuthenticationPrincipal Authentication authentication) {
        Optional<User> user = userService.getLoggedUser(authentication);
        Optional<Product> product = productService.getById(id);
        user.ifPresent(user1 -> product.ifPresent(value -> cartItemService.addProduct(value, user1)));
        return "redirect:/cart";
    }

    @PostMapping("/cart/removeProduct/{productID}/{quantity}")
    public String removeProduct(@PathVariable Long productID,
            @PathVariable Long quantity,
            @AuthenticationPrincipal Authentication authentication) {
        Optional<User> user = userService.getLoggedUser(authentication);
        Optional<Product> product = productService.getById(productID);
        for (int i = 0; i <= quantity - 1; i++) {
            user.ifPresent(user1 -> product.ifPresent(value -> cartItemService.removeProduct(value, user1)));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(@AuthenticationPrincipal Authentication authentication) {
        Optional<User> user = userService.getLoggedUser(authentication);
        user.ifPresent(cartItemService::checkout);
        return "redirect:/home";
    }
}
