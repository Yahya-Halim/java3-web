package com.thefivebros.ecommerce.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderID;
    private Instant orderDate;
    private String shipFirstName;
    private String shipLastName;
    private String shipEmail;
    private String shipAddress;
    private String shipCity;
    private String shipState;
    private String shipZipCode;
    private ArrayList<OrderItem> items;

    // Add shipping, discounts, and taxes

    public Order() {}

    public Order(int orderID, Instant orderDate, String shipFirstName, String shipLastName, String shipEmail, String shipAddress, String shipCity, String shipState, String shipZipCode, ArrayList<OrderItem> items) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.shipFirstName = shipFirstName;
        this.shipLastName = shipLastName;
        this.shipEmail = shipEmail;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipState = shipState;
        this.shipZipCode = shipZipCode;
        this.items = items;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public String getShipFirstName() {
        return shipFirstName;
    }

    public void setShipFirstName(String shipFirstName) {
        this.shipFirstName = shipFirstName;
    }

    public String getShipLastName() {
        return shipLastName;
    }

    public void setShipLastName(String shipLastName) {
        this.shipLastName = shipLastName;
    }

    public String getShipEmail() {
        return shipEmail;
    }

    public void setShipEmail(String shipEmail) {
        this.shipEmail = shipEmail;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipZipCode() {
        return shipZipCode;
    }

    public void setShipZipCode(String shipZipCode) {
        this.shipZipCode = shipZipCode;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderDate=" + orderDate +
                ", shipFirstName='" + shipFirstName + '\'' +
                ", shipLastName='" + shipLastName + '\'' +
                ", shipEmail='" + shipEmail + '\'' +
                ", shipAddress='" + shipAddress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipState='" + shipState + '\'' +
                ", shipZipCode='" + shipZipCode + '\'' +
                ", items=" + items +
                '}';
    }
}
