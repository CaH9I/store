<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp"/>
      <h3>Order</h3>
      <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${pageContext.request.contextPath}/cart">&#8592; Back to cart</a>
      </div>
      <c:set var="order" value="${shoppingCart.order}" scope="request"/>
      <c:if test="${not empty order.commerceItems}">
        <jsp:include page="include/orderTable.jsp"/>
        <form:form modelAttribute="orderForm" method="post" class="form-horizontal col-lg-4 col-md-5 col-sm-6 col-xs-8">
          <div class="form-group">
            <form:label path="firstName" class="col-xs-4 control-label text-left">First name</form:label>
            <div class="col-xs-8">
              <form:input path="firstName" class="form-control" cssErrorClass="form-control error-input" placeholder="First name"/>
              <form:errors path="firstName" cssClass="error-text"/>
            </div>
          </div>
          <div class="form-group">
            <form:label path="lastName" class="col-xs-4 control-label text-left">Last Name</form:label>
            <div class="col-xs-8">
              <form:input path="lastName" class="form-control" cssErrorClass="form-control error-input" placeholder="Last name"/>
              <form:errors path="lastName" cssClass="error-text"/>
            </div>
          </div>
          <div class="form-group">
            <form:label path="address" class="col-xs-4 control-label text-left">Address</form:label>
            <div class="col-xs-8">
              <form:input path="address" class="form-control" cssErrorClass="form-control error-input" placeholder="Address"/>
              <form:errors path="address" cssClass="error-text"/>
            </div>
          </div>
          <div class="form-group">
            <form:label path="phoneNumber" class="col-xs-4 control-label text-left">Phone</form:label>
            <div class="col-xs-8">
              <form:input path="phoneNumber" class="form-control" cssErrorClass="form-control error-input" placeholder="Phone"/>
              <form:errors path="phoneNumber" cssClass="error-text"/>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-12">
              <form:textarea path="additionalInfo" class="form-control" cssErrorClass="form-control error-input" placeholder="Additiotal information" rows="4"/>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-12">
              <input type="submit" class="btn btn-success" value="Order"/>
            </div>
          </div>
        </form:form>
      </c:if>
    </div>
  </body>
</html>