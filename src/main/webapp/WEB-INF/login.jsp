<div class="h-screen flex items-center justify-center bg-cover bg-center" style="background-image: url('https://images.pexels.com/photos/1668246/pexels-photo-1668246.jpeg');">
  <div class="relative w-full max-w-md p-8 bg-gray-800/50 backdrop-blur-lg rounded-xl shadow-lg">
    <h2 class="text-3xl font-bold text-white text-center mb-6">Login</h2>

    <c:if test="${not empty loginFail}">
      <div class="mb-4 text-red-600 bg-red-100 p-3 rounded-lg text-center">${loginFail}</div>
    </c:if>

    <form method="post" action="${appURL}/login" class="space-y-4">
      <div class="relative">
        <label for="email" class="absolute left-3 top-3 text-gray-400 transition-all duration-300 pointer-events-none">Email address</label>
        <input type="text" id="email" name="email" class="w-full h-12 bg-transparent border border-gray-300 px-4 rounded-lg focus:ring-2 focus:ring-purple-500 text-white placeholder-transparent focus:placeholder-gray-400" placeholder="Email address" value="${email}">
      </div>
      <div class="relative">
        <label for="password" class="absolute left-3 top-3 text-gray-400 transition-all duration-300 pointer-events-none">Password</label>
        <input type="password" id="password" name="password" class="w-full h-12 bg-transparent border border-gray-300 px-4 rounded-lg focus:ring-2 focus:ring-purple-500 text-white placeholder-transparent focus:placeholder-gray-400" placeholder="Password" value="${password}">
      </div>
      <div class="flex items-center">
        <input type="checkbox" id="rememberMe" name="rememberMe" class="h-4 w-4 text-purple-500 focus:ring-purple-400 border-gray-300 rounded" value="true" ${rememberMe eq 'true' ? 'checked' : ''}>
        <label for="rememberMe" class="ml-2 text-sm text-white">Remember me for 30 days</label>
      </div>
      <button type="submit" class="w-full h-12 bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring-2 focus:ring-purple-300 transition duration-300">Sign in</button>
    </form>
    <p class="mt-4 text-center text-sm text-white">Don't have an account? <a href="${appURL}/signup" class="text-purple-400 hover:text-purple-600">Sign-up</a></p>
  </div>
</div>

<script>
  document.querySelectorAll('input').forEach(input => {
    input.addEventListener('focus', function() {
      this.previousElementSibling.classList.add('invisible');
    });
    input.addEventListener('blur', function() {
      if (!this.value) {
        this.previousElementSibling.classList.remove('invisible');
      }
    });
  });
</script>
