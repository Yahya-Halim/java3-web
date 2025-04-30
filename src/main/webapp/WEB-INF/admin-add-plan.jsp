<div class="container mx-auto p-6">
    <div class="max-w-xl mx-auto bg-white p-6 rounded shadow">
        <h1 class="text-2xl font-bold mb-4">Add New Plan</h1>

        <c:if test="${not empty planAddedMessage}">
            <div class="mb-4 px-4 py-3 rounded
                <c:choose>
                    <c:when test="${planAdded}">bg-green-100 text-green-800</c:when>
                    <c:otherwise>bg-red-100 text-red-800</c:otherwise>
                </c:choose>">
                ${planAddedMessage}
            </div>
        </c:if>

        <form method="post" action="add-plan" class="space-y-6">
            <!-- Plan ID -->
            <div>
                <label for="planId" class="block text-sm font-medium text-gray-700">Plan ID</label>
                <input type="text" id="planId" name="planId" value="${planId}"
                       class="mt-1 block w-full border text-black <c:if test='${planIdError}'>border-red-500</c:if> rounded p-2" />
                <p class="text-sm <c:if test='${planIdError}'>text-red-600</c:if><c:if test='${!planIdError}'>text-green-600</c:if>">
                    ${planIdMessage}
                </p>
            </div>

            <!-- Plan Name -->
            <div>
                <label for="planName" class="block text-sm font-medium text-gray-700">Plan Name</label>
                <input type="text" id="planName" name="planName" value="${planName}"
                       class="mt-1 block w-full border text-black <c:if test='${planNameError}'>border-red-500</c:if> rounded p-2" />
                <p class="text-sm <c:if test='${planNameError}'>text-red-600</c:if><c:if test='${!planNameError}'>text-green-600</c:if>">
                    ${planNameMessage}
                </p>
            </div>

            <!-- Plan Price -->
            <div>
                <label for="planPrice" class="block text-sm font-medium text-gray-700">Price (USD)</label>
                <input type="number" step="0.01" id="planPrice" name="planPrice" value="${planPrice}"
                       class="mt-1 block w-full border text-black <c:if test='${planPriceError}'>border-red-500</c:if> rounded p-2" />
                <p class="text-sm <c:if test='${planPriceError}'>text-red-600</c:if><c:if test='${!planPriceError}'>text-green-600</c:if>">
                    ${planPriceMessage}
                </p>
            </div>
            <!-- Plan Description -->
            <div>
                <label for="planDescription" class="block text-sm font-medium text-gray-700">Description</label>
                <textarea id="planDescription" name="planDescription"
                          class="mt-1 block w-full border rounded p-2 text-black">${planDescription}</textarea>
            </div>

            <!-- Submit Button -->
            <div>
                <button type="submit" class="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded">
                    Add Plan
                </button>
            </div>
        </form>

        <!-- Back Link -->
        <div class="mt-4 text-center">
            <a href="admin-plans" class="text-blue-600 hover:underline">Back to Plan List</a>
        </div>
    </div>
</div>