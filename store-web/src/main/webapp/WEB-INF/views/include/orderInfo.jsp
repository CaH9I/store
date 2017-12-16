<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <c:if test="${param.displayState}">
      <div class="row form-group">
        <div class="col-xs-4">State</div>
        <div class="col-xs-8">${order.state}</div>
      </div>
    </c:if>
    <c:if test="${not empty order.additionalInfo}">
      <div class="row form-group">
        <div class="col-xs-12">${order.additionalInfo}</div>
      </div>
    </c:if>
  </div>
</div>