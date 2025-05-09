package com.thefivebros.fivebros.model;

import java.time.Instant;

public class NewsArticle {
    private int articleId;
    private String articleTitle;
    private String articleDescription;
    private String articleUrl;
    private String articleImage;
    private Instant articleCreatedAt;

    public NewsArticle() {

    }



    public NewsArticle(int articleId, String articleTitle, String articleDescription, String articleUrl, String articleImage) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleDescription = articleDescription;
        this.articleUrl = articleUrl;
        this.articleImage = articleImage;

    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public Instant getArticleCreatedAt() {
        return articleCreatedAt;
    }

    public void setArticleCreatedAt(Instant articleCreatedAt) {
        this.articleCreatedAt = articleCreatedAt;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", articleImage='" + articleImage + '\'' +
                ", articleCreatedAt=" + articleCreatedAt +
                '}';
    }
}
