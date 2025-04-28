<div class="container my-4">
    <div class="row">
        <div class="col-md-6">
            <div class="card bg-light card-body">
                <h2>Thank you for your order</h2>
                <p class="lead">Order #${sessionScope.newOrderId} has been created!</p>
                <c:remove var="newOrderId" scope="session" />
                <p>Thank you for choosing our business. You will shortly receive a confirmation email.</p>
                <a href="${appURL}/shop" class="btn btn-primary">Back to Shop</a>
            </div>
        </div>
    </div>
</div>