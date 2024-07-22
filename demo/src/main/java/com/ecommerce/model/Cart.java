package com.ecommerce.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }
}
