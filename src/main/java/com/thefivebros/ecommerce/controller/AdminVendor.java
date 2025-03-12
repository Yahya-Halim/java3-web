package com.thefivebros.ecommerce.controller;



import com.thefivebros.ecommerce.model.Vendor;
import com.thefivebros.ecommerce.model.VendorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/vendors")
public class AdminVendor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Vendor> vendors = VendorDAO.getVendorsAdmin();
        req.setAttribute("vendors", vendors);
        req.getRequestDispatcher("WEB-INF/ecommerce/admin-vendors.jsp").forward(req, resp);
    }
}
