
<div class="container py-4">
  <h2>Orders</h2>
  <p class="lead">There ${fn:length(orders) == 1 ? "is" : "are"}&nbsp;${fn:length(orders)} order${fn:length(orders) != 1 ? "s" : ""}</p>
  <div class="table-responsive small">
    <table class="table table-striped table-sm" style="max-width: 800px">
      <thead>
      <tr>
        <th scope="col"></th>
        <th scope="col">Order Date</th>
        <th scope="col">Customer</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${orders}" var="order">
        <tr>
          <td>
            <a href="update-order?id=${order.orderNum}" class="btn btn-outline-primary" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Update</a>
            <a href="delete-order?id=${order.orderNum}" class="btn btn-outline-danger" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Delete</a>
          </td>
          <td class="align-middle"><fmt:formatDate value="${order.orderDateDate}" type="both" dateStyle="full" timeStyle="long"></fmt:formatDate></td>
          <td class="align-middle"><a href="view-customer?cust-id=${order.customerId}">${order.customerName}</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
