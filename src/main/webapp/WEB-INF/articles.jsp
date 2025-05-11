<main class="flex-1 transition-all duration-300 ease-in-out" lang="${language}" <c:if test="${language eq 'ar'}">dir="rtl"</c:if>>
  <!-- Header Section -->
  <div class="relative p-4 md:p-8 mb-8 rounded-lg overflow-hidden bg-black border border-gray-800">
    <div class="absolute inset-0 z-0">
      <div class="absolute inset-0 bg-gradient-to-r from-purple-900 via-blue-900 to-teal-900 opacity-50 animate-holographicBackground"></div>
      <div class="absolute inset-0 bg-particle opacity-30"></div>
      <div class="absolute inset-0 bg-grid-teal-500 opacity-20"></div>
    </div>
    <div class="relative z-10 text-white text-center">
      <h1 class="text-4xl md:text-6xl font-bold italic bg-clip-text text-transparent bg-gradient-to-r from-teal-400 to-purple-400 animate-textGlow">Latest News</h1>
      <p class="text-lg text-teal-300 mt-2">Post our latest news and updates here as premium user</p>
      <div class="mt-6">
      <c:if test="${not empty sessionScope.activeUser && sessionScope.activeUser.status == 'active' && sessionScope.activeUser.privileges == 'premium'}">
        <a href="${appURL}/articles-add"
           class="inline-block bg-green-600 hover:bg-green-500 text-white font-semibold py-3 px-6 rounded-xl transition-all duration-200 shadow-md hover:shadow-lg">
          + Add New Article
        </a>
      </c:if>
      </div>
    </div>
  </div>

  <!-- Articles Grid -->
  <c:choose>
    <c:when test="${not empty articles}">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6 px-4 md:px-8 mb-12">
        <c:forEach var="article" items="${articles}">
          <div class="bg-gray-900 border border-gray-800 rounded-lg overflow-hidden shadow hover:shadow-xl transition-shadow">
            <div class="flex flex-col md:flex-row">

              <div class="p-6 flex flex-col justify-between md:w-2/3">
                <div>
                  <h2 class="text-2xl font-bold text-white mb-2">${article.articleTitle}</h2>
                  <p class="text-gray-300 mb-7">${article.articleDescription}</p>
                </div>
                <div class="flex flex-wrap gap-2">
                  <a href="${article.articleUrl}" target="_blank"
                     class="text-blue-400 hover:text-blue-200 font-semibold">Read More</a>
                  <c:if test="${not empty sessionScope.activeUser.userId  && sessionScope.activeUser.status == 'active' && sessionScope.activeUser.privileges == 'premium'}">
                      <a href="${appURL}/articles-edit?id=${article.articleId}"
                         class="text-yellow-400 hover:text-yellow-200 font-semibold">Edit</a>
                      <form action="${appURL}/articles-delete" method="post" class="inline">
                        <input type="hidden" name="id" value="${article.articleId}">
                        <button type="submit" class="text-red-400 hover:text-red-200 font-semibold">Delete</button>
                      </form>
                  </c:if>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:when>
    <c:otherwise>
      <div class="text-center py-12 text-gray-500 text-lg">No news articles found.</div>
    </c:otherwise>
  </c:choose>
</main>

<!-- Include custom animations -->
<style>
  @keyframes holographicBackground {
    0% { background-position: 0% 50%; }
    50% { background-position: 100% 50%; }
    100% { background-position: 0% 50%; }
  }
  .animate-holographicBackground {
    background-size: 200% 200%;
    animation: holographicBackground 10s ease infinite;
  }
  @keyframes textGlow {
    0% { text-shadow: 0 0 10px rgba(14,165,233,0.8), 0 0 20px rgba(79,70,229,0.8); }
    50% { text-shadow: 0 0 20px rgba(14,165,233,1), 0 0 40px rgba(79,70,229,1); }
    100% { text-shadow: 0 0 10px rgba(14,165,233,0.8), 0 0 20px rgba(79,70,229,0.8); }
  }
  .animate-textGlow {
    animation: textGlow 2s ease-in-out infinite;
  }
  .bg-grid-teal-500 {
    background-image: linear-gradient(to right, rgba(20,184,166,0.1) 1px, transparent 1px),
                      linear-gradient(to bottom, rgba(20,184,166,0.1) 1px, transparent 1px);
    background-size: 40px 40px;
  }
  .bg-particle {
    background-image: radial-gradient(circle, rgba(20,184,166,0.2) 1px, transparent 1px);
    background-size: 20px 20px;
    animation: moveParticles 5s linear infinite;
  }
  @keyframes moveParticles {
    0% { background-position: 0 0; }
    100% { background-position: 100% 100%; }
  }
</style>
