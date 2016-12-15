<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="page-header flex-center">
  <div class="col-xs-6 flex-center">
    <img src="${pageContext.request.contextPath}/img/phone-ico.jpg" height="50px"/>
    <span class="text-logo">Phonify</span>
  </div>
  <div class="col-xs-6">
    <a href="${pageContext.request.contextPath}/cart">
      <span id="cart-item" class="cart-item pull-right bg-primary">
        My Cart:
        <span class="item-number">${shoppingCart.numberOfItems} items</span>
        <span class="item-price">
          <fmt:formatNumber value="${shoppingCart.subtotal}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/>
        </span>
      </span>
    </a>
  </div>
</div>