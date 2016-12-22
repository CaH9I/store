<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="table table-bordered no-border">
  <thead>
    <tr class="bg-primary">
      <th>Model</th>
      <th>Color</th>
      <th>Display size</th>
      <th>Quantity</th>
      <th>Price</th>
     </tr>
  </thead>
  <tbody>
    <c:forEach var="ci" items="${order.commerceItems}">
      <c:set var="phone" value="${ci.phone}"/>
      <c:set var="quantity" value="${ci.quantity}"/>
      <tr>
        <td><a href="${pageContext.request.contextPath}/product-detail/${phone.id}">${phone.model}</a></td>
        <td>${phone.color}</td>
        <td>${phone.display}</td>
        <td>${quantity}</td>
        <td><fmt:formatNumber value="${ci.price * quantity}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
      </tr>
    </c:forEach>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="3" rowspan="3" class="no-border"></td>
      <td>Subtotal</td>
      <td><fmt:formatNumber value="${order.subtotal}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
    </tr>
    <tr>
      <td>Delivery</td>
      <td><fmt:formatNumber value="${order.delivery}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
    </tr>
    <tr>
      <td>Total</td>
      <td><fmt:formatNumber value="${order.total}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
    </tr>
  </tfoot>
</table>