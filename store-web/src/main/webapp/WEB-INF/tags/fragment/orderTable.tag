<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<%@attribute name="order" type="com.expertsoft.core.model.entity.Order" required="true"%>

<table class="table table-bordered no-border">
    <thead>
        <tr class="bg-primary">
            <th><s:message code="product.model"/></th>
            <th><s:message code="product.color"/></th>
            <th><s:message code="product.display"/></th>
            <th><s:message code="product.quantity"/></th>
            <th><s:message code="product.price"/></th>
         </tr>
    </thead>
    <tbody>
        <c:forEach var="ci" items="${order.orderItems}">
            <c:set var="phone" value="${ci.phone}"/>
            <c:set var="quantity" value="${ci.quantity}"/>
            <tr>
                <td><a href="${s:mvcUrl('PDC#productDetail').arg(0, phone.id).build()}">${phone.model}</a></td>
                <td>${phone.color}</td>
                <td>${phone.display}</td>
                <td>${quantity}</td>
                <td><app:price price="${ci.price * quantity}"/></td>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="3" rowspan="3" class="no-border"></td>
            <td><s:message code="order.subtotal"/></td>
            <td><app:price price="${order.subtotal}"/></td>
        </tr>
        <tr>
            <td><s:message code="order.delivery"/></td>
            <td><app:price price="${order.delivery}"/></td>
        </tr>
        <tr>
            <td><s:message code="order.total"/></td>
            <td><app:price price="${order.total}"/></td>
        </tr>
    </tfoot>
</table>