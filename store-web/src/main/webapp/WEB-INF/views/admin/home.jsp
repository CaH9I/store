<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="app"%>

<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="/WEB-INF/views/include/header.jsp"/>
      <c:if test="${not empty orders}">
        <h3>Orders</h3>
        <div class="col-xs-6 no-padding margin-bottom-20">
          <a class="btn btn-default" href="${s:mvcUrl('PLC#productList').build()}">&#8592; Back to store</a>
        </div>
        <table class="table table-bordered table-striped table">
          <thead>
            <tr class="bg-primary">
              <th>Order id</th>
              <th>First name</th>
              <th>Last name</th>
              <th>Address</th>
              <th>Phone number</th>
              <th>State</th>
              <th>Additional info</th>
              <th>Total</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${orders}">
              <tr>
                <td><a href="${s:mvcUrl('AODC#orderDetail').arg(0, order.id).build()}">#${order.id}</a></td>
                <td>${order.firstName}</td>
                <td>${order.lastName}</td>
                <td>${order.address}</td>
                <td>${order.phoneNumber}</td>
                <td>${order.state}</td>
                <td>${order.additionalInfo}</td>
                <td><app:price price="${order.total}"/></td>
                <td>
                  <s:eval var="delivered" expression="T(com.expertsoft.core.model.entity.OrderState).DELIVERED"/>
                  <c:if test="${order.state ne delivered}">
                    <form:form method="post" action="${s:mvcUrl('AHC#changeOrderState').arg(0, order.id).arg(1, delivered).build()}">
                      <input type="hidden" name="_method" value="PUT"/>
                      <input type="submit" value="Change to Delivered" class="btn btn-primary col-xs-12 margin-bottom-15"/>
                    </form:form>
                  </c:if>
                  <form:form method="post" action="${s:mvcUrl('AHC#deleteOrder').arg(0, order.id).build()}">
                    <input type="hidden" name="_method" value="DELETE"/>
                    <input type="submit" value="Delete" class="btn btn-danger col-xs-12">
                  </form:form>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>
    </div>
  </body>
</html>