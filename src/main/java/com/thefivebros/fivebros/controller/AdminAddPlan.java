package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.Plan;
import com.thefivebros.fivebros.model.PlanDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/add-plan")
public class AdminAddPlan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.getRequestDispatcher("WEB-INF/admin-add-plan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String planId = req.getParameter("planId");
        String planName = req.getParameter("planName");
        String planPriceStr = req.getParameter("planPrice");
        String planDescription = req.getParameter("planDescription");

        req.setAttribute("planId", planId);
        req.setAttribute("planName", planName);
        req.setAttribute("planPrice", planPriceStr);
        req.setAttribute("planDescription", planDescription);

        boolean validationError = false;

        Plan plan = new Plan();

        // Validate Plan ID (check uniqueness)
        Plan existingPlan = PlanDAO.get(plan.getId());
        if (existingPlan != null) {
            validationError = true;
            req.setAttribute("planIdError", true);
            req.setAttribute("planIdMessage", "Plan ID already exists.");
        } else {
            try {
                plan.setId(Integer.parseInt(planId));
                req.setAttribute("planIdError", false);
                req.setAttribute("planIdMessage", "Looks good!");
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("planIdError", true);
                req.setAttribute("planIdMessage", "fill this field");
            }
        }

        if (planName == null || planName.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("planNameError", true);
            req.setAttribute("planNameMessage", "Plan name cannot be empty.");
        } else {
            try {
                plan.setName(planName.trim());
                req.setAttribute("planNameError", false);
                req.setAttribute("planNameMessage", "Looks good!");
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("planNameError", true);
                req.setAttribute("planNameMessage", e.getMessage());
            }
        }

        // Validate and set Plan Price
        try {
            BigDecimal planPrice = new BigDecimal(planPriceStr);
            plan.setPrice(planPrice);
            req.setAttribute("planPriceError", false);
            req.setAttribute("planPriceMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("planPriceError", true);
            req.setAttribute("planPriceMessage", "Invalid price value.");
        }

        // Set Description (optional validation if needed)
        plan.setDescription(planDescription);

        // Add to database if no validation errors
        if (!validationError) {
            boolean planAdded = PlanDAO.add(plan);
            req.setAttribute("planAdded", planAdded);
            if (planAdded) {
                req.setAttribute("planAddedMessage", "Successfully added plan!");
            } else {
                req.setAttribute("planAddedMessage", "Error adding plan.");
            }
        }

        req.getRequestDispatcher("WEB-INF/admin-add-plan.jsp").forward(req, resp);
    }
}
