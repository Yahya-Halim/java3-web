package com.thefivebros.ecommerce.controller;


import com.thefivebros.ecommerce.model.Address;
import com.thefivebros.ecommerce.model.Vendor;
import com.thefivebros.ecommerce.model.VendorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value="/update-vendor")
public class AdminUpdateVendor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("id", id);
        Vendor vendor = VendorDAO.getVendor(id);
        req.setAttribute("vendor", vendor);
        req.getRequestDispatcher("WEB-INF/ecommerce/admin-update-vendor.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id"); // Vendor id from the hidden field
        req.setAttribute("id", id);
        String vend_id = req.getParameter("vend_id"); // Vendor id from the form
        String vend_name = req.getParameter("vend_name");
        String streetAddress = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");



        Vendor newVendor = new Vendor();
        boolean validationError = false;

        Vendor originalVendor = VendorDAO.getVendor(vend_id);
        // TODO: validate originalVendor



        try {
            newVendor.setVend_name(vend_name);
            req.setAttribute("vendorNameError", false);
            req.setAttribute("vendorNameMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("vendorNameError", true);
            req.setAttribute("vendorNameMessage", e.getMessage());
        }

        Address address = new Address();

        try {
            address.setCountry(country);
            req.setAttribute("countryError", false);
            req.setAttribute("countryMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("countryError", true);
            req.setAttribute("countryMessage", e.getMessage());
        }

        try {
            address.setAddress(streetAddress);
            req.setAttribute("streetAddressError", false);
            req.setAttribute("streetAddressMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("streetAddressError", true);
            req.setAttribute("streetAddressMessage", e.getMessage());
        }

        try {
            address.setZip(zip);
            if(address.getCountry() != null) {
                req.setAttribute("zipError", false);
                req.setAttribute("zipMessage", "Looks good!");
            }
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("zipError", true);
            req.setAttribute("zipMessage", e.getMessage());
        }

        try {
            address.setCity(city);
            if(address.getCountry() != null) {
                req.setAttribute("cityError", false);
                req.setAttribute("cityMessage", "Looks good!");
            }
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("cityError", true);
            req.setAttribute("cityMessage", e.getMessage());
        }

        try {
            address.setState(state);
            if(address.isUnitedStates()) {
                req.setAttribute("stateError", false);
                req.setAttribute("stateMessage", "Looks good!");
            }
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("stateError", true);
            req.setAttribute("stateMessage", e.getMessage());
        }

        newVendor.setAddress(address);

        if(!validationError) {
            boolean vendorUpdated = VendorDAO.updateVendor(originalVendor, newVendor);
            req.setAttribute("vendorUpdated", vendorUpdated);
            if(vendorUpdated) {
                req.setAttribute("vendorUpdatedMessage", "Successfully updated vendor!");
            } else {
                req.setAttribute("vendorUpdatedMessage", "Error updating vendor.");
            }
        }
        req.setAttribute("vend_id", vend_id);

        req.setAttribute("vendor", newVendor);

        req.getRequestDispatcher("WEB-INF/ecommerce/admin-update-vendor.jsp").forward(req, resp);
    }


}
