
<div class="flex justify-center items-center min-h-screen bg-cover bg-no-repeat relative"
     style="background-image: url('https://images.pexels.com/photos/1428277/pexels-photo-1428277.jpeg');">
    <div class="w-[420px] text-white text-center rounded-lg p-5 backdrop-blur-md border border-white/30 shadow-lg">
        <h2 class="text-2xl mb-5">Sign Up</h2>

        <c:if test="${not empty userAddFail}">
            <div class="mb-4 text-red-600 bg-red-100 p-3 rounded-lg">${userAddFail}</div>
            <c:remove var="userAddFail" scope="session" />
        </c:if>

        <form method="POST" action="${appURL}/signup">
            <div class="mb-4">
                <label class="block text-sm font-medium" for="email">Email address</label>
                <input class="w-full h-12 bg-transparent border border-gray-300 px-4 rounded-lg focus:ring-2 focus:ring-purple-500 text-white placeholder-transparent focus:placeholder-gray-400"
                       type="text" id="email" name="email" value="${email}">
                <c:if test="${not empty emailError}">
                    <div class="text-red-500 text-sm mt-1">${emailError}</div>
                </c:if>
            </div>

            <div class="mb-4 relative">
                <label class="block text-sm font-medium" for="password1">Password</label>
                <input class="w-full h-12 bg-transparent border border-gray-300 px-4 rounded-lg focus:ring-2 focus:ring-purple-500 text-white placeholder-transparent focus:placeholder-gray-400"
                       type="password" id="password1" name="password1"
                       onfocus="document.getElementById('password-requirements').classList.remove('hidden')"
                       onblur="document.getElementById('password-requirements').classList.add('hidden')">

                <div id="password-requirements"
                     class="hidden absolute right-[-200px] top-1/2 transform -translate-y-1/2 w-52 p-3 bg-white text-black border border-gray-300 rounded-lg shadow-md text-sm">
                    <strong>Password must include:</strong>
                    <ul class="mt-1 space-y-1">
                        <li id="length" class="text-red-500">✔ 8+ characters</li>
                        <li id="uppercase" class="text-red-500">✔ 1 uppercase</li>
                        <li id="lowercase" class="text-red-500">✔ 1 lowercase</li>
                        <li id="number" class="text-red-500">✔ 1 number</li>
                        <li id="special" class="text-red-500">✔ 1 special character</li>
                    </ul>
                </div>

                <c:if test="${not empty password1Error}">
                    <div class="text-red-500 text-sm mt-1">${password1Error}</div>
                </c:if>
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium" for="password2">Confirm Password</label>
                <input class="w-full h-12 bg-transparent border border-gray-300 px-4 rounded-lg focus:ring-2 focus:ring-purple-500 text-white placeholder-transparent focus:placeholder-gray-400"
                       type="password" id="password2" name="password2">
                <c:if test="${not empty password2Error}">
                    <div class="text-red-500 text-sm mt-1">${password2Error}</div>
                </c:if>
            </div>

            <div class="flex items-center gap-2 mb-3">
                <input type="checkbox" class="cursor-pointer" value="agree" id="terms" name="terms"
                       <c:if test="${terms eq 'agree'}">checked</c:if>>
                <label for="terms" class="text-sm">Agree to the
                    <a href="${appURL}/terms" class="text-blue-400 hover:underline">Terms of Service</a>
                </label>
            </div>

            <div class="g-recaptcha" data-sitekey="6LfVodsqAAAAAI-90Yq0nkE8mBHXbtc8PHDAmZ0v"></div>

            <input type="submit" value="Sign up"
                   class="w-full mt-4 bg-blue-700/50 text-white py-2 rounded-md text-lg hover:opacity-80 transition cursor-pointer">

            <small class="block mt-3 text-green-400">
                Already have an account? <a href="${appURL}/login" class="text-blue-400 hover:underline">Log in</a>
            </small>
        </form>

        <div class="flex items-center gap-2 mt-5 text-white">
            <span class="w-full h-px bg-white"></span> or <span class="w-full h-px bg-white"></span>
        </div>

        <div class="flex justify-center gap-3 mt-4">
            <button class="bi bi-twitter-x bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-pinterest bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-google bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-facebook bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-discord bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
        </div>
    </div>
</div>


<!-- Include Google reCAPTCHA script -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<script>
    const passwordInput = document.getElementById("password1");
    const requirementsBox = document.getElementById("password-requirements");
    const lengthCheck = document.getElementById("length");
    const uppercaseCheck = document.getElementById("uppercase");
    const lowercaseCheck = document.getElementById("lowercase");
    const numberCheck = document.getElementById("number");
    const specialCheck = document.getElementById("special");

    passwordInput.addEventListener("focus", function () {
        requirementsBox.classList.remove("hidden");
    });

    passwordInput.addEventListener("blur", function () {
        requirementsBox.classList.add("hidden");
    });

    passwordInput.addEventListener("input", function () {
        const password = passwordInput.value;

        lengthCheck.classList.toggle("text-green-500", password.length >= 8);
        lengthCheck.classList.toggle("text-red-500", password.length < 8);

        uppercaseCheck.classList.toggle("text-green-500", /[A-Z]/.test(password));
        uppercaseCheck.classList.toggle("text-red-500", !/[A-Z]/.test(password));

        lowercaseCheck.classList.toggle("text-green-500", /[a-z]/.test(password));
        lowercaseCheck.classList.toggle("text-red-500", !/[a-z]/.test(password));

        numberCheck.classList.toggle("text-green-500", /[0-9]/.test(password));
        numberCheck.classList.toggle("text-red-500", !/[0-9]/.test(password));

        specialCheck.classList.toggle("text-green-500", /[!@#$%^&*(),.?":{}|<>]/.test(password));
        specialCheck.classList.toggle("text-red-500", !/[!@#$%^&*(),.?":{}|<>]/.test(password));
    });
</script>
