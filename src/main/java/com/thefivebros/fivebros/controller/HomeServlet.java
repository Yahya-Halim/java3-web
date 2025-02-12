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
    private static final String NEWS_API_URL = "https://api.nytimes.com/svc/topstories/v2/technology.json?api-key=CFIBOZHHTfwd5PG2FthkOfGVonwp5qAa";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String, String>> newsArticles = fetchNews();
        req.setAttribute("newsArticles", newsArticles);
        req.setAttribute("pageTitle", "Home");
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }

    private List<Map<String, String>> fetchNews() {
        List<Map<String, String>> articles = new ArrayList<>();

        try {
            URL url = new URL(NEWS_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new IOException("Failed to fetch news: HTTP error code " + responseCode);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray newsArray = jsonResponse.optJSONArray("results");

                if (newsArray != null) {
                    for (int i = 0; i < newsArray.length(); i++) {
                        JSONObject newsItem = newsArray.getJSONObject(i);
                        Map<String, String> article = new HashMap<>();
                        article.put("image", "https://via.placeholder.com/200x250");
                        article.put("title", newsItem.optString("title", "No Title"));
                        article.put("url", newsItem.optString("url", "#"));
                        article.put("description", newsItem.optString("abstract", "No description available."));
                        article.put("source", "The New York Times");
                        article.put("publishedAt", newsItem.optString("published_date", "Unknown"));

                        // Extracting image if available
                        JSONArray multimedia = newsItem.optJSONArray("multimedia");
                        if (multimedia != null && multimedia.length() > 0) {
                            article.put("image", multimedia.getJSONObject(0).optString("url", "https://via.placeholder.com/500x250"));
                        } else {
                            article.put("image", "https://via.placeholder.com/200x250");
                        }

                        articles.add(article);
                    }
                } else {
                    throw new IOException("API response did not contain valid 'results' data.");
                }
            } finally {
                conn.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log error
        }

        return articles;
    }
}
