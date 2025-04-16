<main>
  <%@ include file="/WEB-INF/edit-profile-header.jspf" %>

  <!-- Page content START -->
  <section class="pt-0">
    <div class="container">
      <div class="row">
        <%@ include file="/WEB-INF/left-sidebar.jspf" %>

        <!-- Main content START -->
        <div class="col-lg-9">
          <!-- Edit profile START -->
          <div class="card bg-transparent border rounded-3">
            <!-- Card header -->
            <div class="card-header bg-light border-bottom">
              <h3 class="card-header-title mb-0">Edit Profile</h3>
            </div>
            <!-- Card body START -->
            <div class="card-body">
              <!-- Form -->
              <form class="row g-4" action="${appURL}/edit-profile" method="POST">

                <!-- First name -->
                <div class="col-md-6">
                  <label class="form-label text-white" for="firstName">First Name</label>
                  <input class="form-control ${not empty firstNameError ? 'is-invalid' : ''}"
                         type="text" id="firstName" name="firstName"
                         value="${sessionScope.activeUser.firstName}">
                  <c:if test="${not empty firstNameError}">
                    <div class="invalid-feedback">${firstNameError}</div>
                  </c:if>
                </div>

                <!-- Last name -->
                <div class="col-md-6">
                  <label class="form-label text-white" for="lastName">Last Name</label>
                  <input class="form-control ${not empty lastNameError ? 'is-invalid' : ''}"
                         type="text" id="lastName" name="lastName"
                         value="${sessionScope.activeUser.lastName}">
                  <c:if test="${not empty lastNameError}">
                    <div class="invalid-feedback">${lastNameError}</div>
                  </c:if>
                </div>

                <!-- Email id -->
                <div class="col-md-6">
                  <label class="form-label text-white" for="email">Email</label>
                  <input class="form-control ${not empty emailError ? 'is-invalid' : ''}"
                         type="text" id="email" name="email"
                         value="${not empty email ? email : sessionScope.activeUser.email}">
                  <c:if test="${not empty emailError}">
                    <div class="invalid-feedback">${emailError}</div>
                  </c:if>
                </div>

                <!-- Phone number -->
                <div class="col-md-6">
                  <label class="form-label text-white" for="phone">Phone number</label>
                  <input class="form-control ${not empty phoneError ? 'is-invalid' : ''}"
                         type="text" id="phone" name="phone"
                         value="${not empty phone ? phone : sessionScope.activeUser.phone}">
                  <c:if test="${not empty phoneError}">
                    <div class="invalid-feedback">${phoneError}</div>
                  </c:if>
                </div>

                <!-- Language Preference -->
                <div class="col-md-6">
                  <label class="form-label text-white" for="language">Language</label>
                  <select class="form-select js-choice z-index-9 bg-white ${not empty languageError ? 'is-invalid' : ''}"
                          id="language" name="language">
                    <option value="en-US" ${sessionScope.activeUser.language == 'en-US' ? 'selected' : ''}>English</option>
                    <option value="es-MX" ${sessionScope.activeUser.language == 'es-MX' ? 'selected' : ''}>Spanish</option>
                    <option value="fr-FR" ${sessionScope.activeUser.language == 'fr-FR' ? 'selected' : ''}>French</option>
                  </select>
                  <c:if test="${not empty languageError}">
                    <div class="invalid-feedback">${languageError}</div>
                  </c:if>
                </div>
                <!-- Time Zone -->
                <div class="col-md-6">
                  <label class="form-label text-white" for="timeZone">Time Zone</label>
                  <select class="form-select ${not empty timeZoneError ? 'is-invalid' : ''}" id="timeZone" name="timeZone">
                    <c:forEach var="tz" items="${timeZones}">
                      <option value="${tz}" ${sessionScope.activeUser.timezone == tz ? 'selected' : ''}>${tz}</option>
                    </c:forEach>
                  </select>
                  <c:if test="${not empty timeZoneError}">
                    <div class="invalid-feedback">${timeZoneError}</div>
                  </c:if>
                </div>

                


                <!-- Save button -->
                <div class="d-sm-flex justify-content-end">
                  <button type="submit" class="btn btn-primary mb-0">Save changes</button>
                </div>
              </form>
            </div>
            <!-- Card body END -->
          </div>
          <!-- Edit profile END -->
        </div>
        <!-- Main content END -->
      </div><!-- Row END -->
    </div>
  </section>
</main>
