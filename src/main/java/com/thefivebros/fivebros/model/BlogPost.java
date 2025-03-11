package com.thefivebros.fivebros.model;



import java.sql.Timestamp;

public class BlogPost {
    private String author;
    private String content;
    private Timestamp createdAt;

    public BlogPost(String author, String content, Timestamp createdAt) {
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Corrected method to return String
    public String getAuthor() { return author; }

    public String getContent() { return content; }

    public Timestamp getCreatedAt() { return createdAt; }
}

