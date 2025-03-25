package com.thefivebros.ecommerce.model;

import java.time.Instant;
import java.util.Date;

public class Order {
    private int orderNum;
    private Instant orderDate;
    private String customerId;
    private String customerName;

    public Order() {
    }

    public Order(int orderNum, Instant orderDate, String customerId, String customerName) {
        this.orderNum = orderNum;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public Date getOrderDateDate() {
        return Date.from(orderDate);
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNum=" + orderNum +
                ", orderDate=" + orderDate +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
