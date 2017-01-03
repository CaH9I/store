<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="app"%>

<div class="page-header">
  <div class="flex-center">
    <div class="col-xs-6 flex-center">
      <img src="${pageContext.request.contextPath}/img/phone-ico.jpg" height="50px"/>
      <span class="text-logo">Phonify</span>
    </div>
    <c:if test="${param.includeCartFragment}">
      <div class="col-xs-6">
        <a href="${pageContext.request.contextPath}/cart">
          <span id="cart-item" class="cart-item pull-right bg-primary">
            My Cart:
            <span class="item-number">${order.numberOfItems}
              ${order.numberOfItems eq 1 ? 'item' : 'items'}
            </span>
            <span class="item-price">
              <app:price price="${order.subtotal}"/>
            </span>
          </span>
        </a>
      </div>
    </c:if>
  </div>
  <security:authorize access="isAuthenticated()">
    <div class="text-right padding-aside-15">
      <form:form method="POST" action="${pageContext.request.contextPath}/logout">
        <input type="submit" value="Logout" class="btn btn-primary"/>
      </form:form>
    </div>
  </security:authorize>
</div>