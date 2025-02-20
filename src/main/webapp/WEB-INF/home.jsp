<%@ page import="java.util.List, java.util.Map" %>

<%--<style>--%>
<%--  main {--%>
<%--    display: flex;--%>
<%--    min-height: 100vh;--%>
<%--    flex-direction: column;--%>
<%--    z-index: 2;--%>
<%--  }--%>
<%--</style>--%>
<main class="container">
  <div class="p-4 p-md-5 mb-4 rounded text-body-emphasis">
    <div class="row d-flex align-items-center justify-content-between text-primary">
        <h1 class="display-4 fst-italic">Latest News</h1>
        <p class="lead my-3">Discover the latest news, events, and updates about new technology.</p>
        <a href="${appURL}/signup"><button type="button" class="btn btn-primary btn-lg justify-content-center ">Join Today</button></a>
    </div>
  </div>
  <!-- Featured News -->
  <div class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary">
    <div class="row d-flex align-items-center justify-content-between">
      <div class="col-lg-6 px-0">
        <%
          List<Map<String, String>> newsArticles = (List<Map<String, String>>) request.getAttribute("newsArticles");
          if (newsArticles != null && !newsArticles.isEmpty()) {
            Map<String, String> featured = newsArticles.get(0);
        %>
        <h1 class="display-4 fst-italic"><%= featured.get("title") %></h1>
        <p class="lead my-3"><%= featured.get("description") %></p>
        <p class="lead mb-0">
          <a href="<%= featured.get("url") %>" class="text-body-emphasis fw-bold" target="_blank" rel="noopener noreferrer">Continue reading...</a>
        </p>
      </div>
      <div class="col-lg-6 text-end order-md-2">
        <img src="<%= featured.get("image") %>" class="img-fluid rounded" alt="News Image"
             onerror="this.src='https://via.placeholder.com/1000x250';"
             style="max-width: 1000px; max-height: 250px; object-fit: cover;">
      </div>
      <% } %>
    </div>
  </div>


  <!-- News Grid -->
  <div class="row mb-2">
    <%
      if (newsArticles != null) {
        for (int i = 3; i < Math.min(newsArticles.size(), 9); i++) {
          Map<String, String> article = newsArticles.get(i);
    %>
    <div class="col-md-6">
      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <strong class="d-inline-block mb-2 text-primary">Technology</strong>
          <h3 class="mb-0"><%= article.get("title") %></h3>
          <p class="lead my-3"><%= article.get("description") %></p>

          <a href="<%= article.get("url") %>" class="icon-link gap-4 icon-link-hover stretched-link" target="_blank" rel="noopener noreferrer">Continue reading</a>
        </div>
        <div class="col-auto d-none d-sm-block col p-4 d-flex flex-column">
          <img src="<%= article.get("image") %>" class="img-fluid rounded" alt="News Image"
               onerror="this.src='https://via.placeholder.com/200x250';"
               style="max-width: 200px; max-height: 250px; object-fit: cover;">
        </div>
      </div>
    </div>
    <% } } %>
  </div>

  <!-- Blog Post Section -->
  <div class="row g-5">
    <div class="col-md-8">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">
        More Technology News
      </h3>

      <%
        if (newsArticles != null) {
          for (int i = 10; i < Math.min(newsArticles.size(), 20); i++) {
            Map<String, String> article = newsArticles.get(i);
      %>
      <article class="blog-post">
        <h2 class="display-5 link-body mb-1"><%= article.get("title") %></h2>
        <p class="blog-post-meta">Published <a href="<%= article.get("url") %>" target="_blank" rel="noopener noreferrer">Read more</a></p>
        <p><%= article.get("description") %></p>
      </article>
      <% } } %>

    </div>
  </div>

</main>


