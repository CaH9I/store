<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<%@attribute name="hideLogin" type="java.lang.Boolean"%>

<jsp:useBean id="cartView" type="com.expertsoft.core.commerce.ShoppingCartView" scope="request"/>
<jsp:useBean id="languages" type="java.util.List" scope="request"/>
<jsp:useBean id="currentLanguage" type="java.lang.String" scope="request"/>

<div class="page-header">
    <div class="flex-center flex-end padding-aside-15 languages">
        <c:forEach var="lang" items="${languages}">
            <c:choose>
                <c:when test="${currentLanguage eq lang}">
                    <span class="text-muted text-uppercase lang-element">${lang}</span>
                </c:when>
                <c:otherwise>
                    <form:form method="post" cssClass="lang-element" action="${s:mvcUrl('SLC#setLanguage').arg(0, requestScope['javax.servlet.forward.request_uri']).build()}">
                        <input type="hidden" name="_method" value="PUT"/>
                        <input type="hidden" name="<s:eval expression='@localeChangeInterceptor.paramName'/>" value="${lang}"/>
                        <input type="submit" class="link text-uppercase" value="${lang}"/>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <div class="flex-center">
        <div class="col-xs-6 flex-center">
            <a href="${s:mvcUrl('PLC#productList').build()}">
                <img src="<s:url value='/img/phone-ico.jpg'/>" height="50px"/>
            </a>
            <span class="text-logo">Phonify</span>
        </div>
        <div class="col-xs-6">
            <a href="${s:mvcUrl('CC#cart').build()}">
                <span id="cart-item" class="cart-item pull-right bg-primary">
                    <s:message code="header.myCart"/>
                    <span class="badge item-number">${cartView.numberOfItems}</span>
                    <span class="badge item-price"><app:price price="${cartView.subtotal}"/></span>
                </span>
            </a>
        </div>
    </div>
    <c:if test="${not hideLogin}">
        <div class="flex-center flex-end padding-aside-15">
            <security:authorize access="isAuthenticated()">
                <security:authorize access="hasRole(T(com.expertsoft.web.config.SecurityConfig).ADMIN)">
                    <a class="admin-link" href="${s:mvcUrl('AHC#adminHome').build()}"><s:message code="header.administration"/></a>
                </security:authorize>
                <span class="padding-aside-15">
                    <security:authentication property="principal.username" var="username"/>
                    <s:message code="header.greetings" arguments="${username}"/>
                </span>
                <s:url var="logout" value="/logout"/>
                <form:form method="post" action="${logout}">
                    <input type="submit" value="<s:message code="header.logout"/>" class="btn btn-primary"/>
                </form:form>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <a href="${s:mvcUrl("LPC#login").build()}"><s:message code="header.login"/></a>
            </security:authorize>
        </div>
    </c:if>
</div>