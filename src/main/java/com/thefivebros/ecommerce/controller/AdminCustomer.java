package com.thefivebros.ecommerce.controller;


import com.thefivebros.ecommerce.model.Customer;
import com.thefivebros.ecommerce.model.CustomerDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/customers")
public class AdminCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        List<Customer> customers = CustomerDAO.getCustomersAdmin();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("WEB-INF/ecommerce/admin-customers.jsp").forward(req, resp);
    }

}
