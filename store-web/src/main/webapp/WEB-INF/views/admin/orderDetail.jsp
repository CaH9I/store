<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<app:template>
    <h3><s:message code="order.detail" arguments="${order.id}"/></h3>
    <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${s:mvcUrl('AHC#adminHome').build()}">&#8592;&nbsp;<s:message code="navigation.back"/></a>
    </div>
    <app:orderTable order="${order}"/>
    <app:orderInfo order="${order}" displayState="${true}"/>
    <s:eval var="delivered" expression="T(com.expertsoft.core.model.entity.OrderState).DELIVERED"/>
    <c:if test="${order.state ne delivered}">
        <form:form method="post" action="${s:mvcUrl('AODC#changeOrderState').arg(0, order.id).arg(1, delivered).build()}">
            <input type="hidden" name="_method" value="PUT"/>
            <div class="form-group">
                <input type="submit" value="<s:message code='order.state.set' arguments='${delivered}'/>" class="btn btn-primary"/>
            </div>
        </form:form>
    </c:if>
    <form:form method="post" action="${s:mvcUrl('AHC#deleteOrder').arg(0, order.id).build()}">
        <input type="hidden" name="_method" value="DELETE"/>
        <div class="form-group">
            <input type="submit" value="<s:message code='general.button.delete'/>" class="btn btn-danger"/>
        </div>
    </form:form>
</app:template>