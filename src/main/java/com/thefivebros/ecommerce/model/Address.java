package com.thefivebros.ecommerce.model;


import com.thefivebros.shared.Validators;

public class Address {
    private String address;

    private String city;

    private String state;

    private String zip;

    private String country;



    public Address() {

    }

    public Address(String address, String city, String state, String zip, String country) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }


    public String getAddress() {

        return address;

    }



    public void setAddress(String address) {
        if(address == null || address.strip().length() == 0) {
            throw new IllegalArgumentException("Street address is required");
        }

        this.address = address;

    }



    public String getCity() {


        return city;

    }



    public void setCity(String city) {
        if(country != null) {
            if (city == null || city.strip().length() == 0) {
                throw new IllegalArgumentException("City is required");
            }
        }

        this.city = city;

    }



    public String getState() {


        return state;

    }
    public boolean isUnitedStates() {
        return country != null && (country.equals("US") || country.equals("USA"));
    }


    public void setState(String state) {
        if (state != null) {
            state = state.toUpperCase();
        }

        // Require the state only if the country is US or USA
        if (isUnitedStates()) {
            if (state == null || !Validators.isValidState(state)) {
                throw new IllegalArgumentException("Invalid state");
            }
        }

        this.state = state;

    }



    public String getZip() {

        return zip;

    }



    public void setZip(String zip) {
        if(country != null) {
            if (zip == null || !Validators.isValidZip(zip)) {
                throw new IllegalArgumentException("Invalid zip");
            }
        }

        this.zip = zip;

    }



    public String getCountry() {

        return country;

    }



    public void setCountry(String country) {
        country = country.toUpperCase();
        if(country == null || !Validators.isValidCountry(country)) {
            throw new IllegalArgumentException("Invalid country");
        }

        this.country = country;

    }



    @Override

    public String toString() {

        return "Address{" +

                "address='" + address + '\'' +

                ", city='" + city + '\'' +

                ", state='" + state + '\'' +

                ", zip='" + zip + '\'' +

                ", country='" + country + '\'' +

                '}';

    }
}
