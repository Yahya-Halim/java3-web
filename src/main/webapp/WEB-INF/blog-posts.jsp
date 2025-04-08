<%@ page import="com.thefivebros.fivebros.model.BlogPost" %>
<%@ page import="java.util.List" %>
<%
  List<BlogPost> posts = (List<BlogPost>) request.getAttribute("posts");
%>
<div>

  <h1>All Blog Posts</h1>
  <a href="${appURL}blog?action=new">+ New Post</a>
  <ul>
    <% for (BlogPost post : posts) { %>
    <li>
      <h2><a href="blog?action=view&id=<%= post.getId() %>"><%= post.getTitle() %></a></h2>
      <p>By <%= post.getAuthor() %> | Tags: <%= post.getTags() %></p>
      <a href="${appURL}blog?action=edit&id=<%= post.getId() %>">Edit</a> |
      <a href="${appURL}blog?action=delete&id=<%= post.getId() %>" onclick="return confirm('Delete this post?');">Delete</a>
    </li>
    <% } %>
  </ul>
</div>