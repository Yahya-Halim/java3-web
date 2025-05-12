<div class="container mt-5">
    <h1 class="text-center mb-4">Add New Article</h1>

    <%-- Display success/error messages --%>
    <c:if test="${not empty formError}">
        <div class="alert alert-danger mb-4">
            ${formMessage}
        </div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success mb-4">
            ${successMessage}
        </div>
    </c:if>

    <form action="${appURL}/articles-add" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="title" class="form-label">Title *</label>
            <input type="text" class="form-control ${not empty titleError ? 'is-invalid' : ''}"
                   name="title" id="title" value="${param.title}" required>
            <c:if test="${not empty titleError}">
                <div class="invalid-feedback">${titleError}</div>
            </c:if>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description *</label>
            <textarea class="form-control ${not empty descriptionError ? 'is-invalid' : ''}"
                      name="description" id="description" rows="4" required>${param.description}</textarea>
            <c:if test="${not empty descriptionError}">
                <div class="invalid-feedback">${descriptionError}</div>
            </c:if>
        </div>

        <div class="mb-3">
            <label for="url" class="form-label">Article URL *</label>
            <input type="url" class="form-control ${not empty urlError ? 'is-invalid' : ''}"
                   name="url" id="url" placeholder="https://example.com/article"
                   value="${param.url}" required>
            <c:if test="${not empty urlError}">
                <div class="invalid-feedback">${urlError}</div>
            </c:if>
        </div>



        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary btn-lg px-4">Publish Article</button>
            <a href="${appURL}/articles" class="btn btn-outline-secondary btn-lg ms-2 px-4">Cancel</a>
        </div>
    </form>
</div>

