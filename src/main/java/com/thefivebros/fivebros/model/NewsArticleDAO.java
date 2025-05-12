package com.thefivebros.fivebros.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.thefivebros.shared.MySQL_Connect.getConnection;

public class NewsArticleDAO {

    public static void main(String[] args) {
        try {
            boolean added = addNewsArticle("test", "test2", "test3", null);
            System.out.println("Success: " + added);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<NewsArticle> getAllNewsArticles() {
        List<NewsArticle> articles = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String query = "{CALL sp_get_news_articles()}";
            try (CallableStatement stmt = conn.prepareCall(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        NewsArticle article = new NewsArticle();
                        article.setArticleId(rs.getInt("article_id"));
                        article.setArticleTitle(rs.getString("article_title"));
                        article.setArticleDescription(rs.getString("article_description"));
                        article.setArticleUrl(rs.getString("article_url"));
                        article.setArticleImage(rs.getString("article_image"));

                        Timestamp createdAt = rs.getTimestamp("article_created_at");
                        if (createdAt != null) {
                            article.setArticleCreatedAt(createdAt.toInstant());
                        }

                        articles.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logger if needed
        }

        return articles;
    }
    public static NewsArticle getArticleById(int articleId) {
        NewsArticle article = null;
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_get_article_by_id(?)}")) {
            stmt.setInt(1, articleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                article = new NewsArticle(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("url"),
                        rs.getString("image")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }





    public static boolean addNewsArticle(String title, String description, String url, String image) {


        try (Connection conn = getConnection()) {
            String query = "{CALL sp_add_news_article(?, ?, ?, ?)}";
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setString(1, title);
                stmt.setString(2, description);
                stmt.setString(3, url);
                stmt.setString(4, image != null ? image : "");

                stmt.execute();
                return true;
            }
        } catch (SQLException e) {
            
            return false;
        }
    }

    public static boolean update(NewsArticle article) {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL sp_edit_article(?, ?, ?, ?, ?)}")) {
            stmt.setString(1, article.getArticleTitle());
            stmt.setString(2, article.getArticleDescription());
            stmt.setString(3, article.getArticleUrl());
            stmt.setString(4, article.getArticleImage());
            stmt.setInt(5, article.getArticleId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating article: " + e.getMessage(), e);
        }
    }
    public boolean deleteNewsArticle(int userId, int articleId) {
        try (Connection conn = getConnection()) {
            String query = "{CALL sp_delete_news_article(?, ?)}";
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, articleId);

                stmt.execute();
                return true;
            }
        } catch (SQLException e) {
            // Handle error (e.g., user doesn't have permission)
            e.printStackTrace();
            return false;
        }
    }







}
