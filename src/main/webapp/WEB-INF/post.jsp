<%@ page import="com.thefivebros.fivebros.model.BlogPost" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<div class="container mx-auto max-w-2xl bg-white p-6 rounded-lg shadow-md">
  <h2 class="text-2xl font-bold mb-4">Simple Blogger</h2>

  <form action="blog" method="post">
    <textarea
            name="post"
            placeholder="Write something..."
            class="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            rows="4"
    ></textarea>
    <br>
    <button
            type="submit"
            class="mt-3 bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
    >
      Post
    </button>
  </form>

  <h3 class="text-xl font-semibold mb-4">Blog Posts</h3>
  <%
    List<BlogPost> blogPosts = (List<BlogPost>) request.getAttribute("blogPosts");
    if (blogPosts != null && !blogPosts.isEmpty()) {
      SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm"); // Formatting the date
      for (BlogPost post : blogPosts) {
  %>
  <div class="post bg-gray-50 p-4 rounded-lg mb-3 shadow-sm">
    <p class="text-sm text-gray-500"><strong><%= post.getAuthor() %></strong> - <%= sdf.format(post.getCreatedAt()) %></p>
    <p class="mt-2"><%= post.getContent() %></p>
  </div>
  <%
    }
  } else {
  %>
  <p class="text-gray-600">No posts yet. Be the first to write!</p>
  <%
    }
  %>
</div>
