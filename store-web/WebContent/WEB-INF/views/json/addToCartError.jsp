<%@page contentType="application/json;"%>
<%@taglib uri="http://www.atg.com/taglibs/json" prefix="json"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<json:object>
  <json:array name="errors" var="error" items="${errors.allErrors}">
    <spring:message message="${error}"/>
  </json:array>
</json:object>