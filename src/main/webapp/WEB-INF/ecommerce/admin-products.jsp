
<div class="container py-4">
  <h2>Products</h2>
  <p class="lead">There ${fn:length(products) == 1 ? "is" : "are"}&nbsp;${fn:length(products)} product${fn:length(products) != 1 ? "s" : ""}</p>
  <div class="table-responsive large">
    <table class="table table-striped table-lg">
      <thead>
      <tr>
        <th scope="col"></th>
        <th scope="col">Name</th>
        <th scope="col">Description</th>
        <th scope="col">Price</th>
        <th scope="col">Vendor</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${products}" var="product">
        <tr>
          <td>
            <a href="update-product?id=${product.id}" class="btn btn-outline-primary" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Update</a>
            <a href="delete-product?id=${product.id}" class="btn btn-outline-danger" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Delete</a>
          </td>
          <td class="align-middle">${product.name}</td>
          <td class="align-middle">${product.description}</td>
          <td class="text-end align-middle"><fmt:formatNumber value="${product.price}" type="currency" /></td>
          <td class="align-middle"><a href="vendors?id=${product.vendorId}">${product.vendorName}</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>