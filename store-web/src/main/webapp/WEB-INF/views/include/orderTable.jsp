<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="app"%>

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
        <td><a href="${s:mvcUrl('PDC#productDetail').arg(0, phone.id).build()}">${phone.model}</a></td>
        <td>${phone.color}</td>
        <td>${phone.display}</td>
        <td>${quantity}</td>
        <td><app:price price="${ci.price * quantity}"/></td>
      </tr>
    </c:forEach>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="3" rowspan="3" class="no-border"></td>
      <td>Subtotal</td>
      <td><app:price price="${order.subtotal}"/></td>
    </tr>
    <tr>
      <td>Delivery</td>
      <td><app:price price="${order.delivery}"/></td>
    </tr>
    <tr>
      <td>Total</td>
      <td><app:price price="${order.total}"/></td>
    </tr>
  </tfoot>
</table>