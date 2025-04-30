package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.Plan;
import com.thefivebros.fivebros.model.PlanDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/update-plan")
public class AdminUpdatePlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null || !"active".equals(user.getStatus()) || !"admin".equals(user.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String idParam = req.getParameter("id");
        int id;

        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid plan ID.");
            return;
        }

        Plan plan = PlanDAO.get(id);
        if (plan == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("plan", plan);
        req.getRequestDispatcher("/WEB-INF/admin-update-plan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null || !"active".equals(user.getStatus()) || !"admin".equals(user.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String idParam = req.getParameter("id");
        String name = req.getParameter("planName");
        String priceStr = req.getParameter("planPrice");
        String description = req.getParameter("planDescription");

        int id;
        boolean validationError = false;

        Plan updatedPlan = new Plan();

        try {
            id = Integer.parseInt(idParam);
            updatedPlan.setId(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid plan ID.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("planNameError", true);
            req.setAttribute("planNameMessage", "Name cannot be empty.");
            validationError = true;
        } else {
            updatedPlan.setName(name);
            req.setAttribute("planNameError", false);
            req.setAttribute("planNameMessage", "Looks good!");
        }

        try {
            BigDecimal price = new BigDecimal(priceStr);
            if (price.compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
            updatedPlan.setPrice(price);
            req.setAttribute("planPriceError", false);
            req.setAttribute("planPriceMessage", "Looks good!");
        } catch (NumberFormatException e) {
            req.setAttribute("planPriceError", true);
            req.setAttribute("planPriceMessage", "Invalid price.");
            validationError = true;
        }

        updatedPlan.setDescription(description != null ? description.trim() : "");

        if (!validationError) {
            boolean success = PlanDAO.update(updatedPlan);
            req.setAttribute("planUpdated", success);
            req.setAttribute("planUpdatedMessage", success ? "Plan successfully updated!" : "Failed to update plan.");
        }

        req.setAttribute("plan", updatedPlan);
        req.getRequestDispatcher("/WEB-INF/admin-update-plan.jsp").forward(req, resp);
    }
}
