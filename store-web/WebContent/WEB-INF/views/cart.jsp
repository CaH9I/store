<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp"/>
      <h3>Cart</h3>
      <a class="btn btn-default margin-bottom-20" href="${pageContext.request.contextPath}/product-list">&#8592; Back to product list</a>
      <c:if test="${not empty shoppingCart.items}">
        <form:form method="post" modelAttribute="updateCartForm">
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
              <c:forEach var="entry" items="${shoppingCart.items}" varStatus="status">
                <c:set var="phone" value="${entry.key}"/>
                <c:set var="quantity" value="${entry.value}"/>
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
          <div class="text-right">
            <input type="submit" value="Update" class="btn btn-success"/>
          </div>
        </form:form>
      </c:if>
    </div>
  </body>
</html>