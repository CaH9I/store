<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="/WEB-INF/views/include/header.jsp"/>
      <h3>Order detail</h3>
      <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${s:mvcUrl('AHC#adminHome').build()}">&#8592; Back </a>
      </div>
      <jsp:include page="/WEB-INF/views/include/orderTable.jsp"/>
      <jsp:include page="/WEB-INF/views/include/orderInfo.jsp">
        <jsp:param name="displayState" value="true"/>
      </jsp:include>
      <s:eval var="delivered" expression="T(com.expertsoft.core.model.entity.OrderState).DELIVERED"/>
      <c:if test="${order.state ne delivered}">
        <form:form method="post" action="${s:mvcUrl('AODC#changeOrderState').arg(0, order.id).arg(1, delivered).build()}">
          <input type="hidden" name="_method" value="PUT"/>
          <div class="form-group">
            <input type="submit" value="Change to Delivered" class="btn btn-primary"/>
          </div>
        </form:form>
      </c:if>
      <form:form method="post" action="${s:mvcUrl('AHC#deleteOrder').arg(0, order.id).build()}">
        <input type="hidden" name="_method" value="DELETE"/>
        <div class="form-group">
          <input type="submit" value="Delete" class="btn btn-danger"/>
        </div>
      </form:form>
    </div>
  </body>
</html>