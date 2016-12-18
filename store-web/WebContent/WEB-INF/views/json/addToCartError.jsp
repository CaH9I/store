<%@page contentType="application/json;"%>
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
</json:object>