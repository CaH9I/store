<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp"/>
      <h3 class="margin-bottom-20">Thank for your order</h3>
      <c:if test="${not empty order.commerceItems}">
        <jsp:include page="include/orderTable.jsp"/>
        <jsp:include page="include/orderInfo.jsp"/>
        <div class="col-xs-12 no-padding">
          <a class="btn btn-default" href="${pageContext.request.contextPath}/product-list">&#8592; Back to shopping</a>
        </div>
      </c:if>
    </div>
  </body>
</html>