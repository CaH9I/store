<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://store.expertsoft.com/tags" prefix="app"%>

<app:template hideLogin="${true}">
    <h3 class="text-center"><s:message code="login.title"/></h3>
    <form:form method="post">
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-user"></i>
                </span>
                <input name="username" class="form-control" placeholder="<s:message code='login.username'/>" autocomplete="off"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-lock"></i>
                </span>
                <input name="password" type="password"
                       class="form-control" placeholder="<s:message code='login.password'/>" autocomplete="new-password"/>
            </div>
        </div>

        <div class="checkbox">
            <label>
                <input type="checkbox" name="remember-me"/>&nbsp;<s:message code="login.rememberMe"/>
            </label>
        </div>

        <c:if test="${param.error ne null}">
            <p class="text-danger"><s:message code="login.error"/></p>
        </c:if>

        <div class="form-group">
            <input class="col-xs-12 btn btn-primary" type="submit" value="<s:message code='general.button.login'/>"/>
        </div>
    </form:form>
</app:template>