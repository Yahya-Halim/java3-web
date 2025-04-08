package com.thefivebros.fivebros.controller;


import com.thefivebros.fivebros.model.BlogPost;
import com.thefivebros.fivebros.model.BlogPostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/blog")
public class BlogPostServlet extends HttpServlet {
    private BlogPostDAO blogPostDAO;



    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    req.getRequestDispatcher("WEB-INF/create-post.jsp").forward(req, resp);
                    break;
                case "view":
                    int viewId = Integer.parseInt(req.getParameter("id"));
                    BlogPost post = blogPostDAO.getPostById(viewId);
                    req.setAttribute("post", post);
                    req.getRequestDispatcher("WEB-INF/post.jsp").forward(req, resp);
                    break;
                case "edit":
                    int editId = Integer.parseInt(req.getParameter("id"));
                    BlogPost editPost = blogPostDAO.getPostById(editId);
                    req.setAttribute("post", editPost);
                    req.getRequestDispatcher("WEB-INF/create-post.jsp").forward(req, resp);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(req.getParameter("id"));
                    blogPostDAO.deletePost(deleteId);
                    resp.sendRedirect("blog");
                    break;
                case "list":
                default:
                    List<BlogPost> posts = blogPostDAO.getAllPosts();
                    req.setAttribute("posts", posts);
                    req.getRequestDispatcher("WEB-INF/blog-posts.jsp").forward(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String author = req.getParameter("author");
        String tags = req.getParameter("tags");

        BlogPost post = new BlogPost();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setTags(tags);

        try {
            if (idStr == null || idStr.isEmpty()) {
                blogPostDAO.createPost(post);
            } else {
                post.setId(Integer.parseInt(idStr));
                blogPostDAO.updatePost(post);
            }
            resp.sendRedirect("blog");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
