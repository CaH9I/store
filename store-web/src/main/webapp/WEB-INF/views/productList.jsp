<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="app"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp">
        <jsp:param name="includeCartFragment" value="true"/>
      </jsp:include>
      <h3>Phones</h3>
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
          <c:forEach var="phone" items="${mobilePhones}">
            <form:form method="post" class="add-to-cart" action="${pageContext.request.contextPath}/cart/add-to-cart">
              <input type="hidden" name="productId" value="${phone.id}"/>
              <tr>
                <td><a href="${pageContext.request.contextPath}/product-detail/${phone.id}">${phone.model}</a></td>
                <td>${phone.color}</td>
                <td>${phone.display}</td>
                <td><app:price price="${phone.price}"></app:price></td>
                <td><input name="quantity" value="1" class="form-control" maxlength="4"/></td>
                <td><input type="submit" value="Add to cart" class="btn btn-success"/></td>
              </tr>
            </form:form>
          </c:forEach>
        </tbody>
      </table>
      <div id="errors-section"></div>
    </div>
  </body>
</html>