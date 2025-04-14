<div class="container mx-auto py-4">
  <div class="flex flex-col">
    <!-- Main content START -->
    <div class="w-full">
      <!-- Title -->
      <h1 class="text-2xl font-bold mb-2">All Users</h1>
      <p class="text-lg text-gray-600 mb-4">
        <c:choose>
          <c:when test="${users.size() == 1}">There is 1 user</c:when>
          <c:otherwise>There are ${users.size()} users</c:otherwise>
        </c:choose>
      </p>
      <c:if test="${users.size() > 0}">
        <div class="overflow-x-auto">
          <table class="min-w-full bg-white border border-gray-200">
            <thead class="bg-gray-50">
            <tr>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">First name</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Last name</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Phone</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Language</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Privileges</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Created At</th>
              <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Timezone</th>
            </tr>
            </thead>
            <tbody class="divide-y divide-gray-200">
            <c:forEach items="${users}" var="user">
              <tr>
                <td class="px-6 py-4 whitespace-nowrap text-black">
                  <a href="edit-user?user_id=${user.userId}" class="text-indigo-600 hover:text-indigo-900 mr-2">Edit</a>
                  <a href="delete-user?user_id=${user.userId}" class="text-red-600 hover:text-red-900">Delete</a>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.firstName)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.lastName)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.email)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.phone)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.language)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.status)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.privileges)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.createdAt)}</td>
                <td class="px-6 py-4 whitespace-nowrap text-black">${fn:escapeXml(user.timezone)}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </c:if>
    </div> <!-- Col END -->
  </div> <!-- Row END -->
</div> <!-- Container END -->