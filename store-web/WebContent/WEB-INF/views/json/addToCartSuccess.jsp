<%@page contentType="application/json;"%>
<%@taglib uri="http://www.atg.com/taglibs/json" prefix="json"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="app"%>

<json:object>
  <json:property name="itemsText">
    ${order.numberOfItems}
    ${order.numberOfItems eq 1 ? 'item' : 'items'}
  </json:property>
  <json:property name="priceText">
    <app:price price="${order.subtotal}"/>
  </json:property>
</json:object>