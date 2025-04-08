<%@ page import="com.thefivebros.fivebros.model.BlogPost" %>


<%
  BlogPost post = (BlogPost) request.getAttribute("post");
%>

<div>
  <h1><%= post.getTitle() %></h1>
  <p><strong>By:</strong> <%= post.getAuthor() %></p>
  <p><strong>Tags:</strong> <%= post.getTags() %></p>
  <p><%= post.getContent().replaceAll("\n", "<br/>") %></p>

  <a href="${appURL}/blog">Back to all posts</a>
</div>