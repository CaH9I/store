<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <jsp:include page="include/head.jsp"/>
  <body>
    <div class="container">
      <jsp:include page="include/header.jsp">
        <jsp:param name="includeCartFragment" value="true"/>
      </jsp:include>
      <a class="btn btn-default" href="${pageContext.request.contextPath}/product-list">&#8592; Back to product list</a>
      <h2>${mobilePhone.model}</h2>
      <div class="col-lg-4 col-md-6 col-sm-8 col-xs-12 no-padding">
        <table class="table table-bordered table-striped">
          <tr>
            <td>Display</td>
            <td>${mobilePhone.display}</td>
          </tr>
          <tr>
            <td>Length</td>
            <td>${mobilePhone.length}</td>
          </tr>
          <tr>
            <td>Width</td>
            <td>${mobilePhone.width}</td>
          </tr>
          <tr>
            <td>Color</td>
            <td>${mobilePhone.color}</td>
          </tr>
          <tr>
            <td>Price</td>
            <td><fmt:formatNumber value="${mobilePhone.price}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></td>
          </tr>
          <tr>
            <td>Camera</td>
            <td>${mobilePhone.camera}</td>
          </tr>
        </table>
        <form:form method="post" class="form-inline add-to-cart margin-bottom-20" action="${pageContext.request.contextPath}/cart/add-to-cart">
          <input type="hidden" name="productId" value="${mobilePhone.id}"/>
          <div class="form-group">
            <input name="quantity" class="form-control" value="1" maxlength="4"/>
          </div>
          <div class="form-group">
            <input type="submit" value="Add to cart" class="btn btn-success"/>
          </div>
        </form:form>
        <div id="errors-section"></div>
      </div>
    </div>
  </body>
</html>