package com.thefivebros.fivebros.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    private static final String NEWS_API_URL = "https://api.mediastack.com/v1/news?categories=sports&languages=en&access_key=b0a714cc78622e98e35469b9773b34bc";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String, String>> newsArticles = fetchNews();
        req.setAttribute("newsArticles", newsArticles);
        req.setAttribute("pageTitle", "Home");
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }

    private List<Map<String, String>> fetchNews() {
        List<Map<String, String>> articles = new ArrayList<>();
        HttpURLConnection conn = null;
        BufferedReader br = null;

        try {
            URL url = new URL(NEWS_API_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray newsArray = jsonResponse.optJSONArray("data");

            if (newsArray != null) {
                for (int i = 0; i < newsArray.length(); i++) {
                    JSONObject newsItem = newsArray.optJSONObject(i);
                    if (newsItem != null) {
                        Map<String, String> article = new HashMap<>();
                        article.put("title", newsItem.optString("title", "No Title"));
                        article.put("url", newsItem.optString("url", "#"));
                        article.put("description", newsItem.optString("description", "No description available."));
                        article.put("image", newsItem.optString("image", "https://via.placeholder.com/200x250"));
                        article.put("source", newsItem.optString("source", "Unknown"));
                        article.put("publishedAt", newsItem.optString("publishedAt", "Unknown"));
                        articles.add(article);
                    }
                }
            } else {
                throw new RuntimeException("API response did not contain valid data.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources to avoid memory leaks
            try {
                if (br != null) {
                    br.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return articles;
    }
}