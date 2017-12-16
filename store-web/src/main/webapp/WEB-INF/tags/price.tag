<%@attribute name="price" required="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:formatNumber value="${price}" pattern="$#,###.##" maxFractionDigits="2" minFractionDigits="2"/>