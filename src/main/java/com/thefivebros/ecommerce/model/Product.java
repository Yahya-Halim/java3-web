package com.thefivebros.ecommerce.model;

public class Product {
    private String prod_id;
    private String prod_name;
    private double prod_price;
    private String prod_desc;
    private String vend_id;
    private String vend_name;

    public Product(String prod_id, String prod_name, double prod_price, String prod_desc, String vend_id, String vend_name) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.prod_desc = prod_desc;
        this.vend_id = vend_id;
        this.vend_name = vend_name;
    }

    public Product(String prod_id, String prod_name, double prod_price, String prod_desc) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.prod_desc = prod_desc;


    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getVend_id() {
        return vend_id;
    }

    public void setVend_id(String vend_id) {
        this.vend_id = vend_id;
    }

    public String getVend_name() {
        return vend_name;
    }

    public void setVend_name(String vend_name) {
        this.vend_name = vend_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public double getProd_price() {
        return prod_price;
    }

    public void setProd_price(double prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prod_id='" + prod_id + '\'' +
                ", prod_name='" + prod_name + '\'' +
                ", prod_price=" + prod_price +
                ", prod_desc='" + prod_desc + '\'' +
                '}';
    }
}
