<div class="container py-4">
    <h2>Orders</h2>
    <p class="lead">There ${fn:length(orders) == 1 ? "is" : "are"}&nbsp;${fn:length(orders)} order${fn:length(orders) != 1 ? "s" : ""}</p>
    <div class="table-responsive small">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Order Date</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Email</th>
                <th scope="col">Address</th>
                <th scope="col">City</th>
                <th scope="col">State</th>
                <th scope="col">Zip</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>
                        <a href="update-order?id=${order.orderID}" class="btn btn-outline-primary" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Update</a>
                        <a href="refund-order?id=${order.orderID}" class="btn btn-outline-danger" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">Refund</a>
                        <a href="order-details?id=${order.orderID}" class="btn btn-outline-success" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">View Details</a>
                    </td>
                    <td class="align-middle"><fmt:formatDate value="${order.orderDateDate}" type="date" dateStyle="full"></fmt:formatDate></td>
                    <td class="align-middle">${fn:escapeXml(order.shipFirstName)}</td>
                    <td class="align-middle">${fn:escapeXml(order.shipLastName)}</td>
                    <td class="align-middle">${fn:escapeXml(order.shipEmail)}</td>
                    <td class="align-middle">${fn:escapeXml(order.shipAddress)}</td>
                    <td class="align-middle">${fn:escapeXml(order.shipCity)}</td>
                    <td class="align-middle">${fn:escapeXml(order.shipState)}</td>
                    <td class="align-middle">${fn:escapeXml(order.shipZipCode)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>