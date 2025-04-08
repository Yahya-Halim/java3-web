package com.thefivebros.ecommerce.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> contents;

    public ShoppingCart() {
        contents = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        contents.put(product, contents.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getContents() {
        return contents;
    }

    public int getTotalProductCount() {
        int total = 0;
        // Loop through the Map's values (quantities)
        for(int quantity: contents.values()) {
            total += quantity;
        }
        return total;
    }

    public double getTotalPrice() {
        double total = 0;
        for(Map.Entry<Product, Integer> entry: contents.entrySet()) {

        }
        return total;
    }

    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        Product product1 = ProductDAO.getProduct("DOL001");
        Product product2 = ProductDAO.getProduct("BR02");
        Product product3 = ProductDAO.getProduct("DOL001");
        sc.addProduct(product1, 1);
        sc.addProduct(product2, 2);
        sc.addProduct(product3, 3);
        sc.getContents().entrySet().forEach(item -> {
            System.out.print(item.getKey().getName() + ", ");
            System.out.print(item.getValue() + " x "); // Qty
            System.out.print(item.getKey().getPrice() + " = ");
            System.out.println(item.getValue() * item.getKey().getPrice());
        });
        System.out.println("There are " + sc.getTotalProductCount() + " products in your cart");
    }
}

