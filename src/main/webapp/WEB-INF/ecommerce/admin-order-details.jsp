<div class="container py-4">
    <a href="${appURL}/orders" class="btn btn-primary mb-4">View all orders</a>
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body mx-4">
                    <div class="container">
                        <div class="row">
                            <ul class="list-unstyled">
                                <li class="text-black">${fn:escapeXml(order.shipFirstName)}&nbsp;${fn:escapeXml(order.shipLastName)} </li>
                                <li class="text-muted mt-1"><span class="text-black">Order</span> #${order.orderID}</li>
                                <li class="text-black mt-1"><fmt:formatDate value="${order.orderDateDate}" type="date" dateStyle="long" /></li>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-6"><p>Product Name</p></div>
                            <div class="col-2"><p class="float-end">Qty</p></div>
                            <div class="col-2"><p class="float-end">Price</p></div>
                            <div class="col-2"><p class="float-end">Total</p></div>
                            <hr>
                        </div>
                        <c:forEach items="${order.items}" var="item">
                        <div class="row">
                            <div class="col-xl-10">
                                <p>${item.productName}</p>
                            </div>
                            <div class="col-xl-2">
                                <p class="float-end"><fmt:formatNumber value="${item.totalPrice}" type="currency" />
                                </p>
                            </div>
                            <hr>
                        </div>
                        </c:forEach>
                        <div class="row">
                            <div class="col-xl-10">
                                <p>Support</p>
                            </div>
                            <div class="col-xl-2">
                                <p class="float-end">$10.00
                                </p>
                            </div>
                            <hr style="border: 2px solid black;">
                        </div>
                        <div class="row text-black">

                            <div class="col-xl-12">
                                <p class="float-end fw-bold">Total: $10.00
                                </p>
                            </div>
                            <hr style="border: 2px solid black;">
                        </div>
                        <div class="text-center" style="margin-top: 90px;">
                            <a><u class="text-info">View in browser</u></a>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>