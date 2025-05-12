

<div class="container mx-auto py-6 px-4">

    <div class="flex flex-col">
        <div class="w-full">
            <!-- Header -->
            <div class="flex justify-between items-center mb-4">
                <div>
                    <h1 class="text-2xl font-bold">All Doctors</h1>
                    <p class="text-lg text-gray-600">
                        <c:choose>
                            <c:when test="${empty doctors or doctors.size() == 0}">There are 0 doctors</c:when>
                            <c:when test="${doctors.size() == 1}">There is 1 doctor</c:when>
                            <c:otherwise>There are ${doctors.size()} doctors</c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <a href="${appURL}/add-doctor"
                   class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                    + Add Doctor
                </a>
            </div>

            <c:if test="${not empty sessionScope.successMessage}">
                <div class="mb-4 p-4 text-sm text-green-700 bg-green-100 rounded-lg" role="alert">
                    ${fn:escapeXml(sessionScope.successMessage)}
                </div>
                <c:remove var="successMessage" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.errorMessage}">
                <div class="mb-4 p-4 text-sm text-red-700 bg-red-100 rounded-lg" role="alert">
                    ${fn:escapeXml(sessionScope.errorMessage)}
                </div>
                <c:remove var="errorMessage" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.warningMessage}">
                <div class="mb-4 p-4 text-sm text-yellow-700 bg-yellow-100 rounded-lg" role="alert">
                    ${fn:escapeXml(sessionScope.warningMessage)}
                </div>
                <c:remove var="warningMessage" scope="session"/>
            </c:if>

            <!-- Table -->
            <c:if test="${not empty doctors && doctors.size() > 0}">
                <div class="overflow-x-auto shadow rounded">
                    <table class="min-w-full bg-white border border-gray-200">
                        <thead class="bg-gray-50">
                        <tr>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">First Name</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Last Name</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Specialties</th>
                        </tr>
                        </thead>
                        <tbody class="divide-y divide-gray-200">
                            <c:forEach items="${doctors}" var="doctor">
                              <tr>
                                <td class="px-6 py-4 whitespace-nowrap text-black">
                                    <%-- Corrected Edit Link --%>
                                    <a href="${appURL}/admin-update-doctor?doctorId=${doctor.id}" class="text-indigo-600 hover:text-indigo-900 mr-2">Edit</a>

                                    <form method="post" action="${appURL}/admin-delete-doctor"
                                          onsubmit="return confirm('Are you sure you want to delete doctor ${fn:escapeXml(doctor.firstName)} ${fn:escapeXml(doctor.lastName)} (ID: ${doctor.id})?');"
                                          style="display:inline;">
                                        <input type="hidden" name="doctorId" value="${doctor.id}" />
                                        <button type="submit"
                                                class="text-red-600 hover:text-red-900 bg-transparent border-none p-0 cursor-pointer">
                                            Delete
                                        </button>
                                    </form>

                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-black">${doctor.id}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(doctor.firstName)}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(doctor.lastName)}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-black">
                                    <c:forEach items="${doctor.specialties}" var="specialty" varStatus="loop">
                                        ${fn:escapeXml(specialty)}<c:if test="${not loop.last}">, </c:if>
                                    </c:forEach>
                                </td>
                              </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${empty doctors || doctors.size() == 0}">
                <p class="text-lg text-gray-600 text-center mt-4">No doctors found. You can add one using the button above.</p>
            </c:if>
        </div>
    </div>
</div>

<div id="deleteDoctorModal" class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center" style="display: none; z-index: 100;">
    <div class="relative mx-auto p-5 border w-full max-w-md shadow-lg rounded-md bg-white">
        <div class="mt-3 text-center">
            <div class="mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-red-100">
                <svg class="h-6 w-6 text-red-600" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126zM12 15.75h.007v.008H12v-.008z" />
                </svg>
            </div>
            <h3 class="text-lg leading-6 font-medium text-gray-900 mt-2" id="modal-title">Delete Doctor</h3>
            <div class="mt-2 px-7 py-3">
                <p class="text-sm text-gray-500">
                    Are you sure you want to delete doctor <strong id="doctorNameToDelete"></strong>?
                    This action cannot be undone.
                </p>
            </div>
            <form id="deleteDoctorForm" action="${appURL}/admin-delete-doctor" method="post" class="items-center px-4 py-3">
                <input type="hidden" name="doctorId" id="deleteDoctorIdInput" value="" />
                <button id="confirmDeleteButton" type="submit"
                        class="px-4 py-2 bg-red-500 text-white text-base font-medium rounded-md w-auto shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-300">
                    Confirm Delete
                </button>
                <button id="cancelDeleteButton" type="button"
                        class="px-4 py-2 ml-3 bg-gray-200 text-gray-800 text-base font-medium rounded-md w-auto shadow-sm hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-300"
                        onclick="closeDeleteModal()">
                    Cancel
                </button>
            </form>
        </div>
    </div>
</div>