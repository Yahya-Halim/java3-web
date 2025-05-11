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

@WebServlet("/articles-add")
public class AddNewsArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null || !"active".equals(user.getStatus()) || !"premium".equals(user.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        req.setAttribute("appURL", req.getContextPath());
        req.getRequestDispatcher("/WEB-INF/add-articles.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null || !"active".equals(user.getStatus()) || !"premium".equals(user.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // Get form parameters
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String url = req.getParameter("url");
        String image = req.getParameter("image");

        // Basic validation
        boolean validationError = false;

        if (title == null || title.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("titleError", "Title is required");
        }

        if (description == null || description.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("descriptionError", "Description is required");
        }

        if (url == null || url.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("urlError", "URL is required");
        }

        // Repopulate form fields
        req.setAttribute("title", title);
        req.setAttribute("description", description);
        req.setAttribute("url", url);
        req.setAttribute("image", image);

        // Add to database if no validation errors
        boolean articleAdded = false;
        if (!validationError) {
            articleAdded = NewsArticleDAO.addNewsArticle(
                    user.getUserId(),
                    title.trim(),
                    description.trim(),
                    url.trim(),
                    image != null ? image.trim() : null
            );

            if (articleAdded == true) {
                req.setAttribute("successMessage", "Article added successfully!");
                resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/articles"));
                return;
            } else {
                req.setAttribute("formError", true);
                req.setAttribute("formMessage", "Failed to add article. Please try again.");
            }
        }

        req.setAttribute("appURL", req.getContextPath());
        req.getRequestDispatcher("/WEB-INF/add-articles.jsp").forward(req, resp);
    }
}