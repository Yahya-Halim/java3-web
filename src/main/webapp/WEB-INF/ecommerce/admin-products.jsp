
<div class="container py-4">
  <h2>Admin - all products</h2>
  <div class="table-responsive small">
    <table class="table table-striped table-sm">
      <thead>
      <tr>
        <th scope="col">Edit/Delete</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Description</th>
        <th scope="col">Vendor</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${products}" var="product">
      <tr>
        <td>
          <a href="edit-product?prod_id=${product.prod_id}" class="btn btn-sm btn-outline-primary">Edit</a>
          <a href="delete-product?prod_id=${product.prod_id}" class="btn btn-sm btn-outline-danger">Delete</a>


        </td>
        <td>${product.prod_name}</td>
        <td><fmt:formatNumber value="${product.prod_price}" type="currency" /></td>
        <td>${product.prod_desc}</td>
        <td><a href="view-vendor?vend_id=${product.vend_id}">${product.vend_name}</a></td>
      </tr>
      </c:forEach>

      </tbody>
    </table>
  </div>

</div>

