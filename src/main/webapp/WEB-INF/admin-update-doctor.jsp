<div class="container mx-auto p-6">
        <div class="max-w-xl mx-auto bg-white p-6 rounded shadow">
            <h1 class="text-2xl font-bold mb-4 text-gray-800">Update Doctor</h1>

            <c:if test="${not empty formMessage}">
                <div class="mb-4 px-4 py-3 rounded
                    <c:choose>
                        <c:when test="${formError}">bg-red-100 text-red-800</c:when>
                        <c:otherwise>bg-blue-100 text-blue-800</c:otherwise>
                    </c:choose>">
                    ${fn:escapeXml(formMessage)}
                </div>
            </c:if>

            <form action="${appURL}/admin-update-doctor" method="post" class="space-y-6">
                <input type="hidden" name="doctorId" value="${fn:escapeXml(doctor.id)}" />

                <!-- First Name Field -->
                <div>
                    <label for="firstName" class="block text-sm font-medium text-gray-700">First Name</label>
                    <input type="text" id="firstName" name="firstName"
                           value="${fn:escapeXml(not empty requestScope.firstName ? requestScope.firstName : doctor.firstName)}"
                           class="mt-1 block w-full border text-black <c:if test='${firstNameError}'>border-red-500</c:if> <c:if test='${!firstNameError && firstNameValid}'>border-green-500</c:if> rounded p-2" />
                    <c:if test="${not empty firstNameMessage}">
                        <p class="text-sm <c:if test='${firstNameError}'>text-red-600</c:if><c:if test='${!firstNameError && firstNameValid}'>text-green-600</c:if> mt-1">
                            ${fn:escapeXml(firstNameMessage)}
                        </p>
                    </c:if>
                </div>

                <!-- Last Name Field -->
                <div>
                    <label for="lastName" class="block text-sm font-medium text-gray-700">Last Name</label>
                    <input type="text" id="lastName" name="lastName"
                           value="${fn:escapeXml(not empty requestScope.lastName ? requestScope.lastName : doctor.lastName)}"
                           class="mt-1 block w-full border text-black <c:if test='${lastNameError}'>border-red-500</c:if> <c:if test='${!lastNameError && lastNameValid}'>border-green-500</c:if> rounded p-2" />
                    <c:if test="${not empty lastNameMessage}">
                        <p class="text-sm <c:if test='${lastNameError}'>text-red-600</c:if><c:if test='${!lastNameError && lastNameValid}'>text-green-600</c:if> mt-1">
                            ${fn:escapeXml(lastNameMessage)}
                        </p>
                    </c:if>
                </div>

                 <!-- Specialties Section -->
                <div>
                    <label class="block text-sm font-medium text-gray-700">Specialties</label>
                    <div class="mt-1 space-y-2">
                        <c:set var="submittedSpecialties" value="${paramValues.specialties}" />

                        <c:forEach items="${Specialty}" var="specialty">
                            <div class="flex items-center">
                                <input class="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                                       type="checkbox"
                                       name="specialties"
                                       value="${specialty.id}"
                                       id="specialty-${specialty.name}"
                                       <c:choose>
                                           <%-- After form submission with errors --%>
                                           <c:when test="${not empty firstNameError || not empty lastNameError || not empty specialtiesError}">
                                               <c:forEach items="${submittedSpecialties}" var="submittedSpec">
                                                   <c:if test="${submittedSpec eq specialty.name}">checked</c:if>
                                               </c:forEach>
                                           </c:when>
                                           <%-- Initial page load --%>
                                           <c:otherwise>
                                               <c:forEach items="${doctor.specialties}" var="docSpec">
                                                   <c:if test="${docSpec eq specialty.name}">checked</c:if>
                                               </c:forEach>
                                           </c:otherwise>
                                       </c:choose>
                                >
                                <label class="ml-2 block text-sm text-gray-900" for="specialty-${specialty.name}">
                                    ${fn:escapeXml(specialty.name)}
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
                        Update Doctor
                    </button>
                </div>
            </form>

            <!-- Back Link -->
            <div class="mt-6 text-center">
                <a href="${appURL}/admin-doctor" class="text-blue-600 hover:underline">Back to Doctor List</a>
            </div>
        </div>
    </div>