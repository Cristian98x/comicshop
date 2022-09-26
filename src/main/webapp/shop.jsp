<%--
  Created by IntelliJ IDEA.
  User: crbe1606
  Date: 5/17/2022
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="menu-wrapper d-flex justify-content-center">
    <ul class="menu-category" id="selectcategory">
        <c:forEach var="category" items="${category}">
            <li><a class="btn" href="${category.name}">${category.name}</a></li>
        </c:forEach>
    </ul>
</div>
<div class="container" id="productshop">

</div>