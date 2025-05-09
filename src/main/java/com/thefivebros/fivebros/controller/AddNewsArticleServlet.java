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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("activeUser");

        // Check if user is logged in and has premium privileges
        if (user == null || !"premium".equals(user.getPrivileges())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        request.setAttribute("appURL", request.getContextPath());
        request.getRequestDispatcher("/WEB-INF/add-articles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("activeUser");

        // Check authorization
        if (user == null || !"premium".equals(user.getPrivileges())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // Get form parameters
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String url = request.getParameter("url");
        String image = request.getParameter("image");

        // Basic validation
        if (title == null || title.trim().isEmpty() ||
                description == null || description.trim().isEmpty() ||
                url == null || url.trim().isEmpty()) {

            request.setAttribute("error", "Title, description, and URL are required fields");

            return;
        }

        // Add article using the logged-in user's ID
        NewsArticleDAO dao = new NewsArticleDAO();
        boolean success = dao.addNewsArticle(user.getUserId(), title, description, url, image);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/articles");
        } else {
            request.setAttribute("error", "Failed to add article. Please try again.");

        }
        request.setAttribute("appURL", request.getContextPath());
        request.getRequestDispatcher("/WEB-INF/articles.jsp").forward(request, response);
    }
}