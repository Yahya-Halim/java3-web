package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.NewsArticle;
import com.thefivebros.fivebros.model.NewsArticleDAO;
import com.thefivebros.fivebros.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/articles-edit")
public class EditArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null || !"active".equals(user.getStatus()) || !"premium".equals(user.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
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
        NewsArticleDAO articleDAO = new NewsArticleDAO();
        NewsArticle article = articleDAO.getArticleById(id);

        if (article == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("article", article);
        req.setAttribute("appURL", req.getContextPath());
        req.getRequestDispatcher("/WEB-INF/edit-article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        // Validate user permissions
        if (user == null || !"active".equals(user.getStatus()) || !"premium".equals(user.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String idParam = req.getParameter("articleId");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String url = req.getParameter("url");
        String image = req.getParameter("image");

        int id;
        boolean validationError = false;

        NewsArticle updatedArticle = new NewsArticle();

        try {
            id = Integer.parseInt(idParam);
            updatedArticle.setArticleId(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid article ID.");
            return;
        }

        // Title validation
        if (title == null || title.trim().isEmpty()) {
            req.setAttribute("titleError", true);
            req.setAttribute("titleMessage", "Title cannot be empty.");
            validationError = true;
        } else {
            updatedArticle.setArticleTitle(title.trim());
        }

        // Description validation
        if (description == null || description.trim().isEmpty()) {
            req.setAttribute("descriptionError", true);
            req.setAttribute("descriptionMessage", "Description cannot be empty.");
            validationError = true;
        } else {
            updatedArticle.setArticleDescription(description.trim());
        }

        // URL validation
        if (url == null || url.trim().isEmpty()) {
            req.setAttribute("urlError", true);
            req.setAttribute("urlMessage", "URL cannot be empty.");
            validationError = true;
        } else {
            updatedArticle.setArticleUrl(url.trim());
        }

        // Image validation (optional)
        updatedArticle.setArticleImage(image != null ? image.trim() : "");

        // If validation errors, forward back to the form
        if (validationError) {
            req.setAttribute("article", updatedArticle);
            req.setAttribute("appURL", req.getContextPath());
            req.getRequestDispatcher("/WEB-INF/edit-article.jsp").forward(req, resp);
            return;
        }

        // Update the article in the database
        NewsArticleDAO articleDAO = new NewsArticleDAO();
        boolean success;
        try {
            success = articleDAO.update(updatedArticle);
        } catch (Exception e) {
            throw new RuntimeException("Error updating article with ID " + id, e);
        }

        if (success) {
            // Redirect to the article details page or confirmation page
            resp.sendRedirect(req.getContextPath() + "/articles?id=" + id);
        } else {
            // Handle case where the update fails (e.g., article not found)
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update article.");
        }
    }
}