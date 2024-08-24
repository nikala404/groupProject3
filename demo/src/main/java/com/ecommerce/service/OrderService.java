package com.ecommerce.service;

import com.ecommerce.exception.InsufficientFundsException;
import com.ecommerce.exception.OutOfStockException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public void placeOrder(User user, Cart cart) throws InsufficientFundsException, OutOfStockException {
        double totalCost = 0;
        for (Product product : cart.getProducts().keySet()) {
            int quantity = cart.getProducts().get(product);
            if (product.getStock() < quantity) {
                throw new OutOfStockException("Product " + product.getName() + " is out of stock.");
            }
            totalCost += product.getPrice() * quantity;
        }
        if (user.getBudget() < totalCost) {
            throw new InsufficientFundsException("User has insufficient funds.");
        }
        user.setBudget(user.getBudget() - totalCost);
        
        for (Product product : cart.getProducts().keySet()) {
            int quantity = cart.getProducts().get(product);
            product.setStock(product.getStock() - quantity);
        }

        cart.clear();
    }

    public Order placeOrder(Order order) {

        return order;
    }
}
