<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Mobile shop</title>
        <link rel="stylesheet" href="<s:url value='/css/bootstrap/bootstrap-3.3.7.min.css'/>"/>
        <link rel="stylesheet" href="<s:url value='/css/base.css'/>"/>
    </head>
    <body>
        <div class="container">
            <jsp:doBody/>
        </div>
        <script src="<s:url value='/js/jquery/jquery-3.1.1.min.js'/>"></script>
        <script src="<s:url value='/js/bootstrap/bootstrap-3.3.7.min.js'/>"></script>
        <script src="<s:url value='/js/common.js'/>"></script>
    </body>
</html>