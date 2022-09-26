<%--
  Created by IntelliJ IDEA.
  User: crbe1606
  Date: 5/16/2022
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container product">
    <div class="row d-flex justify-content-center" id="productplace">
        <c:forEach items="${product}" var="product" >
        <figure class='snip1418'>
        <img class="productimg" src="${product.image}"   alt=''/>
        <div class="add-to-cart"><i class="ion-android-add"></i><button id ="add-product-cart" class="add-product-cart" href="#" value="${product.id}">ADD TO CART</button></div>
        <figcaption>
             <h3> ${product.name}</h3>
            <p> ${product.description}</p>
            <div class="price">${product.price}$</div>
            </figcaption>
        </figure>
        </c:forEach>
    </div>
</div>