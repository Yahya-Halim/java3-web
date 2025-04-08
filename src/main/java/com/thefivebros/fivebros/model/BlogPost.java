package com.thefivebros.fivebros.model;

import java.sql.Timestamp;

public class BlogPost {
    private int id;
    private String title;
    private String content;
    private String user;
    private String tags;
    private Timestamp createdAt;

    public BlogPost() {}

    public BlogPost(int id, String title, String content, String user, String tags, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.tags = tags;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return user; }
    public void setAuthor(String user) { this.user = user; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user='" + user + '\'' +
                ", tags='" + tags + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
