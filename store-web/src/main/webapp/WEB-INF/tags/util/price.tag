<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@attribute name="price" type="java.lang.Double" required="true"%>

<s:eval expression="@priceFormatter.format(price)"/>