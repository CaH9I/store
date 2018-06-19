<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@attribute name="mappingName" type="java.lang.String" required="true"%>

<c:if test="${page.totalPages gt 1}">
    <ul class="pager">
        <c:forEach begin="${1}" end="${page.totalPages}" var="index">
            <c:if test="${index eq 1}">
                <li ${page.first ? 'class="disabled"' : ''}>
                    <a href="${page.first ? '#' : s:mvcUrl(mappingName).arg(0, page.number).build()}">
                        <span>&laquo;</span>
                    </a>
                </li>
            </c:if>

            <c:set var="current" value="${page.number + 1 eq index}"/>
            <li ${current ? 'class="active"' : ''}>
                <c:choose>
                    <c:when test="${current}">
                        <span>${index}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${s:mvcUrl(mappingName).arg(0, index).build()}">${index}</a>
                    </c:otherwise>
                </c:choose>
            </li>

            <c:if test="${index eq page.totalPages}">
                <c:set var="nextPageIndex">${page.number + 2}</c:set>
                <li ${page.last ? 'class="disabled"' : ''}>
                    <a href="${page.last ? '#' : s:mvcUrl(mappingName).arg(0, nextPageIndex).build()}">
                        <span>&raquo;</span>
                    </a>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>