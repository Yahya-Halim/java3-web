
<div class="main-container">
    <div class="form-wrapper">
        <div class="form-title">Sign Up</div>
        <c:if test="${not empty userAddFail}">
            <div class="alert alert-danger mb-2">${userAddFail}</div>
        </c:if>
        <form method="POST" action="${appURL}/signup">
            <div class="field">
                <input class="inp <c:if test="${not empty emailError}">is-invalid</c:if>" type="text" id="email" name="email" value="${email}" >
                <label class="label" for="email">Email address</label>
                <span class="bi bi-envelope"></span>
                <c:if test="${not empty emailError}"><div class="invalid-feedback">${emailError}</div></c:if>
            </div>

            <div class="field">
                <input class="inp <c:if test="${not empty password1Error}">is-invalid</c:if>" type="password" id="password1" name="password1">
                <label class="label" for="password1">Password</label>
                <span class="toggle-pass bi bi-eye"></span>
                <c:if test="${not empty password1Error}"><div class="invalid-feedback">${password1Error}</div></c:if>
            </div>

            <div class="field">
                <input class="inp <c:if test="${not empty password2Error}">is-invalid</c:if>" type="password" id="password2" name="password2" >
                <label class="label" for="password2">Confirm Password</label>
                <span class="toggle-pass bi bi-eye"></span>
                <c:if test="${not empty password2Error}"><div class="invalid-feedback">${password2Error}</div></c:if>
            </div>

            <div class="form-checkbox mb-3">
                <input type="checkbox" class="<c:if test="${not empty termsError}">is-invalid</c:if>" value="agree" id="terms" name="terms" <c:if test="${terms eq 'agree'}">checked</c:if>>
                <label for="terms">Agree to the <a href="${appURL}/terms">Terms of Service</a></label>
                <c:if test="${not empty termsError}"><div class="invalid-feedback">${termsError}</div></c:if>
            </div>

            <!-- reCAPTCHA Widget -->
            <div class="g-recaptcha" data-sitekey="6LfRe9oqAAAAAIOxxur3EtZjGRmq82ue8jmkTXPK"></div>

            <input type="submit" value="Sign up" id="login-btn">

            <small style="color: rgba(11, 255, 72, 0.75)">Already have an account? <a href="${appURL}/login">Log in</a></small>
        </form>
        <div class="separator">or</div>
        <div class="alternative">
            <button class="bi bi-twitter-x bi-primary"></button>
            <button class="bi bi-pinterest bi-primary"></button>
            <button class="bi bi-google bi-primary"></button>
            <button class="bi bi-facebook bi-primary"></button>
            <button class="bi bi-discord bi-primary"></button>
        </div>
    </div>
    <div class="bg"></div>
</div>

<!-- Load reCAPTCHA script -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<style>
    @import url('https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css');

    .main-container {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        min-width: 500px;
    }

    a {
        text-decoration: none;
        color: rgb(255, 255, 255);
    }

    a:hover {

    }

    .form-wrapper {
        width: 420px;
        z-index: 1;
        color: #ffffff;
        text-align: center;
        border-radius: 10px;
        font-family: 'Calibri';
        padding: 20px 20px 5px;
        backdrop-filter: blur(12px);
        border: 1px solid #ffffff4f;
        box-shadow: 0px 0px 20px 0px #00000070;
    }

    .form-title {
        font-size: 30px;
    }

    .field {
        position: relative;
        margin-top: 45px;
    }

    .field .bi {
        position: absolute;
        right: 5px;
        bottom: 5px;
        color: #ffffff;
    }

    .toggle-pass {
        cursor: pointer;
    }

    form input {
        background-color: transparent;
        border: none;
    }

    .inp {
        width: 100%;
        color: #ffffff;
        font-size: 16px;
        caret-color: #000000;
        outline: none;
        padding: 0 25px 5px 0;
        border-bottom: 1px solid #ffffff54;
    }

    .label {
        position: absolute;
        left: 0px;
        bottom: 5px;
        color: #ffffff;
        transition: transform .3s ease-in-out, color .3s ease-in-out, font-size .3s ease-in-out;
    }

    .inp:focus~label,
    .inp:valid~label {
        transform: translateY(-25px);
        font-size: 15px;
        color: #ffffff;
    }

    .action {
        display: flex;
        justify-content: space-between;
        font-size: 15px;
        color: #000000;
        user-select: none;
        margin-top: 10px;
    }

    .action label {
        display: flex;
        gap: 5px;
        cursor: pointer;
    }

    #save-info {
        cursor: pointer;
    }

    #login-btn {
        cursor: pointer;
        background-color: rgba(6, 56, 163, 0.46);
        width: 100%;
        color: #ffffff;
        border-radius: 5px;
        margin-top: 40px;
        font-size: 18px;
        font-family: 'Calibri';
        padding: 5px 0;
    }

    .separator {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 10px;
        margin-top: 30px;
    }

    .separator::before,
    .separator::after {
        content: '';
        background: #ffffff4f;
        width: 40%;
        height: 1px;
    }

    .alternative {
        display: flex;
        gap: 12px;
        justify-content: center;
        margin-top: 15px;
    }

    .alternative button {
        border: none;
        font-size: 14px;
        height: 40px;
        width: 40px;
        border-radius: 8px;
        cursor: pointer;
        background: #00000033;
        color: #eefcfd;
    }

    #login-btn:hover,
    .alternative button:hover {
        opacity: 0.8;
    }



    .bg {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 0;
        opacity: 0.8;
        background-image: url('https://images.pexels.com/photos/1668246/pexels-photo-1668246.jpeg');
        background-size: cover;
        background-repeat: no-repeat;
    }

</style>
<script>
    const input = document.querySelector('#password');
    const icon = document.querySelector('.toggle-pass');
    icon.addEventListener('click', () => {
        icon.classList.toggle('bi-eye-slash');
        icon.classList.toggle('bi-eye');
        input.type = (input.type === 'password') ? 'text' : 'password';
    });
</script>