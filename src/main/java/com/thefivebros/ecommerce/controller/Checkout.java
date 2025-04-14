package com.thefivebros.ecommerce.controller;

import com.thefivebros.ecommerce.model.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart)req.getAttribute("cart");
        double salesTaxRate = 0.07;
        double shippingCost = 0;
        double discountPercent = 0;
        req.setAttribute("pageTitle", "Checkout");
        req.getRequestDispatcher("WEB-INF/ecommerce/checkout.jsp").forward(req, resp);

    }
}
