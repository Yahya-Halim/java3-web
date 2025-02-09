<div class="container col-xl-10 col-xxl-8 px-4 py-5">
    <div class="row align-items-center g-lg-5 py-5">
        <div class="col-lg-7 text-center text-lg-start">
            <h1 class="display-4 fw-bold lh-1 text-body-emphasis mb-3">Vertically centered hero sign-up form</h1>
            <p class="col-lg-10 fs-4">
                Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.
            </p>
        </div>
        <div class="col-md-10 mx-auto col-lg-5">
            <form method="POST" action="${appURL}/signup" class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
                <div class="card p-4">
                    <h2 class="card-title text-center">Sign Up</h2>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email address</label>
                        <input type="text" class="form-control <c:if test="${not empty emailError}">is-invalid</c:if>" id="email" name="email" value="${email}" placeholder="name@example.com">
                        <c:if test="${not empty emailError}"><div class="invalid-feedback">${emailError}</div></c:if>
                    </div>

                    <div class="mb-3">
                        <label for="password1" class="form-label">Password</label>
                        <input type="password" class="form-control <c:if test="${not empty password1Error}">is-invalid</c:if>" id="password1" name="password1" placeholder="Password">
                        <c:if test="${not empty password1Error}"><div class="invalid-feedback">${password1Error}</div></c:if>
                    </div>

                    <div class="mb-3">
                        <label for="password2" class="form-label">Confirm Password</label>
                        <input type="password" class="form-control <c:if test="${not empty password2Error}">is-invalid</c:if>" id="password2" name="password2" placeholder="Confirm Password">
                        <c:if test="${not empty password2Error}"><div class="invalid-feedback">${password2Error}</div></c:if>
                    </div>

                    <div class="form-check mb-3">
                        <input type="checkbox" class="form-check-input <c:if test="${not empty termsError}">is-invalid</c:if>" value="agree" id="terms" name="terms" <c:if test="${terms eq 'agree'}">checked</c:if>>
                        <label class="form-check-label" for="terms">Agree to the <a href="${appURL}/terms">Terms of Service</a></label>
                        <c:if test="${not empty termsError}"><div class="invalid-feedback">${termsError}</div></c:if>
                    </div>

                    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
                    <hr class="my-4">
                    <small class="text-body-secondary">Already have an account? <a href="${appURL}/login">Log in</a></small>
                </div>
            </form>
        </div>
    </div>
</div>