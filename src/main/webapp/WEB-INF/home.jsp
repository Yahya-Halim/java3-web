<%@ page import="java.util.List, java.util.Map" %>

<!-- Main Container -->
<main class="flex-1 transition-all duration-300 ease-in-out">
  <!-- Hero Section -->
  <div class="relative p-4 md:p-8 mb-8 rounded-lg overflow-hidden bg-black border border-gray-800">
    <!-- Animated Background -->
    <div class="absolute inset-0 z-0">
      <!-- Holographic Gradient Animation -->
      <div class="absolute inset-0 bg-gradient-to-r from-purple-900 via-blue-900 to-teal-900 opacity-50 animate-holographicBackground"></div>
      <!-- Particle Animation -->
      <div class="absolute inset-0 bg-particle opacity-30"></div>
      <!-- Grid Overlay -->
      <div class="absolute inset-0 bg-grid-teal-500 opacity-20"></div>
    </div>

    <!-- Content -->
    <div class="relative z-10 flex flex-col md:flex-row items-center justify-between text-white">
      <div>
        <h1 class="text-4xl md:text-6xl font-bold italic bg-clip-text text-transparent bg-gradient-to-r from-teal-400 to-purple-400 animate-textGlow">
          LATEST NEWS
        </h1>
        <p class="text-lg md:text-xl my-3 font-mono text-teal-300">Discover the latest news, events, and updates about new technology.</p>
      </div>
      <div class="relative inline-flex items-center justify-center gap-4 group">
        <div
                class="absolute inset-0 duration-1000 opacity-60 transitiona-all bg-gradient-to-r from-indigo-500 via-pink-500 to-yellow-400 rounded-xl blur-lg filter group-hover:opacity-100 group-hover:duration-200"
        ></div>
        <a
                role="button"
                class="group relative inline-flex items-center justify-center text-base rounded-xl bg-gray-900 px-8 py-3 font-semibold text-white transition-all duration-200 hover:bg-gray-800 hover:shadow-lg hover:-translate-y-0.5 hover:shadow-gray-600/30"
                title="payment"
                href="${appURL}/signup"
        >Get Started For Free<svg
                aria-hidden="true"
                viewBox="0 0 10 10"
                height="10"
                width="10"
                fill="none"
                class="mt-0.5 ml-2 -mr-1 stroke-white stroke-2"
        >
          <path
                  d="M0 5h7"
                  class="transition opacity-0 group-hover:opacity-100"
          ></path>
          <path
                  d="M1 1l4 4-4 4"
                  class="transition group-hover:translate-x-[3px]"
          ></path>
        </svg>
        </a>
      </div>

    </div>
  </div>

  <!-- Featured News -->
  <div class="p-4 md:p-8 mb-8 rounded-lg bg-gray-100">
    <div class="flex flex-col md:flex-row items-center justify-between">
      <%
        List<Map<String, String>> newsArticles = (List<Map<String, String>>) request.getAttribute("newsArticles");
        if (newsArticles != null && !newsArticles.isEmpty()) {
          Map<String, String> featured = newsArticles.get(0);
      %>
      <div class="w-full md:w-1/2">
        <h1 class="text-4xl md:text-5xl font-bold italic text-black"><%= featured.get("title") %></h1>
        <p class="text-lg md:text-xl my-3 text-black"><%= featured.get("description") %></p>
        <a href="<%= featured.get("url") %>" class="text-blue-600 font-bold hover:underline" target="_blank" rel="noopener noreferrer">Continue reading...</a>
      </div>
      <div class="w-full md:w-1/2 mt-6 md:mt-0 md:pl-8">
        <img src="<%= featured.get("image") %>" class="w-full h-64 md:h-72 object-cover rounded-lg" alt="News Image" onerror="this.src='https://via.placeholder.com/1000x250';">
      </div>
      <% } %>
    </div>
  </div>

  <!-- News Grid -->
  <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
    <%
      if (newsArticles != null) {
        for (int i = 3; i < Math.min(newsArticles.size(), 9); i++) {
          Map<String, String> article = newsArticles.get(i);
    %>
    <div class="bg-gray-900 border border-gray-800 rounded-lg overflow-hidden shadow-md hover:shadow-lg transition-shadow">
      <div class="flex flex-col md:flex-row">
        <div class="p-6 flex-1">
          <span class="inline-block bg-blue-900 text-blue-200 text-sm font-semibold px-2 py-1 rounded mb-2">Technology</span>
          <h3 class="text-2xl font-bold mb-2 text-white"><%= article.get("title") %></h3>
          <p class="text-white-300 mb-4"><%= article.get("description") %></p>
          <a href="<%= article.get("url") %>" class="text-blue-400 font-semibold hover:underline" target="_blank" rel="noopener noreferrer">Continue reading</a>
        </div>
        <div class="p-6">
          <img src="<%= article.get("image") %>" class="w-48 h-48 object-cover rounded-lg" alt="News Image" onerror="this.src='https://via.placeholder.com/200x250';">
        </div>
      </div>
    </div>
    <% } } %>
  </div>

  <!-- Blog Post Section -->
  <div class="mt-8">
    <div class="max-w-4xl mx-auto">
      <h3 class="text-3xl font-bold italic border-b-2 border-gray-200 pb-4 mb-6 text-white">More Technology News</h3>
      <%
        if (newsArticles != null) {
          for (int i = 10; i < Math.min(newsArticles.size(), 20); i++) {
            Map<String, String> article = newsArticles.get(i);
      %>
      <article class="mb-8">
        <h2 class="text-2xl font-bold text-white-800 mb-2 "><%= article.get("title") %></h2>
        <p class="text-white-600 mb-4">Published <a href="<%= article.get("url") %>" class="text-blue-600 hover:underline" target="_blank" rel="noopener noreferrer">Read more</a></p>
        <p class="text-white-700"><%= article.get("description") %></p>
      </article>
      <% } } %>
    </div>
  </div>
</main>


<!-- Custom CSS for Animations -->
<style>
  /* Holographic Background Animation */
  @keyframes holographicBackground {
    0% {
      background-position: 0% 50%;
    }
    50% {
      background-position: 100% 50%;
    }
    100% {
      background-position: 0% 50%;
    }
  }

  .animate-holographicBackground {
    background-size: 200% 200%;
    animation: holographicBackground 10s ease infinite;
  }

  /* Text Glow Animation */
  @keyframes textGlow {
    0% {
      opacity: 0.8;
      text-shadow: 0 0 10px rgba(14, 165, 233, 0.8), 0 0 20px rgba(79, 70, 229, 0.8);
    }
    50% {
      opacity: 1;
      text-shadow: 0 0 20px rgba(14, 165, 233, 1), 0 0 40px rgba(79, 70, 229, 1);
    }
    100% {
      opacity: 0.8;
      text-shadow: 0 0 10px rgba(14, 165, 233, 0.8), 0 0 20px rgba(79, 70, 229, 0.8);
    }
  }

  .animate-textGlow {
    animation: textGlow 2s ease-in-out infinite;
  }

  /* Grid Overlay */
  .bg-grid-teal-500 {
    background-image: linear-gradient(to right, rgba(20, 184, 166, 0.1) 1px, transparent 1px),
    linear-gradient(to bottom, rgba(20, 184, 166, 0.1) 1px, transparent 1px);
    background-size: 40px 40px;
  }

  /* Particle Animation */
  .bg-particle {
    background-image: radial-gradient(circle, rgba(20, 184, 166, 0.2) 1px, transparent 1px);
    background-size: 20px 20px;
    animation: moveParticles 5s linear infinite;
  }

  @keyframes moveParticles {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: 100% 100%;
    }
  }
</style>