<%@include file="include.jsp" %>

<html>
<head>
    <title><spring:message code="title.user.menu"/></title>
</head>
<body>
<menu>
    <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="user-menu/show-users"><spring:message code="user.menu.show"/></a></li>
        <li><a href="user-menu/delete-form"><spring:message code="menu.delete"/></a></li>
    </security:authorize>
</menu>
<br>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>

</body>
</html>
