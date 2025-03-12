
<div class="container py-9">
  <h2>Admin - All Customer</h2>
  <div class="table-responsive large">
    <table class="table table-striped table-sm" style="max-width: 1000px">
      <thead>
      <tr>
        <th scope="col">Edit/Delete</th>
        <th scope="col">Customer Name</th>
        <th scope="col">Email</th>
        <th scope="col">Contact Name</th>
        <th scope="col">Address</th>
        <th scope="col">City</th>
        <th scope="col">State</th>
        <th scope="col">Zip</th>
        <th scope="col">Country</th>




      </tr>
      </thead>
      <tbody>
      <c:forEach items="${customers}" var="customer">
        <tr>
          <td>
            <a href="edit-customer?cust_id=${customer.cust_id}" class="btn btn-sm btn-outline-primary">Edit</a>
            <a href="delete-customer?cust_id=${customer.cust_id}" class="btn btn-sm btn-outline-danger">Delete</a>
          </td>
          <td class="text">${customer.cust_name}</td>
          <td class="text">${customer.cust_email}</td>
          <td class="text">${customer.cust_contact}</td>
          <td class="text">${customer.cust_address}</td>
          <td class="text">${customer.cust_city}</td>
          <td class="text">${customer.cust_state}</td>
          <td class="text">${customer.cust_zip}</td>
          <td class="text">${customer.cust_country}</td>
          <td></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

