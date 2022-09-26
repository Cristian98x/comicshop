<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user" value="${sessionScope.get('user')}"/>
<c:set var="totalitems" value="${0}"/>
<c:set var="cart" value="${sessionScope.get('cart')}"/>
    <div class="container-fluid">
        <a class="navbar-brand" href="" id="logohome">
            <img src="images/logo.png" width="100" height="100" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="" id="homelink">Home</a>
                </li>

                <c:if test="${user==null}">
                <li class="nav-item">
                    <a class="nav-link" href="" id="registerlink">Register</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="" id="loginlink">Login</a>
                </li>

                </c:if>

                <c:if test="${user!=null}">
                    <li class="nav-item">
                        <a class="nav-link" href="" id="myorderlink">My orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="" id="logoutlink">Logout</a>
                    </li>
                </c:if>
            </ul>
            <c:forEach items="${cart.cartItemDtos}" var="item">
                <c:set var="totalitems" value="${totalitems +item.quantity}"/>
            </c:forEach>
            <ul class="navbar-right">
                <li class="cart"><a href="" id="cart">
                    <lord-icon class="shopping-cart-icon"
                            src="https://cdn.lordicon.com/webtefou.json"
                            trigger="hover"
                            colors="primary:#c79816"
                            >
                    </lord-icon><span class="badge">${totalitems}</span></a></li>
            </ul>

        </div>
    </div>
