package com.ecommerce.model;

public class User {
    private String name;
    private double budget;
    private Cart cart = new Cart();

    public User(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
    public Cart getCart() { return cart; }


    public boolean purchaseProduct(Product product, int quantity) {
        double totalCost = product.getPrice() * quantity;
        if (totalCost <= budget && product.getStock() >= quantity) {
            cart.addProduct(product, quantity);
            budget -= totalCost;
            product.setStock(product.getStock() - quantity);
            return true;
        }
        return false;
    }
}
