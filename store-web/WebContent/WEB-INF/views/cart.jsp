<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp">
        <jsp:param name="includeCartFragment" value="true"/>
      </jsp:include>
      <h3>Cart</h3>
      <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${pageContext.request.contextPath}/product-list">&#8592; Back to product list</a>
      </div>
      <c:if test="${not empty shoppingCart.order.commerceItems}">
        <form:form method="post" modelAttribute="updateCartForm">
          <button type="submit" name="checkout" value="true" class="btn btn-success pull-right">Order</button>
          <table class="table table-bordered table-striped">
            <thead>
              <tr class="bg-primary">
                <th>Model</th>
                <th>Color</th>
                <th>Display size</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="ci" items="${shoppingCart.order.commerceItems}">
                <c:set var="phone" value="${ci.phone}"/>
                <c:set var="quantity" value="${ci.quantity}"/>
                <tr>
                  <td><a href="${pageContext.request.contextPath}/product-detail/${phone.id}">${phone.model}</a></td>
                  <td>${phone.color}</td>
                  <td>${phone.display}</td>
                  <td><fmt:formatNumber value="${phone.price * quantity}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
                  <td>
                    <form:input path="items[${phone.id}]" class="form-control" cssErrorClass="form-control error-input" maxlength="9"/>
                    <form:errors path="items[${phone.id}]" cssClass="error-text"/>
                  </td>
                  <td><button type="submit" name="productToRemoveId" value="${phone.id}" class="btn btn-default">Delete</button></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <div class="text-right form-inline">
            <div class="form-group">
              <input type="submit" value="Update" class="btn btn-primary"/>
            </div>
            <div class="form-group">
              <button type="submit" name="checkout" value="true" class="btn btn-success">Order</button>
            </div>
          </div>
        </form:form>
      </c:if>
    </div>
  </body>
</html>