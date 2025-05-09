<div class="container mt-5">
    <h1 class="text-center mb-4">Add New Article</h1>

    <%-- Display error message if exists --%>
    <c:if test="${not empty error}">
        <div class="alert alert-danger mb-4">
            ${error}
        </div>
    </c:if>

    <form action="${appURL}/articles-add" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">Title *</label>
            <input type="text" class="form-control" name="title" id="title" required
                   value="${param.title}">
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description *</label>
            <textarea class="form-control" name="description" id="description"
                     rows="4" required>${param.description}</textarea>
        </div>
        

        <div class="mb-3">
            <label for="url" class="form-label">Article Link *</label>
            <input type="url" class="form-control" name="url" id="url"
                   placeholder="https://example.com/full-article" required value="${param.url}">
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary btn-lg">Publish Article</button>
            <a href="${appURL}/articles" class="btn btn-secondary btn-lg ms-2">Cancel</a>
        </div>
    </form>
</div>