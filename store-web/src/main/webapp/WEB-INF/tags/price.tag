<%@attribute name="price" type="java.lang.Double" required="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<s:eval expression="@priceFormatter.format(price)"/>