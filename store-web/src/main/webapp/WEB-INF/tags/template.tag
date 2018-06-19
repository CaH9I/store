<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<%@attribute name="hideLogin" type="java.lang.Boolean"%>

<!DOCTYPE html>
<html>
    <head>
        <title><s:message code="header.title"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="<s:url value='/css/base.css'/>"/>
    </head>
    <body>
        <div class="container">
            <app:header hideLogin="${hideLogin}"/>
            <jsp:doBody/>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="<s:url value='/js/common.js'/>"></script>
    </body>
</html>