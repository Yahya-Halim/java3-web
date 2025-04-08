package com.thefivebros.fivebros.model;

import com.thefivebros.fivebros.model.BlogPost;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogPostDAO {
    private Connection conn;

    public BlogPostDAO(Connection conn) {
        this.conn = conn;
    }

    public void createPost(BlogPost post) throws SQLException {
        String sql = "INSERT INTO blog_posts (title, content, user, tags) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());
            stmt.setString(4, post.getTags());
            stmt.executeUpdate();
        }
    }

    public BlogPost getPostById(int id) throws SQLException {
        String sql = "SELECT * FROM blog_posts WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPostFromResultSet(rs);
            }
        }
        return null;
    }

    public List<BlogPost> getAllPosts() throws SQLException {
        List<BlogPost> posts = new ArrayList<>();
        String sql = "SELECT * FROM blog_posts ORDER BY created_at DESC";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                posts.add(extractPostFromResultSet(rs));
            }
        }
        return posts;
    }

    public void updatePost(BlogPost post) throws SQLException {
        String sql = "UPDATE blog_posts SET title = ?, content = ?, tags = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getTags());
            stmt.setInt(4, post.getId());
            stmt.executeUpdate();
        }
    }

    public void deletePost(int id) throws SQLException {
        String sql = "DELETE FROM blog_posts WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private BlogPost extractPostFromResultSet(ResultSet rs) throws SQLException {
        return new BlogPost(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("user"),
                rs.getString("tags"),
                rs.getTimestamp("created_at")
        );
    }
}
