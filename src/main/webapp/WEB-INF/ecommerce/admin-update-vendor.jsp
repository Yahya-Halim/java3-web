
<div class="container py-4">
  <a href="vendors" class="btn btn-primary mb-4" role="button">View All Vendors</a>
  <h2>Admin - Update Vendor</h2>
  <c:choose>
    <c:when test="${empty vendor}">
      <p class="lead">No vendor found</p>
    </c:when>
    <c:otherwise>
      <c:if test="${not empty vendorUpdated}">
        <div class="alert <c:choose><c:when test="${vendorUpdated == true}">alert-success</c:when><c:otherwise>alert-danger</c:otherwise></c:choose>" role="alert">
            ${vendorUpdatedMessage}
        </div>
      </c:if>
      <form class="row g-3" method="POST" action="edit-vendor?vend_id=${vend_id}">
        <div class="col-md-3">
          <label for="vendorId" class="form-label">Vendor Id</label>
          <input disabled type="text" class="form-control <c:choose><c:when test="${vendorIdError == true}">is-invalid</c:when><c:when test="${vendorIdError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="vendorId" value="${vend_id}">
          <input type="hidden" name="vendorId" value="${vend_id}">
          <div class="<c:choose><c:when test="${vendorIdError == true}">invalid-feedback</c:when><c:when test="${vendorIdError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${vendorIdMessage}
          </div>
        </div>
        <div class="col-md-9">
          <label for="vendorName" class="form-label">Vendor name</label>
          <input type="text" class="form-control <c:choose><c:when test="${vendorNameError == true}">is-invalid</c:when><c:when test="${vendorNameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="vendorName" name="vendorName" value="${vendor.vend_name}">
          <div class="<c:choose><c:when test="${vendorNameError == true}">invalid-feedback</c:when><c:when test="${vendorNameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${vendorNameMessage}
          </div>
        </div>

        <div class="col-md-4">
          <label for="country" class="form-label">Country Abbreviation</label>
          <input type="text" class="form-control <c:choose><c:when test="${countryError == true}">is-invalid</c:when><c:when test="${countryError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="country" name="country" value="${vendor.address.country}" maxlength="3">
          <div class="<c:choose><c:when test="${countryError == true}">invalid-feedback</c:when><c:when test="${countryError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${countryMessage}
          </div>
        </div>
        <div class="col-md-8">
          <label for="streetAddress" class="form-label">Street Address</label>
          <input type="text" class="form-control <c:choose><c:when test="${streetAddressError == true}">is-invalid</c:when><c:when test="${streetAddressError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="streetAddress" name="streetAddress" value="${vendor.address.address}">
          <div class="<c:choose><c:when test="${streetAddressError == true}">invalid-feedback</c:when><c:when test="${streetAddressError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${streetAddressMessage}
          </div>
        </div>

        <div class="col-md-4">
          <label for="city" class="form-label">City</label>
          <input type="text" class="form-control <c:choose><c:when test="${cityError == true}">is-invalid</c:when><c:when test="${cityError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="city" name="city" value="${vendor.address.city}">
          <div class="<c:choose><c:when test="${cityError == true}">invalid-feedback</c:when><c:when test="${cityError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${cityMessage}
          </div>
        </div>
        <div class="col-md-4">
          <label for="state" class="form-label">State Abbreviation</label>
          <input type="text" class="form-control <c:choose><c:when test="${stateError == true}">is-invalid</c:when><c:when test="${stateError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="state" name="state" value="${vendor.address.state}" maxlength="2">
          <div class="<c:choose><c:when test="${stateError == true}">invalid-feedback</c:when><c:when test="${stateError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${stateMessage}
          </div>
        </div>
        <div class="col-md-4">
          <label for="zip" class="form-label">Zip</label>
          <input type="text" class="form-control <c:choose><c:when test="${zipError == true}">is-invalid</c:when><c:when test="${zipError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="zip" name="zip" value="${vendor.address.zip}">
          <div class="<c:choose><c:when test="${zipError == true}">invalid-feedback</c:when><c:when test="${zipError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${zipMessage}
          </div>
        </div>

        <div class="col-12">
          <button class="btn btn-dark" type="submit">Submit form</button>
        </div>
      </form>
    </c:otherwise>
  </c:choose>
</div>


