<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
  	<div class="container">
  	  <jsp:include page="include/header.jsp"/>
  	  <h3>Cart</h3>
  	  <a class="btn btn-default margin-bottom-20" href="${pageContext.request.contextPath}/product-list">&#8592; Back to product list</a>
  	  <c:if test="${not empty shoppingCart.items}">
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
  	        <c:forEach var="entry" items="${shoppingCart.items}">
  	          <c:set var="phone" value="${entry.key}"/>
  	          <c:set var="quantity" value="${entry.value}"/>
  	          <tr>
  	            <td><a href="${pageContext.request.contextPath}/product-detail/${phone.id}">${phone.model}</a></td>
  	            <td>${phone.color}</td>
  	            <td>${phone.display}</td>
  	            <td><fmt:formatNumber value="${phone.price * quantity}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
  	            <td><input name="quantity" value="${quantity}" class="form-control"/></td>
  	            <td>
  	              <form method="post">
  	                <input type="hidden" name="productId" value="${phone.id}"/>
  	                <input type="submit" value="Delete" class="btn btn-success"/>
  	              </form>
  	            </td>
  	          </tr>
  	        </c:forEach>
  	      </tbody>
  	    </table>
  	  </c:if>
  	</div>
  </body>
</html>