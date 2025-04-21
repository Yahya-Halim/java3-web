<div>
  <form action="subscription" method="post" class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <c:forEach var="plan" items="${plans}">
            <div class="bg-white shadow-xl rounded-2xl p-4 text-center hover:transition-all duration-200">
                <h2 class="text-2xl font-bold text-blue-700">${plan.name} Plan</h2>
                <p class="text-xl text-gray-800 my-2">$${plan.price} / month</p>
                <p class="text-gray-600 mb-4">${plan.description}</p>
                <button
                    type="submit"
                    name="plan"
                    value="${plan.name.toLowerCase()}"
                    class="w-full py-2 px-4 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700"
                >
                    Choose ${plan.name}
                </button>
            </div>
        </c:forEach>
    </form>
</div>