package com.thefivebros.ecommerce.model;

import java.time.Instant;

public class Order {
    // order_num, order_date, orders.cust_id, cust_name
    private int order_num;
    private Instant order_date;
    private String cust_id;
    private String cust_name;

    public Order(int order_num, Instant order_date, String cust_id, String cust_name) {
        this.order_num = order_num;
        this.order_date = order_date;
        this.cust_id = cust_id;
        this.cust_name = cust_name;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public Instant getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Instant order_date) {
        this.order_date = order_date;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_num=" + order_num +
                ", order_date=" + order_date +
                ", cust_id='" + cust_id + '\'' +
                ", cust_name='" + cust_name + '\'' +
                '}';
    }
}
