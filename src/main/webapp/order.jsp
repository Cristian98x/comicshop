<%--
  Created by IntelliJ IDEA.
  User: crbe1606
  Date: 6/8/2022
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cart" value="${sessionScope.get('orders')}"/>

<div class="text-center d-flex justify-content-center">
    <h4>My orders</h4>
</div>
<c:forEach items="${orders}" var="order" >
<table class="table table-striped table-bordered table-order">
    <thead class="thead-dark-order">
    <tr>
        <th scope="col">Order Nr.</th>
        <th scope="col">Status</th>
        <th scope="col">Total price</th>
    </tr>
    </thead>
        <tbody>
        <tr>
            <th scope="row">${order.number}</th>
            <td>${order.status}</td>
            <td>${order.price}</td>
        </tr>
        <tr>
            <td colspan="4">
                <table class="table mb-0">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                    </tr>
                    </thead>
                    <c:forEach items="${order.productHistoryDTO}" var="product" >

                        <tbody>
                        <tr>
                            <th scope="row">${product.name}</th>
                            <td>${product.price}</td>
                            <td>${product.quantity}</td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </td>
        </tr>
        </tbody>
</table>
</c:forEach>
