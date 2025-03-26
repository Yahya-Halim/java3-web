package com.thefivebros.ecommerce.model;


public class Product {
    private String id;
    private String name;
    private double price;
    private String description;
    private String vendorId;
    private String vendorName;
    private int categoryId;
    private String categoryName;

    public Product() {}

    // This constructor is for products on the shop page
    public Product(String id, String name, double price, String description, int categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    // This constructor is for the admin view of products
    public Product(String id, String name, double price, String description, String vendorId, String vendorName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}