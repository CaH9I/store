<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="app"%>

<app:template>
    <jsp:include page="include/header.jsp"/>
    <h3 class="margin-bottom-20">Thank for your order</h3>
    <c:if test="${not empty order.commerceItems}">
        <jsp:include page="include/orderTable.jsp"/>
        <jsp:include page="include/orderInfo.jsp"/>
        <div class="col-xs-12 no-padding">
            <a class="btn btn-default" href="${s:mvcUrl('PLC#productList').build()}">&#8592; Back to shopping</a>
        </div>
    </c:if>
</app:template>