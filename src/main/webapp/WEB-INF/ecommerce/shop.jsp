<div class="container py-4">

    <div class="col d-flex justify-content-between align-items-center">
        <h2 class="mb-4">Shop the best selection of toys around!</h2>
        <!-- Responsive toggler START -->
        <button class="btn btn-primary d-lg-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
            <i class="bi bi-list fs-4"></i>
        </button>
        <!-- Responsive toggler END -->
    </div>
    <div class="row">
        <div class="col-lg-9">
            <div class="col d-flex justify-content-between align-items-center">
                <p class="lead">Showings products ${firstProductShown}-${lastProductShown} of ${totalProducts}</p>
                <%@include file="/WEB-INF/pagination.jspf"%>
            </div>
            <div class="row g-4">
                <c:forEach items="${products}" var="product">
                <%-- 12 means full-width, 6 means half-width, 4 means one-third width, 3 means one-forth width   --%>
                <div class="col-sm-12 col-md-6 col-lg-4">
                    <div class="card shadow-sm">
                        <div class="card-header py-2">
                            <h4 class="my-0 text-center">${product.name}</h4>
                        </div>
                        <div class="card-body">
                            <p class="badge rounded-pill text-bg-secondary"><a class="text-white" href="${appURL}/shop?categories=${product.categoryId}">${product.categoryName}</a></p>
                            <p class="card-text">${product.description}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="fw-bold"><fmt:formatNumber value="${product.price}" type="currency" /></small>
                                <form method="POST" action="${appURL}/add-to-cart" class="w-75">
                                    <input type="hidden" name="prod_id" value="${product.id}">
                                    <div class="input-group">
                                        <div class="form-floating">
                                            <input type="number" min="0" class="form-control" id="qty" name="qty" value="1">
                                            <label for="qty">Quantity</label>
                                        </div>
                                        <button class="btn btn-primary" type="submit">Add to Cart</button>
                                    </div>
                                </form>
                            </div>
                        </div><!-- End Card Body -->
                    </div><!-- End Card -->
                </div><!-- End Column -->
                </c:forEach>
            </div> <!-- End Product Row -->
            <div class="col d-flex justify-content-end align-items-center mt-4">
                <%@include file="/WEB-INF/pagination.jspf"%>
            </div>
        </div><!-- End 3/4 -->
        <%@include file="shop-sidebar.jspf"%>
    </div><!-- End 3/4 (Products), 1/4 (Sidebar) Row -->
</div><!-- End Container -->
