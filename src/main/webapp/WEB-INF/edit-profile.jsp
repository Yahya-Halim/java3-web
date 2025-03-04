<main class="bg-gray-900 text-white">
  <%@ include file="/WEB-INF/edit-profile-header.jspf" %>

  <!-- Page content START -->
  <section class="pt-0">
    <div class="container mx-auto px-4">
      <div class="row flex flex-wrap">
        <%@ include file="/WEB-INF/left-sidebar.jspf" %>

        <!-- Main content START -->
        <div class="w-full lg:w-9/12">
          <!-- Edit profile START -->
          <div class="card bg-transparent border border-gray-700 rounded-lg">
            <!-- Card header -->
            <div class="card-header bg-gray-800 border-b border-gray-700 p-4">
              <h3 class="text-xl font-semibold mb-0 text-white">Edit Profile</h3>
            </div>
            <!-- Card body START -->
            <div class="card-body p-4">
              <!-- Form -->
              <form class="space-y-4">

                <!-- First name -->
                <div class="flex flex-col space-y-2">
                  <label class="text-sm font-medium text-white" for="firstName">First Name</label>
                  <input class="form-input bg-gray-700 border border-gray-600 rounded-lg p-2 text-white <c:if test="${not empty firstNameError}">is-invalid</c:if>" type="text" id="firstName" name="firstName">
                  <c:if test="${not empty firstNameError}">
                    <div class="invalid-feedback text-red-400 text-sm mt-1">${firstNameError}</div>
                  </c:if>
                </div>

                <!-- Last name -->
                <div class="flex flex-col space-y-2">
                  <label class="text-sm font-medium text-white" for="lastName">Last Name</label>
                  <input type="text" class="form-input bg-gray-700 border border-gray-600 rounded-lg p-2 text-white <c:if test="${not empty lastNameError}">is-invalid</c:if>" id="lastName" name="lastName">
                  <c:if test="${not empty lastNameError}">
                    <div class="invalid-feedback text-red-400 text-sm mt-1">${lastNameError}</div>
                  </c:if>
                </div>

                <!-- Email id -->
                <div class="flex flex-col space-y-2">
                  <label class="text-sm font-medium text-white" for="email">Email</label>
                  <input class="form-input bg-gray-700 border border-gray-600 rounded-lg p-2 text-white <c:if test="${not empty emailError}">is-invalid</c:if>" type="text" id="email" name="email">
                  <c:if test="${not empty emailError}">
                    <div class="invalid-feedback text-red-400 text-sm mt-1">${emailError}</div>
                  </c:if>
                </div>

                <!-- Phone number -->
                <div class="flex flex-col space-y-2">
                  <label class="text-sm font-medium text-white" for="phone">Phone number</label>
                  <input type="text" class="form-input bg-gray-700 border border-gray-600 rounded-lg p-2 text-white <c:if test="${not empty phoneError}">is-invalid</c:if>" id="phone" name="phone">
                  <c:if test="${not empty phoneError}">
                    <div class="invalid-feedback text-red-400 text-sm mt-1">${phoneError}</div>
                  </c:if>
                </div>

                <!-- Select option -->
                <div class="flex flex-col space-y-2">
                  <!-- Language Preference -->
                  <label class="text-sm font-medium text-white" for="language">Language</label>
                  <select class="form-select bg-gray-700 border border-gray-600 rounded-lg p-2 text-white <c:if test="${not empty languageError}">is-invalid</c:if>" id="language" name="language">
                    <option value="en-US">English</option>
                    <option value="es-MX">Spanish</option>
                    <option value="fr-FR">French</option>
                  </select>
                  <c:if test="${not empty languageError}">
                    <div class="invalid-feedback text-red-400 text-sm mt-1">${languageError}</div>
                  </c:if>
                </div>

                <!-- Save button -->
                <div class="flex justify-end">
                  <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg">Save changes</button>
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