package com.ecommerce.service;

import com.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(product.getName())) {
                products.set(i, product);
                break;
            }
        }
        return product;
    }

    public boolean deleteProduct(String productName) {
        return products.removeIf(product -> product.getName().equals(productName));
    }


    public List<Product> getProducts() {
        return products;
    }
}
