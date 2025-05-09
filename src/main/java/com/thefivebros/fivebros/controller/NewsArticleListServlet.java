package com.thefivebros.fivebros.controller;

import com.thefivebros.fivebros.model.NewsArticle;
import com.thefivebros.fivebros.model.NewsArticleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/articles")
public class NewsArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsArticleDAO dao = new NewsArticleDAO();
        List<NewsArticle> articles = dao.getAllNewsArticles();

        request.setAttribute("articles", articles);
        request.getRequestDispatcher("/WEB-INF/articles.jsp").forward(request, response);
    }
}