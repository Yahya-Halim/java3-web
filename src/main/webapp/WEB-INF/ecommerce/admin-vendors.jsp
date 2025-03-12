<div class="container py-4">
  <a href="${appURL}/add-vendor" class="btn btn-primary" role="button">Add Vendor</a>
  <h1>Vendors</h1>
  <p class="lead">There ${fn:length(vendors) == 1 ? "is" : "are"}&nbsp;${fn:length(vendors)} vendor${fn:length(vendors) != 1 ? "s" : ""}</p>
  <div class="table-responsive large">
    <table class="table table-striped table-lg">
      <thead>
      <tr>
        <th scope="col">Edit/Delete</th>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Address</th>
        <th scope="col">City</th>
        <th scope="col">State</th>
        <th scope="col">Zipcode</th>
        <th scope="col">Country</th>
      </thead>
      <tbody>
      <c:forEach items="${vendors}" var="vendor">
        <tr>
          <td>
            <a href="${appURL}/update-vendor?id=${vendor.vend_id}" class="btn btn-sm btn-outline-primary">Update</a>
            <a href="${appURL}/delete-vendor?id=${vendor.vend_id}" class="btn btn-sm btn-outline-danger">Delete</a>
          </td>
          <td>${vendor.vend_id}</td>
          <td><a href="${appURL}/view-vendor?id=${vendor.vend_id}">${vendor.vend_name}</a></td>
          <td>${vendor.address.address}</td>
          <td>${vendor.address.city}</td>
          <td>${vendor.address.state}</td>
          <td>${vendor.address.zip}</td>
          <td>${vendor.address.country}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
