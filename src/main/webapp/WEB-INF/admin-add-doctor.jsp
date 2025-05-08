<div class="container mx-auto p-6">
    <div class="max-w-xl mx-auto bg-white p-6 rounded shadow">
        <h1 class="text-2xl font-bold mb-4 text-gray-800">Add New Doctor</h1>

        <%-- Display overall success or error message --%>
        <c:if test="${not empty doctorAddedMessage}">
            <div class="mb-4 px-4 py-3 rounded
                <c:choose>
                    <c:when test="${doctorAdded}">bg-green-100 text-green-800</c:when>
                    <c:otherwise>bg-red-100 text-red-800</c:otherwise>
                </c:choose>">
                ${fn:escapeXml(doctorAddedMessage)}
            </div>
        </c:if>

        <form action="${appURL}/add-doctor" method="post" class="space-y-6">
            <!-- First Name -->
            <div>
                <label for="firstName" class="block text-sm font-medium text-gray-700">First Name</label>
                <input type="text" id="firstName" name="firstName" value="${fn:escapeXml(firstName)}"
                       class="mt-1 block w-full border text-black <c:if test='${firstNameError}'>border-red-500</c:if> rounded p-2" />
                <c:if test="${not empty firstNameMessage}">
                    <p class="text-sm <c:if test='${firstNameError}'>text-red-600</c:if><c:if test='${!firstNameError && firstNameValid}'>text-green-600</c:if> mt-1">
                        ${fn:escapeXml(firstNameMessage)}
                    </p>
                </c:if>
            </div>

            <!-- Last Name -->
            <div>
                <label for="lastName" class="block text-sm font-medium text-gray-700">Last Name</label>
                <input type="text" id="lastName" name="lastName" value="${fn:escapeXml(lastName)}"
                       class="mt-1 block w-full border text-black <c:if test='${lastNameError}'>border-red-500</c:if> rounded p-2" />
                <c:if test="${not empty lastNameMessage}">
                    <p class="text-sm <c:if test='${lastNameError}'>text-red-600</c:if><c:if test='${!lastNameError && lastNameValid}'>text-green-600</c:if> mt-1">
                        ${fn:escapeXml(lastNameMessage)}
                    </p>
                </c:if>
            </div>

            <!-- Specialties -->
            <div>
                <label class="block text-sm font-medium text-gray-700">Specialties</label>
                <div class="mt-1 space-y-2">
                   <c:forEach items="${Specialty}" var="s">
                        <div class="flex items-center">
                            <input class="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                                   type="checkbox" name="specialties" value="${s.id}" id="specialty-${s.id}"
                                   <c:if test="${not empty selectedSpecialties}">
                                       <c:forEach var="selectedSpec" items="${selectedSpecialties}">
                                           <c:if test="${selectedSpec eq s.id}">checked</c:if>
                                       </c:forEach>
                                   </c:if>
                            >
                            <label class="ml-2 block text-sm text-gray-900" for="specialty-${s.id}">
                                ${fn:escapeXml(s.name)}
                            </label>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${not empty specialtiesMessage}">
                    <p class="text-sm <c:if test='${specialtiesError}'>text-red-600</c:if><c:if test='${!specialtiesError && specialtiesValid}'>text-green-600</c:if> mt-1">
                        ${fn:escapeXml(specialtiesMessage)}
                    </p>
                </c:if>
            </div>

            <!-- Submit Button -->
            <div>
                <button type="submit" class="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded">
                    Add Doctor
                </button>
            </div>
        </form>

        <!-- Back Link -->
        <div class="mt-6 text-center">
            <a href="${appURL}/admin-doctor" class="text-blue-600 hover:underline">Back to Doctor List</a>
        </div>
    </div>
</div>