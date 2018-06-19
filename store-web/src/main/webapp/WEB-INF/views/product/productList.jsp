<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<app:template>
    <h3><s:message code="product.title"/></h3>
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
            <c:forEach var="phone" items="${mobilePhones.content}">
                <form:form method="post" class="add-to-cart" action="${s:mvcUrl('RCC#addToCart').build()}">
                    <input type="hidden" name="productId" value="${phone.id}"/>
                    <tr>
                        <td><a href="${s:mvcUrl('PDC#productDetail').arg(0, phone.id).build()}">${phone.model}</a></td>
                        <td>${phone.color}</td>
                        <td>${phone.display}</td>
                        <td><app:price price="${phone.price}"/></td>
                        <td><input name="quantity" value="1" class="form-control" maxlength="4"/></td>
                        <td><input type="submit" value="<s:message code='general.button.addToCart'/>" class="btn btn-success"/></td>
                    </tr>
                </form:form>
            </c:forEach>
        </tbody>
    </table>
    <app:pagination page="${mobilePhones}" mappingName="PLC#productListForPage"/>
    <div id="errors-section"></div>
</app:template>