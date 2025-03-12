
<div class="container py-4">
  <a href="vendors" class="btn btn-primary mb-4" role="button">View All Vendors</a>
  <h2 style="font-size: 2.5rem;">Add New Vendor</h2>
  <c:if test="${not empty vendorAdded}">
    <div class="alert <c:choose><c:when test="${vendorAdded == true}">alert-success</c:when><c:otherwise>alert-danger</c:otherwise></c:choose>" role="alert">
        ${vendorAddedMessage}
    </div>
  </c:if>
  <form class="row g-3" method="POST" action="add-vendor">
    <div class="col-md-3">
      <label for="vendorId" class="form-label">Vendor Id</label>
      <input type="text" class="form-control <c:choose><c:when test="${vendorIdError == true}">is-invalid</c:when><c:when test="${vendorIdError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="vendorId" name="vendorId" value="${vendorId}">
      <div class="<c:choose><c:when test="${vendorIdError == true}">invalid-feedback</c:when><c:when test="${vendorIdError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${vendorIdMessage}
      </div>
    </div>
    <div class="col-md-9">
      <label for="vendorName" class="form-label">Vendor name</label>
      <input type="text" class="form-control <c:choose><c:when test="${vendorNameError == true}">is-invalid</c:when><c:when test="${vendorNameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="vendorName" name="vendorName" value="${vendorName}">
      <div class="<c:choose><c:when test="${vendorNameError == true}">invalid-feedback</c:when><c:when test="${vendorNameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${vendorNameMessage}
      </div>
    </div>

    <div class="col-md-4">
      <label for="country" class="form-label">Country Abbreviation</label>
      <input type="text"
             class="form-control
                  <c:choose>
                      <c:when test="${countryError == true}">is-invalid</c:when>
                      <c:when test="${countryError == false}">is-valid</c:when>
                  </c:choose>"
             id="country"
             name="country"
             value="${country}"
             maxlength="3"
             oninput="updateStateField()">
      <div class="<c:choose>
                  <c:when test='${countryError == true}'>invalid-feedback</c:when>
                  <c:when test='${countryError == false}'>valid-feedback</c:when>
              </c:choose>">
        ${countryMessage}
      </div>
    </div>
    <div class="col-md-8">
      <label for="streetAddress" class="form-label">Street Address</label>
      <input type="text" class="form-control <c:choose><c:when test="${streetAddressError == true}">is-invalid</c:when><c:when test="${streetAddressError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="streetAddress" name="streetAddress" value="${streetAddress}">
      <div class="<c:choose><c:when test="${streetAddressError == true}">invalid-feedback</c:when><c:when test="${streetAddressError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${streetAddressMessage}
      </div>
    </div>
    <div class="col-md-4">
      <label for="city" class="form-label">City</label>
      <input type="text" class="form-control <c:choose><c:when test="${cityError == true}">is-invalid</c:when><c:when test="${cityError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="city" name="city" value="${city}">
      <div class="<c:choose><c:when test="${cityError == true}">invalid-feedback</c:when><c:when test="${cityError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${cityMessage}
      </div>
    </div>
    <div class="col-md-4">
      <label for="state" class="form-label">State Abbreviation</label>
      <input type="text"
             class="form-control
                  <c:choose>
                      <c:when test="${stateError == true}">is-invalid</c:when>
                      <c:when test="${stateError == false}">is-valid</c:when>
                  </c:choose>"
             id="state"
             name="state"
             value="${state}"
             maxlength="2"
             <c:if test="${country != 'US' && country != 'USA'}">disabled</c:if>>
      <div class="<c:choose>
                  <c:when test="${stateError == true}">invalid-feedback</c:when>
                  <c:when test="${stateError == false}">valid-feedback</c:when>
              </c:choose>">
        ${stateMessage}
      </div>
    </div>


    <div class="col-md-4">
      <label for="zip" class="form-label">Zip</label>
      <input type="text" class="form-control <c:choose><c:when test="${zipError == true}">is-invalid</c:when><c:when test="${zipError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="zip" name="zip" value="${zip}">
      <div class="<c:choose><c:when test="${zipError == true}">invalid-feedback</c:when><c:when test="${zipError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${zipMessage}
      </div>
    </div>
    <div class="col-12">
      <button class="btn btn-dark" type="submit">Submit form</button>
    </div>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

<script>
  function updateStateField() {
    const country = document.getElementById('country').value.toUpperCase();

    const stateField = document.getElementById('state');

    if (country === 'US' || country === 'USA' ) {
      stateField.disabled = false;
    } else {
      stateField.disabled = true;
      stateField.value = '';
    }
  }
  document.addEventListener('DOMContentLoaded', updateStateField);
</script>

