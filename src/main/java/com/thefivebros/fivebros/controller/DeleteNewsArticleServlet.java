package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.NewsArticleDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/articles-delete")
public class DeleteNewsArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        // Check if user has proper privileges
        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"premium".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String idParam = req.getParameter("id");
        int id;

        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid article ID.");
            return;
        }

        // Call the DAO to delete the article
        boolean success = new NewsArticleDAO().deleteNewsArticle(userFromSession.getUserId(), id);

        // Set success or failure message in session
        if (success) {
            session.setAttribute("articleDeleteMessage", "Article deleted successfully.");
        } else {
            session.setAttribute("articleDeleteMessage", "Failed to delete the article.");
        }

        // Redirect back to the article list page
        resp.sendRedirect(req.getContextPath() + "/articles");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        // Check if user has proper privileges
        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"premium".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String idParam = req.getParameter("id");
        int id;

        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid article ID.");
            return;
        }

        // Call the DAO to delete the article
        boolean success = new NewsArticleDAO().deleteNewsArticle(userFromSession.getUserId(), id);

        // Set success or failure message in session
        if (success) {
            session.setAttribute("articleDeleteMessage", "Article deleted successfully.");
        } else {
            session.setAttribute("articleDeleteMessage", "Failed to delete the article.");
        }

        // Redirect back to the article list page
        resp.sendRedirect(req.getContextPath() + "/articles");
    }
}