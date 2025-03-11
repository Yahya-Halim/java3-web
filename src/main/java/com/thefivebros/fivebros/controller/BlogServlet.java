package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.UserDAO;
import com.thefivebros.fivebros.model.BlogPost;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/post")
public class BlogServlet extends HttpServlet {

    // Method to handle GET requests (fetch and display blog posts)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("user_id") == null) {
            // Use request scope for flash message since session might be null
            req.setAttribute("flashMessageWarning", "You must be logged in to view this page.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login?redirect=post")); // Redirect to login if not logged in
            return;
        }

        // Fetch all blog posts from the database
        List<BlogPost> blogPosts = UserDAO.getAllBlogs();

        // Set blog posts in the request scope
        req.setAttribute("blogPosts", blogPosts);

        // Forward to the post.jsp page
        req.getRequestDispatcher("WEB-INF/post.jsp").forward(req, resp);
    }

    // Method to handle POST requests (create a new blog post)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("user_id") == null) {
            req.setAttribute("flashMessageWarning", "You must be logged in to add a blog.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login?redirect=post"));
            return;
        }

        // Get the user ID from the session
        int userId = (int) session.getAttribute("user_id");

        // Get the content of the post from the form
        String postContent = req.getParameter("post");

        // Insert blog post if content is not empty
        if (postContent != null && !postContent.trim().isEmpty()) {
            try {
                UserDAO.insertBlog(userId, postContent);
                // Set success message
                session.setAttribute("flashMessageSuccess", "Blog post added successfully!");
            } catch (Exception e) {
                // Handle database errors
                session.setAttribute("flashMessageError", "An error occurred while adding the blog post.");
                e.printStackTrace();
            }
        } else {
            // Handle empty post content
            session.setAttribute("flashMessageWarning", "Blog post content cannot be empty.");
        }

        // Redirect to the same page to avoid duplicate form submissions
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/post"));
    }
}