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
import java.util.List;
import java.util.Map;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&category=health&news&apiKey=c30076a5341949f98d6a50c3301602f7";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String, String>> newsArticles = fetchNews();
        req.setAttribute("newsArticles", newsArticles);
        req.setAttribute("pageTitle", "Home");
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }

    private List<Map<String, String>> fetchNews() throws IOException {
        List<Map<String, String>> articlesList = new ArrayList<>();
        URL url = new URL(NEWS_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray articles = jsonResponse.getJSONArray("articles");

        for (int i = 0; i < articles.length(); i++) {
            JSONObject article = articles.getJSONObject(i);
            articlesList.add(Map.of(
                    "title", article.optString("title"),
                    "description", article.optString("description"),
                    "url", article.optString("url"),
                    "image", article.optString("urlToImage")
            ));
        }
        return articlesList;
    }
}
