<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<app:template>
    <h3><s:message code="orders.title"/></h3>
    <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${s:mvcUrl('PLC#productList').build()}">&#8592;&nbsp;<s:message code="navigation.home"/></a>
    </div>
    <c:if test="${not empty orders}">
        <table class="table table-bordered table-striped table">
            <thead>
                <tr class="bg-primary">
                    <th><s:message code="order.id"/></th>
                    <th><s:message code="order.firstName"/></th>
                    <th><s:message code="order.lastName"/></th>
                    <th><s:message code="order.address"/></th>
                    <th><s:message code="order.phone"/></th>
                    <th><s:message code="order.state"/></th>
                    <th><s:message code="order.notes"/></th>
                    <th><s:message code="order.total"/></th>
                    <th><s:message code="general.action"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td><a href="${s:mvcUrl('AODC#orderDetail').arg(0, order.id).build()}">#${order.id}</a></td>
                        <td>${order.firstName}</td>
                        <td>${order.lastName}</td>
                        <td>${order.address}</td>
                        <td>${order.phoneNumber}</td>
                        <td>${order.state}</td>
                        <td>${order.additionalInfo}</td>
                        <td><app:price price="${order.total}"/></td>
                        <td>
                            <s:eval var="delivered" expression="T(com.expertsoft.core.model.entity.OrderState).DELIVERED"/>
                            <c:if test="${order.state ne delivered}">
                                <form:form method="put" action="${s:mvcUrl('AHC#changeOrderState').arg(0, order.id).arg(1, delivered).build()}">
                                    <input type="submit" value="<s:message code='order.state.set' arguments='${delivered}'/>"
                                           class="btn btn-primary col-xs-12 margin-bottom-15"/>
                                </form:form>
                            </c:if>
                            <form:form method="delete" action="${s:mvcUrl('AHC#deleteOrder').arg(0, order.id).build()}">
                                <input type="submit" value="<s:message code='general.button.delete'/>" class="btn btn-danger col-xs-12">
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</app:template>