<%@page contentType="application/json;"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.atg.com/taglibs/json" prefix="json"%>

<json:object>
  <json:array name="errors" var="error" items="${errors.allErrors}">
    <json:property name="message" escapeXml="false">
      <div class="alert alert-danger alert-dismissable">
        ${error.defaultMessage}
        <a href="#" class="close" aria-label="close" title="close" data-dismiss="alert">&times;</a>
      </div>
    </json:property>
  </json:array>
  <c:if test="${errors.errorCount eq 0}">
    <c:set var="numberOfItems" value="${shoppingCart.numberOfItems}"/>
    <c:choose>
      <c:when test="${numberOfItems eq 1}">
        <json:property name="itemsText">${numberOfItems} item</json:property>
      </c:when>
      <c:otherwise>
        <json:property name="itemsText">${numberOfItems} items</json:property>
      </c:otherwise>
    </c:choose>
    <json:property name="priceText"><fmt:formatNumber value="${shoppingCart.subtotal}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/></json:property>
  </c:if>
</json:object>