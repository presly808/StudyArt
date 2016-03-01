<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="title.403"/></title>
</head>
<body>
<h1><spring:message code="title.403"/></h1>

<c:choose>
    <c:when test="${empty username}">
        <h2><spring:message code="error.403"/></h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="user.name"/>: ${username} <br/>
            <spring:message code="error.403"/></h2>
    </c:otherwise>
</c:choose>

</body>
</html>