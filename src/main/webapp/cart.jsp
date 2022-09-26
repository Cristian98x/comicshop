<%--
  Created by IntelliJ IDEA.
  User: crbe1606
  Date: 5/18/2022
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="total" value="${0.0}"/>
<c:set var="totalProduct" value="${0.0}"/>
<c:set var="cart" value="${cart}"/>
<div class="container">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%">Product</th>
            <th style="width:10%">Price</th>
            <th style="width:8%">Quantity</th>
            <th style="width:22%" class="text-center">Subtotal</th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <tr>
        <c:forEach items="${cart.cartItemDtos}" var="item">
            <td data-th="Product">
                <div class="row">
                    <div class="col hidden-xs"><img class="cart-image" src="${item.productDTO.image}" alt="..." /></div>
                    <div class="col">
                        <h4 class="nomargin">${item.productDTO.name}</h4>
                        <p>${item.productDTO.description}</p>
                    </div>
                </div>
            </td>
            <td data-th="Price">${item.productDTO.price}$</td>
            <td data-th="Quantity">
                  <form method="post" action="">
                   <div class="col-lg-2">

                         <div class="input-group">
                                    <span class="input-group-btn">
                                        <button type="button" class="quantity-left-minus btn btn-danger btn-number" id="quantityminus" data-type="minus" data-field="" value="${item.productDTO.id}">
                                          <i class="fa-solid fa-minus"></i>
                                        </button>
                                    </span>
                                        <input type="text" id="quantity" name="quantity" class="form-control input-number" value="${item.quantity}" readonly>
                                    <span class="input-group-btn">
                                        <button type="button" class="quantity-right-plus btn btn-success btn-number" id="quantityplus" data-type="plus" data-field="" value="${item.productDTO.id}">
                                            <i class="fa-solid fa-plus"></i>
                                        </button>
                                    </span>
                            </div>

                   </div>
                  </form>
            </td>
            <td data-th="Subtotal" class="text-center subtotal">${totalProduct+item.productDTO.price*item.quantity}$</td>
            <td class="actions" data-th="">
                <button class="btn btn-danger btn-sm" id="removeitem" value="${item.productDTO.id}"><i class="fa fa-trash"></i></button>
            </td>
        </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr class="visible-xs">
            <td class="text-center"><strong>${cart.totalPrice}$ </strong></td>
        </tr>
        <tr>
            <td colspan="2" class="hidden-xs"></td>
            <td class="hidden-xs text-center"><strong>${cart.totalPrice}$</strong></td>
            <td><a href="#" class="btn btn-success btn-block" id="checkoutlink">Checkout <i class="fa fa-angle-right"></i></a></td>
        </tr>
        </tfoot>
    </table>
</div>
