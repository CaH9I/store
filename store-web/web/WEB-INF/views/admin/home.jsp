<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
          <a class="btn btn-default" href="${pageContext.request.contextPath}/product-list">&#8592; Back to store</a>
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
                <td><a href="${pageContext.request.contextPath}/admin/${order.id}">#${order.id}</a></td>
                <td>${order.firstName}</td>
                <td>${order.lastName}</td>
                <td>${order.address}</td>
                <td>${order.phoneNumber}</td>
                <td>${order.state}</td>
                <td>${order.additionalInfo}</td>
                <td><app:price price="${order.total}"/></td>
                <td>
                  <form:form method="POST">
                    <c:if test="${order.state ne 'DELIVERED'}">
                      <button type="submit" name="orderToDeliverId" value="${order.id}" class="btn btn-primary col-xs-12 margin-bottom-15">Change to Delivered</button>
                    </c:if>
                    <button type="submit" name="orderToRemoveId" value="${order.id}" class="btn btn-danger col-xs-12">Delete</button>
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