<div class="container py-4">


    <!-- Products -->
    <div class="row g-4">
        <c:forEach items="${products}" var="product">
            <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
                <div class="card shadow-sm">
                    <div class="card-header">
                        <h4>${product.prod_name}</h4>
                    </div>
                    <div class="card-body">
                        <p class="card-text">${product.prod_desc}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <small class="text-body-secondary">
                                <fmt:formatNumber value="${product.prod_price}" type="currency" />
                            </small>
                            <a href="add-to-cart?prod_id=${product.prod_id}" class="btn btn-sm btn-outline-primary">Add to Cart</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    const searchBox = document.getElementById("searchBox");
    const clearBtn = document.getElementById("clearSearch");

    searchBox.addEventListener("input", () => {
        clearBtn.classList.toggle("d-none", searchBox.value.trim() === "");
    });

    document.getElementById("clearSearch").addEventListener("click", () => {
        searchBox.value = "";
        clearBtn.classList.add("d-none");
        searchBox.focus();
    });
</script>
