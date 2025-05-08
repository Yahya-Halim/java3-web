

<%--Display a table to show the doctors' names and specialties. Display Edit and Delete buttons that reference the doctor's ID. Add a button to create a new doctor.--%>

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
                                    <a href="${appURL}/update-doctor?id=${doctor.id}" class="text-indigo-600 hover:text-indigo-900 mr-2">Edit</a>
                                    <form method="post" action="${appURL}/delete-doctor?id=${doctor.id}"
                                          onsubmit="return confirm('Are you sure you want to delete this doctor?');"
                                          style="display:inline;">
                                        <input type="hidden" name="id" value="${doctor.id}" />
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