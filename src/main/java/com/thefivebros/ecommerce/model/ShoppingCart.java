package com.thefivebros.ecommerce.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> contents;

    public ShoppingCart() {
        contents = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
        contents.put(product, contents.getOrDefault(product, 0) + quantity);
    }

    public void updateProduct(Product product, int quantity) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
        contents.put(product, quantity);
    }

    public void deleteProduct(Product product) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(contents.containsKey(product)) {
            contents.remove(product);
        }
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
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice();
            total += quantity * price;
        }
        return total;
    }

    @Override
    public String toString() {
        String json = "[";
        for(Map.Entry<Product, Integer> entry: contents.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            json += "{";
            json += String.format("\"prod_id\": \"%s\", \"quantity\": %d, \"price\": %.2f", product.getId(), quantity, product.getPrice() );
            json += "},";
        }
        json = json.substring(0, json.length() - 1) + "]";
        return json;
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
        System.out.println("Your total is " + sc.getTotalPrice());
        System.out.println(sc);
        String[] shippingInfo = new String[]{"Test", "User", "111 First Ave", "Iowa City", "IA", "55555"};
        String email = "test@example.com";
        int newOrderId = OrderDAO.addOrder(shippingInfo, email, sc.toString());
        System.out.println(newOrderId);
    }
}

