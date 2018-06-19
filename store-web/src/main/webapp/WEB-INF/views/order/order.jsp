<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<app:template>
    <h3><s:message code="order.title"/></h3>
    <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${s:mvcUrl('CC#cart').build()}">&#8592;&nbsp;<s:message code="navigation.cart"/></a>
    </div>
    <c:if test="${not empty order.orderItems}">
        <app:orderTable order="${order}"/>
        <form:form modelAttribute="orderForm" method="post" class="form-horizontal col-lg-5 col-md-6 col-sm-8 col-xs-12">
            <div class="form-group">
                <s:message code="order.firstName" var="firstName"/>
                <form:label path="firstName" class="col-xs-4 control-label text-left">${firstName}</form:label>
                <div class="col-xs-8">
                    <form:input path="firstName" class="form-control" cssErrorClass="form-control error-input" placeholder="${firstName}"/>
                    <form:errors path="firstName" cssClass="error-text"/>
                </div>
            </div>
            <div class="form-group">
                <s:message code="order.lastName" var="lastName"/>
                <form:label path="lastName" class="col-xs-4 control-label text-left">${lastName}</form:label>
                <div class="col-xs-8">
                    <form:input path="lastName" class="form-control" cssErrorClass="form-control error-input" placeholder="${lastName}"/>
                    <form:errors path="lastName" cssClass="error-text"/>
                </div>
            </div>
            <div class="form-group">
                <s:message code="order.address" var="address"/>
                <form:label path="address" class="col-xs-4 control-label text-left">${address}</form:label>
                <div class="col-xs-8">
                    <form:input path="address" class="form-control" cssErrorClass="form-control error-input" placeholder="${address}"/>
                    <form:errors path="address" cssClass="error-text"/>
                </div>
            </div>
            <div class="form-group">
                <s:message code="order.phone" var="phone"/>
                <form:label path="phoneNumber" class="col-xs-4 control-label text-left">${phone}</form:label>
                <div class="col-xs-8">
                    <form:input path="phoneNumber" class="form-control" cssErrorClass="form-control error-input" placeholder="${phone}"/>
                    <form:errors path="phoneNumber" cssClass="error-text"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12">
                    <s:message code="order.notes" var="notes"/>
                    <form:textarea path="additionalInfo" class="form-control" cssErrorClass="form-control error-input" placeholder="${notes}" rows="4"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="submit" class="btn btn-success" value="<s:message code='general.button.order.place'/>"/>
                </div>
            </div>
        </form:form>
    </c:if>
</app:template>