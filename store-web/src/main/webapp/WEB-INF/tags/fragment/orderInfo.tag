<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@attribute name="order" type="com.expertsoft.core.model.entity.Order" required="true"%>
<%@attribute name="displayState" type="java.lang.Boolean"%>

<div class="col-xs-12 no-padding">
    <div class="col-lg-5 col-md-6 col-sm-8 col-xs-12 no-padding">
        <div class="row form-group">
            <div class="col-xs-4"><s:message code="order.firstName"/></div>
            <div class="col-xs-8">${order.firstName}</div>
        </div>
        <div class="row form-group">
            <div class="col-xs-4"><s:message code="order.lastName"/></div>
            <div class="col-xs-8">${order.lastName}</div>
        </div>
        <div class="row form-group">
            <div class="col-xs-4"><s:message code="order.address"/></div>
            <div class="col-xs-8">${order.address}</div>
        </div>
        <div class="row form-group">
            <div class="col-xs-4"><s:message code="order.phone"/></div>
            <div class="col-xs-8">${order.phoneNumber}</div>
        </div>
        <c:if test="${displayState}">
            <div class="row form-group">
                <div class="col-xs-4"><s:message code="order.state"/></div>
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