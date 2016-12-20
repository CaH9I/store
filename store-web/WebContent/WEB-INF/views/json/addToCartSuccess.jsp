<%@page contentType="application/json;"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.atg.com/taglibs/json" prefix="json"%>

<json:object>
  <json:property name="itemsText">${shoppingCart.numberOfItems}
    ${shoppingCart.numberOfItems eq 1 ? 'item' : 'items'}
  </json:property>
  <json:property name="priceText">
    <fmt:formatNumber value="${shoppingCart.order.subtotal}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/>
  </json:property>
</json:object>