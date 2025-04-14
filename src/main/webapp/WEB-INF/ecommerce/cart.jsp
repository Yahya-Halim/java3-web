<section class="h-100 h-custom bg-secondary">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                        <h1 class="fw-bold mb-0">Shopping Cart</h1>
                                        <h6 class="mb-0 text-muted">
                                            <c:choose>
                                                <c:when test="${not empty cart}">
                                                    ${cart.totalProductCount}&nbsp;${cart.totalProductCount eq 1 ? 'item' : 'items'}
                                                </c:when>
                                                <c:otherwise></c:otherwise>
                                            </c:choose>
                                        </h6>
                                    </div>
                                    <hr class="my-4">

                                    <c:forEach items="${cart.contents}" var="entry">
                                    <div class="row mb-4 d-flex justify-content-between align-items-center">
                                        <div class="col-md-5">
                                            <%-- entry.key refers to the product --%>
                                            <h6 class="mb-0">${entry.key.name}</h6>
                                        </div>
                                        <div class="col-md-4">
                                            <form method="POST" action="${appURL}/cart" class="d-flex justify-content-center align-items-end">
                                                <input type="hidden" name="prod_id" value="${entry.key.id}">
                                                <input type="hidden" name="action" value="update">
                                                <div class="input-group w-50">
                                                    <div class="">
                                                        <%-- entry.value refers to the Integer, the product quantity--%>
                                                        <input id="quantity" min="0" name="quantity" value="${entry.value}" type="number"
                                                               class="form-control form-control-sm" />

                                                    </div>
                                                </div>
                                                <button type="submit" class="btn btn-outline-primary btn-sm">Update</button>
                                            </form>
                                        </div>
                                        <div class="col-md-2 text-end">
                                            <h6 class="mb-0"><fmt:formatNumber value="${entry.key.price}" type="currency"></fmt:formatNumber></h6>
                                        </div>
                                        <div class="col-md-1 text-end">
                                            <form method="POST" action="${appURL}/cart" class="d-flex justify-content-center align-items-end">
                                                <input type="hidden" name="prod_id" value="${entry.key.id}">
                                                <input type="hidden" name="action" value="delete">
                                                <button type="submit" class="text-muted"><i class="bi bi-trash"></i></button>
                                            </form>
                                        </div>
                                    </div>

                                    <hr class="my-4">
                                    </c:forEach>

                                    <div class="pt-5">
                                        <h6 class="mb-0"><a href="${appURL}/shop" class="text-body">
                                            <i class="bi bi-arrow-left"></i> Back to shop</a></h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 bg-body-tertiary">
                                <div class="p-5">
                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase">Subtotal</h5>
                                        <h5><fmt:formatNumber value="${cart.totalPrice}" type="currency"></fmt:formatNumber></h5>
                                    </div>

                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase mb-3">Free Shipping</h5>
                                        <h5><fmt:formatNumber value="0" type="currency"></fmt:formatNumber></h5>
                                    </div>

                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-5">
                                        <h5 class="text-uppercase">Total price</h5>
                                        <%-- Add shipping, subtract discount --%>
                                        <h5><fmt:formatNumber value="${cart.totalPrice}" type="currency"></fmt:formatNumber></h5>
                                    </div>

                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-block btn-lg"
                                             data-mdb-ripple-color="dark">Check out</button>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>