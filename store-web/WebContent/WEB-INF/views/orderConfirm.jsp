<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp"/>
      <h3 class="margin-bottom-20">Thank for your order</h3>
      <c:set var="order" value="${shoppingCart.lastOrder}" scope="request"/>
      <c:if test="${not empty order.commerceItems}">
        <jsp:include page="include/orderTable.jsp"/>
        <div class="col-xs-12 no-padding">
          <div class="col-lg-4 col-md-5 col-sm-6 col-xs-8 no-padding">
            <div class="row form-group">
              <div class="col-xs-4">First name</div>
              <div class="col-xs-8">${order.firstName}</div>
            </div>
            <div class="row form-group">
              <div class="col-xs-4">Last name</div>
              <div class="col-xs-8">${order.lastName}</div>
            </div>
            <div class="row form-group">
              <div class="col-xs-4">Address</div>
              <div class="col-xs-8">${order.address}</div>
            </div>
            <div class="row form-group">
              <div class="col-xs-4">Phone</div>
              <div class="col-xs-8">${order.phoneNumber}</div>
            </div>
            <c:if test="${not empty order.additionalInfo}">
              <div class="row form-group">
                <div class="col-xs-12">${order.additionalInfo}</div>
              </div>
            </c:if>
          </div>
        </div>
        <div class="col-xs-12 no-padding">
          <a class="btn btn-default" href="${pageContext.request.contextPath}/product-list">&#8592; Back to shopping</a>
        </div>
      </c:if>
    </div>
  </body>
</html>