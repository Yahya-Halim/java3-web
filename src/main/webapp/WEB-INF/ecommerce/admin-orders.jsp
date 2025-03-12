
<div class="container py-4">
  <h2>Admin - All Orders</h2>
  <div class="table-responsive small">
    <table class="table table-striped table-sm" style="max-width: 500px">
      <thead>
      <tr>
        <th scope="col">Edit/Delete</th>
        <th scope="col">Order Date</th>
        <th scope="col">Customer</th>
        <th scope="col">Total Sale</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${orders}" var="order">
        <tr>
          <td>
            <a href="edit-order?order_id=${order.order_num}" class="btn btn-sm btn-outline-primary">Edit</a>
            <a href="delete-order?order_id=${order.order_num}" class="btn btn-sm btn-outline-danger">Delete</a>
          </td>
          <td class="text-end">${order.order_date}</td>
          <td><a href="view-customer?cust_id=${order.cust_id}">${order.cust_name}</a></td>
          <td></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
