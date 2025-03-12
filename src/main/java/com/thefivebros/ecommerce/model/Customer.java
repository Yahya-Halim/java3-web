package com.thefivebros.ecommerce.model;

public class Customer {
    private String cust_id;
    private String cust_name;
    private String cust_email;
    private String cust_contact;
    private String cust_address;
    private String cust_city;
    private String cust_state;
    private String cust_zip;
    private String cust_country;


    public Customer(String cust_id, String cust_name, String cust_email, String cust_contact, String cust_address, String cust_city, String cust_state, String cust_zip, String cust_country) {
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.cust_email = cust_email;
        this.cust_contact = cust_contact;
        this.cust_address = cust_address;
        this.cust_city = cust_city;
        this.cust_state = cust_state;
        this.cust_zip = cust_zip;
        this.cust_country = cust_country;
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

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_contact() {
        return cust_contact;
    }

    public void setCust_contact(String cust_contact) {
        this.cust_contact = cust_contact;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCust_city() {
        return cust_city;
    }

    public void setCust_city(String cust_city) {
        this.cust_city = cust_city;
    }

    public String getCust_state() {
        return cust_state;
    }

    public void setCust_state(String cust_state) {
        this.cust_state = cust_state;
    }

    public String getCust_zip() {
        return cust_zip;
    }

    public void setCust_zip(String cust_zip) {
        this.cust_zip = cust_zip;
    }

    public String getCust_country() {
        return cust_country;
    }

    public void setCust_country(String cust_country) {
        this.cust_country = cust_country;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id='" + cust_id + '\'' +
                ", cust_name='" + cust_name + '\'' +
                ", cust_email='" + cust_email + '\'' +
                ", cust_contact='" + cust_contact + '\'' +
                ", cust_address='" + cust_address + '\'' +
                ", cust_city='" + cust_city + '\'' +
                ", cust_state='" + cust_state + '\'' +
                ", cust_zip='" + cust_zip + '\'' +
                ", cust_country='" + cust_country + '\'' +
                '}';
    }
}
