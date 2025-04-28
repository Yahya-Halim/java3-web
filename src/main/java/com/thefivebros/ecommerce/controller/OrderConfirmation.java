package com.thefivebros.ecommerce.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/order-confirmation")
public class OrderConfirmation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer newOrderId = (Integer)session.getAttribute("newOrderId");
        if(newOrderId != null) {
            req.setAttribute("pageTitle", "Order Confirmation");
            req.getRequestDispatcher("WEB-INF/ecommerce/order-confirmation.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/cart"));
        }
    }
}
