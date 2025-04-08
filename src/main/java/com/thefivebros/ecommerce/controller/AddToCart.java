package com.thefivebros.ecommerce.controller;

import com.thefivebros.ecommerce.model.Product;
import com.thefivebros.ecommerce.model.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-to-cart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prodId = req.getParameter("prod_id");
        String quantityStr = req.getParameter("qty");

        boolean errorFound = false;
        String errorMsg = "";
        Product product = ProductDAO.getProduct(prodId);
        if(product == null) {
            errorFound = true;
            errorMsg = "Product not found";
        }

        int quantity = 1;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            errorFound = true;
            errorMsg = "Invalid quantity";
        }
        if(quantity < 1) {
            errorFound = true;
            errorMsg = "Quantity must be 1 or more";
        }

        HttpSession session = req.getSession();
        if(errorFound) {
            session.setAttribute("flashMessageDanger", errorMsg);
        } else {
            session.setAttribute("flashMessageSuccess", "Added to cart successfully");
        }
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/shop"));
    }
}
