<%@ page import="com.thefivebros.fivebros.model.BlogPost" %>
<%
  BlogPost post = (BlogPost) request.getAttribute("post");
  boolean editing = post != null;
%>

<div>
  <h1><%= editing ? "Edit" : "Create New" %> Blog Post</h1>
  <form action="blog" method="post">
    <% if (editing) { %>
    <input type="hidden" name="id" value="<%= post.getId() %>"/>
    <% } %>
    <label>Title:</label><br/>
    <input type="text" name="title" value="<%= editing ? post.getTitle() : "" %>" required/><br/>

    <label>Author:</label><br/>
    <input type="text" name="author" value="<%= editing ? post.getAuthor() : "" %>" required/><br/>

    <label>Tags (comma-separated):</label><br/>
    <input type="text" name="tags" value="<%= editing ? post.getTags() : "" %>" /><br/>

    <label>Content:</label><br/>
    <textarea name="content" rows="10" cols="60" required><%= editing ? post.getContent() : "" %></textarea><br/>

    <button type="submit"><%= editing ? "Update" : "Publish" %></button>
    <a href="${appURL}/blog">Cancel</a>
  </form>

</div>