<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp"/>
      <h3>Order</h3>
      <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${pageContext.request.contextPath}/cart">&#8592; Back to cart</a>
      </div>
      <c:set var="order" value="${shoppingCart.order}"/>
      <c:if test="${not empty order.commerceItems}">
        <table class="table table-bordered table-striped no-border">
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
                <td><fmt:formatNumber value="${phone.price * quantity}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
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
              <td><fmt:formatNumber value="${order.subtotal + order.delivery}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
            </tr>
          </tfoot>
        </table>
        <form method="post" class="form-horizontal col-lg-4 col-md-5 col-sm-6 col-xs-8">
          <div class="form-group">
            <label class="col-xs-4 control-label text-left">First name</label>
            <div class="col-xs-8">
              <input class="form-control" placeholder="First name"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-4 control-label text-left">Last name</label>
            <div class="col-xs-8">
              <input class="form-control" placeholder="Last name"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-4 control-label text-left">Address</label>
            <div class="col-xs-8">
              <input class="form-control" placeholder="Address"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-4 control-label text-left">Phone</label>
            <div class="col-xs-8">
              <input class="form-control" placeholder="Phone"/>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-12">
              <textarea class="form-control" placeholder="Additiotal information" rows="4"></textarea>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-12">
              <input type="submit" class="btn btn-success" value="Order"/>
            </div>
          </div>
        </form>
      </c:if>
    </div>
  </body>
</html>