
<div class="main-container flex justify-center items-center min-h-screen min-w-[500px]">
    <div class="form-wrapper w-[420px] z-10 text-white text-center rounded-lg font-calibri p-5 backdrop-blur-md border border-white/30 shadow-lg">
        <div class="form-title text-2xl">Sign Up</div>
        <c:if test="${not empty userAddFail}">
            <div class="alert alert-danger mb-2">${userAddFail}</div>
            <c:remove var="userAddFail" scope="session"/>
        </c:if>
        <form method="POST" action="${appURL}/signup">
            <div class="field relative mt-12">
                <input class="inp w-full text-white text-lg caret-black outline-none p-2 bg-transparent border-b border-white/30 focus:border-white/70 transition-all peer" type="text" id="email" name="email" value="${email}" >
                <label class="label absolute left-0 bottom-2 text-white transition-all peer-placeholder-shown:opacity-100 peer-placeholder-shown:translate-y-0 peer-focus:opacity-0 peer-focus:-translate-y-6 text-sm" for="email">Email address</label>
                <span class="bi bi-envelope absolute right-2 bottom-2 text-white"></span>
                <c:if test="${not empty emailError}">
                    <div class="invalid-feedback">${emailError}</div>
                </c:if>
            </div>

            <div class="field relative mt-12">
                <input class="inp w-full text-white text-lg caret-black outline-none p-2 bg-transparent border-b border-white/30 focus:border-white/70 transition-all peer" type="password" id="password1" name="password1">
                <label class="label absolute left-0 bottom-2 text-white transition-all peer-placeholder-shown:opacity-100 peer-placeholder-shown:translate-y-0 peer-focus:opacity-0 peer-focus:-translate-y-6 text-sm" for="password1">Password</label>
                <span class="toggle-pass bi bi-eye absolute right-2 bottom-2 text-white cursor-pointer"></span>
                <c:if test="${not empty password1Error}">
                    <div class="invalid-feedback">${password1Error}</div>
                </c:if>
            </div>

            <div class="field relative mt-12">
                <input class="inp w-full text-white text-lg caret-black outline-none p-2 bg-transparent border-b border-white/30 focus:border-white/70 transition-all peer" type="password" id="password2" name="password2">
                <label class="label absolute left-0 bottom-2 text-white transition-all peer-placeholder-shown:opacity-100 peer-placeholder-shown:translate-y-0 peer-focus:opacity-0 peer-focus:-translate-y-6 text-sm" for="password2">Confirm Password</label>
                <span class="toggle-pass bi bi-eye absolute right-2 bottom-2 text-white cursor-pointer"></span>
                <c:if test="${not empty password2Error}">
                    <div class="invalid-feedback">${password2Error}</div>
                </c:if>
            </div>

            <div class="form-checkbox mb-3 flex items-center gap-2">
                <input type="checkbox" class="cursor-pointer" value="agree" id="terms" name="terms" <c:if test="${terms eq 'agree'}">checked</c:if>>
                <label for="terms">Agree to the <a href="${appURL}/terms" class="text-blue-400 hover:underline">Terms of Service</a></label>
                <c:if test="${not empty termsError}">
                    <div class="invalid-feedback">${termsError}</div>
                </c:if>
            </div>

            <div class="g-recaptcha" data-sitekey="6LfVodsqAAAAAI-90Yq0nkE8mBHXbtc8PHDAmZ0v"></div>

            <input type="submit" value="Sign up" id="login-btn" class="cursor-pointer bg-blue-700/50 w-full text-white rounded-md mt-10 text-lg font-calibri py-2 hover:opacity-80 transition">

            <small class="text-green-400">Already have an account? <a href="${appURL}/login" class="text-blue-400 hover:underline">Log in</a></small>
        </form>
        <div class="separator flex justify-center items-center gap-2 mt-10 text-white">or</div>
        <div class="alternative flex gap-3 justify-center mt-5">
            <button class="bi bi-twitter-x bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-pinterest bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-google bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-facebook bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
            <button class="bi bi-discord bg-gray-700 text-white p-3 rounded-lg hover:opacity-80"></button>
        </div>
    </div>
    <div class="bg absolute top-0 left-0 w-full h-full z-0 opacity-80 bg-cover bg-no-repeat" style="background-image: url('https://images.pexels.com/photos/1668246/pexels-photo-1668246.jpeg');"></div>
</div>

<!-- Include Google reCAPTCHA script -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<script>
    document.querySelectorAll('.toggle-pass').forEach(toggle => {
        toggle.addEventListener('click', function () {
            let input = this.previousElementSibling;
            this.classList.toggle('bi-eye-slash');
            this.classList.toggle('bi-eye');
            input.type = (input.type === 'password') ? 'text' : 'password';
        });
    });
</script>
