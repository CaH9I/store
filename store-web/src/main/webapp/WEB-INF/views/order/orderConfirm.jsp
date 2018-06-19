<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<app:template>
    <h3 class="margin-bottom-20"><s:message code="order.thanks"/></h3>
    <c:if test="${not empty order.orderItems}">
        <app:orderTable order="${order}"/>
        <app:orderInfo order="${order}"/>
        <div class="col-xs-12 no-padding">
            <a class="btn btn-default" href="${s:mvcUrl('PLC#productList').build()}">&#8592;&nbsp;<s:message code="navigation.productList"/></a>
        </div>
    </c:if>
</app:template>