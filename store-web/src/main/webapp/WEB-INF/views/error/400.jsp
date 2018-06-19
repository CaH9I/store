<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<s:message code="error.400" var="message"/>
<app:error errorCode="400" message="${message}"/>