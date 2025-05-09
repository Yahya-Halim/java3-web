<div class="container mx-auto p-6">
    <div class="max-w-xl mx-auto bg-white p-6 rounded shadow">
        <h1 class="text-2xl font-bold mb-4">Edit Article</h1>

        <!-- Feedback Message -->
        <c:if test="${not empty articleUpdatedMessage}">
            <div class="mb-4 px-4 py-3 rounded <c:choose>
                <c:when test="${articleUpdated}">bg-green-100 text-green-800</c:when>
                <c:otherwise>bg-red-100 text-red-800</c:otherwise>
                </c:choose>">
                ${articleUpdatedMessage}
            </div>
        </c:if>

        <!-- Edit Article Form -->
        <form method="post" action="${appURL}/articles-edit" class="space-y-6">
            <input type="hidden" name="articleId" value="${article.articleId}" />

            <!-- Title -->
            <div>
                <label for="title" class="block text-sm font-medium text-gray-700">Title</label>
                <input type="text" id="title" name="title" value="${fn:escapeXml(article.articleTitle)}"
                       class="mt-1 block w-full border text-black
                       ${titleError ? 'border-red-500' : ''} rounded p-2"
                       aria-describedby="titleMessage" required />
                <p id="titleMessage" class="text-sm
                   ${titleError ? 'text-red-600' : 'text-green-600'}">
                    ${titleMessage}
                </p>
            </div>

            <!-- Description -->
            <div>
                <label for="description" class="block text-sm font-medium text-gray-700">Description</label>
                <textarea id="description" name="description" rows="4" required
                          class="mt-1 block w-full border text-black
                          ${descriptionError ? 'border-red-500' : ''} rounded p-2"
                          aria-describedby="descriptionMessage">${fn:escapeXml(article.articleDescription)}</textarea>
                <p id="descriptionMessage" class="text-sm
                   ${descriptionError ? 'text-red-600' : 'text-green-600'}">
                    ${descriptionMessage}
                </p>
            </div>

            <!-- URL -->
            <div>
                <label for="url" class="block text-sm font-medium text-gray-700">Article URL</label>
                <input type="url" id="url" name="url" value="${fn:escapeXml(article.articleUrl)}"
                       class="mt-1 block w-full border text-black
                       ${urlError ? 'border-red-500' : ''} rounded p-2"
                       aria-describedby="urlMessage" required />
                <p id="urlMessage" class="text-sm
                   ${urlError ? 'text-red-600' : 'text-green-600'}">
                    ${urlMessage}
                </p>
            </div>

            <!-- Image URL -->
            <div>
                <label for="image" class="block text-sm font-medium text-gray-700">Image URL</label>
                <input type="url" id="image" name="image" value="${fn:escapeXml(article.articleImage)}"
                       class="mt-1 block w-full border text-black rounded p-2" />
            </div>

            <!-- Submit Button -->
            <div>
                <button type="submit" class="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded">
                    Update Article
                </button>
            </div>
        </form>

        <!-- Back Link -->
        <div class="mt-4 text-center">
            <a href="${appURL}/articles" class="text-blue-600 hover:underline">Back to Article List</a>
        </div>
    </div>
</div>