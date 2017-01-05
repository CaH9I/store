<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="/WEB-INF/views/include/header.jsp"/>
      <h3>Order detail</h3>
      <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin">&#8592; Back </a>
      </div>
      <jsp:include page="/WEB-INF/views/include/orderTable.jsp"/>
      <jsp:include page="/WEB-INF/views/include/orderInfo.jsp">
        <jsp:param name="displayState" value="true"/>
      </jsp:include>
      <form:form method="post" class="form-inline">
        <c:if test="${order.state ne 'DELIVERED'}">
          <div class="form-group">
            <button type="submit" name="orderToDeliverId" value="${order.id}" class="btn btn-primary">Change to Delivered</button>
          </div>
        </c:if>
        <div class="form-group">
          <button type="submit" name="orderToRemoveId" value="${order.id}" class="btn btn-danger">Delete</button>
        </div>
      </form:form>
    </div>
  </body>
</html>