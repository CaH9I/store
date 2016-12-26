<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp"/>
      <c:if test="${not empty orderList}">
        <h3>Orders</h3>
        <table class="table table-bordered table-striped">
          <thead>
            <tr class="bg-primary">
              <td>Order id</td>
              <td>First name</td>
              <td>Last name</td>
              <td>Address</td>
              <td>Phone number</td>
              <td>Additional info</td>
              <td>Total</td>
              <td>Action</td>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${orderList}">
              <tr>
                <td><a href="${pageContext.request.contextPath}/admin/${order.id}">Order #${order.id}</a></td>
                <td>${order.firstName}</td>
                <td>${order.lastName}</td>
                <td>${order.address}</td>
                <td>${order.phoneNumber}</td>
                <td>${order.additionalInfo}</td>
                <td><fmt:formatNumber value="${order.total}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
                <td>
                  <form:form method="POST">
                    <button type="submit" name="orderToRemoveId" value="${order.id}" class="btn btn-danger">Delete</button>
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