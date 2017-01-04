<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp"/>
      <h3>Order detail</h3>
      <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin">&#8592; Back </a>
      </div>
      <jsp:include page="include/orderTable.jsp"/>
      <jsp:include page="include/orderInfo.jsp"/>
      <div class="col-xs-12 no-padding margin-bottom-15">
        <form:form method="POST">
          <button type="submit" name="orderToRemoveId" value="${order.id}" class="btn btn-danger width-50">Delete</button>
        </form:form>
      </div>
    </div>
  </body>
</html>