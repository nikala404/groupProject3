package com.ecommerce.controller;

import com.ecommerce.exception.InsufficientFundsException;
import com.ecommerce.exception.OutOfStockException;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import com.ecommerce.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class UserController {

    private final ProductService productService;
    private final OrderService orderService;
    private final User user;

    public UserController(ProductService productService, OrderService orderService, User user) {
        this.productService = productService;
        this.orderService = orderService;
        this.user = user;
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody Product product) {
        if (product.getStock() <= 0) {
            return ResponseEntity.badRequest().body("Product is out of stock.");
        }
        user.getCart().addProduct(product, 1); // Assuming quantity is 1 for simplicity
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder() {
        try {
            orderService.placeOrder(user, user.getCart()); // Correct method call
            return ResponseEntity.ok("Order placed successfully.");
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds.");
        } catch (OutOfStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is out of stock.");
        }
    }
}
