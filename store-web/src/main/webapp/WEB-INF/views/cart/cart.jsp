<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<jsp:useBean id="cartView" type="com.expertsoft.core.commerce.ShoppingCartView" scope="request"/>

<app:template>
    <h3><s:message code="cart.title"/></h3>
    <div class="col-xs-6 no-padding margin-bottom-20">
        <a class="btn btn-default" href="${s:mvcUrl('PLC#productList').build()}">&#8592;&nbsp;<s:message code="navigation.productList"/></a>
    </div>
    <c:if test="${not empty cartView.items}">
        <form:form method="post" modelAttribute="updateCartForm">
            <button type="submit" name="checkout" value="true" class="btn btn-success pull-right"><s:message code="general.button.order.proceed"/></button>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr class="bg-primary">
                        <th><s:message code="product.model"/></th>
                        <th><s:message code="product.color"/></th>
                        <th><s:message code="product.display"/></th>
                        <th><s:message code="product.price"/></th>
                        <th><s:message code="product.quantity"/></th>
                        <th><s:message code="general.action"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${cartView.items}">
                        <c:set var="phone" value="${entry.key}"/>
                        <c:set var="quantity" value="${entry.value}"/>
                        <tr>
                            <td><a href="${s:mvcUrl('PDC#productDetail').arg(0, phone.id).build()}">${phone.model}</a></td>
                            <td>${phone.color}</td>
                            <td>${phone.display}</td>
                            <td><app:price price="${phone.price * quantity}"/></td>
                            <td>
                                <form:input path="items[${phone.id}].quantity" class="form-control" cssErrorClass="form-control error-input" maxlength="9"/>
                                <form:errors path="items[${phone.id}].quantity" cssClass="error-text"/>
                            </td>
                            <td>
                                <button type="submit" formaction="${s:mvcUrl('CC#removeCartItem').arg(0, phone.id).build()}" name="_method" value="DELETE" class="btn btn-default">
                                    <s:message code="general.button.delete"/>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="text-right form-inline">
                <div class="form-group">
                    <input type="submit" value="<s:message code='general.button.update'/>" class="btn btn-primary"/>
                </div>
                <div class="form-group">
                    <button type="submit" name="checkout" value="true" class="btn btn-success"><s:message code="general.button.order.proceed"/></button>
                </div>
            </div>
        </form:form>
    </c:if>
</app:template>