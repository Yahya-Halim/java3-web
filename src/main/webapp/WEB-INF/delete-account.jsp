<main>
  <%@include file="/WEB-INF/edit-profile-header.jspf"%>
  <section class="pt-0">
    <div class="container">
      <div class="row">
        <%@include file="/WEB-INF/left-sidebar.jspf"%>

        <!-- Main content START -->
        <div class="col-xl-9">
          <!-- Title and select START -->
          <div class="card border bg-transparent rounded-3 mb-0">
            <!-- Card header -->
            <div class="card-header bg-transparent border-bottom">
              <h3 class="card-header-title mb-0 text-white">Delete Account</h3>
            </div>
            <!-- Card body -->
            <div class="card-body">
              <h6 class="form-label text-white">If you delete your account, you will lose all your data.</h6>
              <form id="deleteForm" method="POST" action="${appURL}/delete-account">
                <!-- Email id -->
                <div class="col-md-6 my-4">
                  <label class="form-label text-white" for="email">Enter your email to confirm account deletion</label>
                  <input class="form-control <c:if test="${not empty results.emailError}">is-invalid</c:if>"
                         type="text" id="email" name="email" value="${email}">
                  <c:if test="${not empty results.emailError}">
                    <div class="invalid-feedback">${results.emailError}</div>
                  </c:if>
                </div>

                <!-- Trigger the modal instead of submitting -->
                <button type="button" class="btn btn-danger mb-0" data-bs-toggle="modal" data-bs-target="#confirmDeleteModal">
                  Delete my account
                </button>
              </form>
            </div>
          </div>
          <!-- Title and select END -->
        </div>
        <!-- Main content END -->
      </div><!-- Row END -->
    </div>

    <!-- Modal -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Deletion</h5>
            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body text-black">
            Are you sure you want to delete your account? This action cannot be undone.
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-danger" onclick="document.getElementById('deleteForm').submit();">
              Yes, delete my account
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</main>
