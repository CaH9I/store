<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="app"%>

<app:template>
    <jsp:include page="include/header.jsp">
        <jsp:param name="hideLogin" value="true"/>
    </jsp:include>
    <h3 class="text-center">Login</h3>
    <form:form method="post">
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-user"></i>
                </span>
                <input name="username" class="form-control" placeholder="Email" autocomplete="off"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-lock"></i>
                </span>
                <input name="password" type="password"
                       class="form-control" placeholder="Password" autocomplete="new-password"/>
            </div>
        </div>

        <c:if test="${param.error != null}">
            <p class="text-danger">Invalid credentials</p>
        </c:if>

        <div class="form-group">
            <input class="col-xs-12 btn btn-primary" type="submit" value="Login"/>
        </div>
    </form:form>
</app:template>