package com.thefivebros.ecommerce.controller;



import com.thefivebros.ecommerce.model.Address;
import com.thefivebros.ecommerce.model.Vendor;
import com.thefivebros.ecommerce.model.VendorDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-vendor")
public class AdminAddVendor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.getRequestDispatcher("WEB-INF/ecommerce/admin-add-vendor.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String vendorId = req.getParameter("vendorId");
        String vendorName = req.getParameter("vendorName");
        String streetAddress = req.getParameter("streetAddress");
        String zip = req.getParameter("zip");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        req.setAttribute("vendorId", vendorId);
        req.setAttribute("vendorName", vendorName);
        req.setAttribute("streetAddress", streetAddress);
        req.setAttribute("zip", zip);
        req.setAttribute("city", city);
        req.setAttribute("state", state);
        req.setAttribute("country", country);

        Vendor vendor = new Vendor();
        boolean validationError = false;

        Vendor vendorFromDB = VendorDAO.getVendor(vendorId);
        if (vendorFromDB!= null) {
            validationError = true;
            req.setAttribute("vendorIdError", true);
            req.setAttribute("vendorIdMessage", "Vendor ID already exists.");
        }else {
            try {
                vendor.setVend_id(vendorId);
                req.setAttribute("vendorIdError", false);
                req.setAttribute("vendorIdMessage", "Looks good!");
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("vendorIdError", true);
                req.setAttribute("vendorIdMessage", e.getMessage());
            }
        }

        try {
            vendor.setVend_name(vendorName);
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

        vendor.setAddress(address);

        if(!validationError) {
            boolean vendorAdded = VendorDAO.addVendor(vendor);
            req.setAttribute("vendorAdded", vendorAdded);
            if(vendorAdded) {
                req.setAttribute("vendorAddedMessage", "Successfully added vendor!");
            } else {
                req.setAttribute("vendorAddedMessage", "Error adding vendor.");
            }
        }

        req.getRequestDispatcher("WEB-INF/ecommerce/admin-add-vendor.jsp").forward(req, resp);
    }
}

