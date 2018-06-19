<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<app:template>
    <a class="btn btn-default" href="${s:mvcUrl('PLC#productList').build()}">&#8592;&nbsp;<s:message code="navigation.productList"/></a>
    <h2>${mobilePhone.model}</h2>
    <div class="col-lg-4 col-md-6 col-sm-8 col-xs-12 no-padding">
        <table class="table table-bordered table-striped">
            <tr>
                <td><s:message code="product.display"/></td>
                <td>${mobilePhone.display}</td>
            </tr>
            <tr>
                <td><s:message code="product.length"/></td>
                <td>${mobilePhone.length}</td>
            </tr>
            <tr>
                <td><s:message code="product.width"/></td>
                <td>${mobilePhone.width}</td>
            </tr>
            <tr>
                <td><s:message code="product.color"/></td>
                <td>${mobilePhone.color}</td>
            </tr>
            <tr>
                <td><s:message code="product.price"/></td>
                <td><app:price price="${mobilePhone.price}"/></td>
            </tr>
            <tr>
                <td><s:message code="product.camera"/></td>
                <td>${mobilePhone.camera}</td>
            </tr>
        </table>
        <form:form method="post" class="form-inline add-to-cart margin-bottom-20" action="${s:mvcUrl('RCC#addToCart').build()}">
            <input type="hidden" name="productId" value="${mobilePhone.id}"/>
            <div class="form-group">
                <input name="quantity" class="form-control" value="1" maxlength="4"/>
            </div>
            <div class="form-group">
                <input type="submit" value="<s:message code='general.button.addToCart'/>" class="btn btn-success"/>
            </div>
        </form:form>
        <div id="errors-section"></div>
    </div>
</app:template>