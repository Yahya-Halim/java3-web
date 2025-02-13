<%--<div class="container col-xl-10 col-xxl-8 px-4 py-5">--%>
<%--    <div class="row align-items-center g-lg-5 py-5">--%>
<%--        <div class="col-lg-7 text-center text-lg-start">--%>
<%--            <h1 class="display-4 fw-bold lh-1 text-body-emphasis mb-3">Vertically centered hero sign-up form</h1>--%>
<%--            <p class="col-lg-10 fs-4">Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>--%>
<%--        </div>--%>
<%--        <div class="col-md-10 mx-auto col-lg-5">--%>
<%--            <c:if test="${not empty userAddFail}">--%>
<%--                <div class="alert alert-danger mb-2">${userAddFail}</div>--%>
<%--            </c:if>--%>
<%--            <form method="POST" action="${appURL}/signup" class="p-4 p-md-5 border rounded-3 bg-body-tertiary">--%>
<%--                <div class="form-floating mb-3">--%>
<%--                    <input type="text" class="form-control <c:if test="${not empty emailError}">is-invalid</c:if>" id="email" name="email" value="${email}" placeholder="name@example.com">--%>
<%--                    <label for="email">Email address</label>--%>
<%--                    <c:if test="${not empty emailError}"><div class="invalid-feedback">${emailError}</div></c:if>--%>
<%--                </div>--%>
<%--                <div class="form-floating mb-3">--%>
<%--                    <input type="password" class="form-control  <c:if test="${not empty password1Error}">is-invalid</c:if>" id="password1" name="password1" value="${password1}" placeholder="Password">--%>
<%--                    <label for="password1">Password</label>--%>
<%--                    <c:if test="${not empty password1Error}"><div class="invalid-feedback">${password1Error}</div></c:if>--%>
<%--                </div>--%>
<%--                <div class="form-floating mb-3">--%>
<%--                    <input type="password" class="form-control <c:if test="${not empty password2Error}">is-invalid</c:if>" id="password2" name="password2" value="${password2}" placeholder="Confirm Password">--%>
<%--                    <label for="password2">Confirm Password</label>--%>
<%--                    <c:if test="${not empty password2Error}"><div class="invalid-feedback">${password2Error}</div></c:if>--%>
<%--                </div>--%>
<%--                <div class="form-checkbox mb-3">--%>
<%--                    <input type="checkbox" class="<c:if test="${not empty termsError}">is-invalid</c:if>" value="agree" id="terms" name="terms" <c:if test="${terms eq 'agree'}">checked</c:if>>--%>
<%--                    <label for="terms">Agree to the <a href="${appURL}/terms">Terms of Service</a></label>--%>
<%--                    <c:if test="${not empty termsError}"><div class="invalid-feedback">${termsError}</div></c:if>--%>
<%--                </div>--%>
<%--                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>--%>
<%--                <hr class="my-4">--%>
<%--                <small class="text-body-secondary">Already have an account? <a href="${appURL}/login">Log in</a></small>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<div class="main-container">
    <div class="form-wrapper">
        <div class="form-title">Sign Up</div>
        <c:if test="${not empty userAddFail}">--%>
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

            <input type="submit" value="Sign up" id="login-btn">

            <small class="text-body-secondary">Already have an account? <a href="${appURL}/login">Log in</a></small>
        </form>
        <div class="separator">or</div>
        <div class="alternative">
            <button class="bi bi-twitter-x"></button>
            <button class="bi bi-pinterest"></button>
            <button class="bi bi-google"></button>
            <button class="bi bi-facebook"></button>
            <button class="bi bi-discord"></button>
        </div>
    </div>
    <div class="bg"></div>
</div>
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
        z-index: -1;
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
        color: #040404;
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