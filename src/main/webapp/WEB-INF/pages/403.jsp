<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h1>HTTP Status 403 - Access is denied</h1>

<c:choose>
    <c:when test="${empty username}">
        <h2><spring:message code="label.403"/></h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="label.user.name"/>: ${username} <br/>
            <spring:message code="label.403"/></h2>
    </c:otherwise>
</c:choose>

</body>
</html>