<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="title.lesson"/></title>
</head>
<body>
<menu>

    <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
    <li><a href="${pageContext.request.contextPath}/lesson-menu/create-lesson"><spring:message code="menu.add"/></a></li>
    </security:authorize>

    <li><a href="${pageContext.request.contextPath}/lesson-menu/show-lessons"><spring:message code="lesson.menu.show"/></a></li>

    <li><a href="${pageContext.request.contextPath}/lesson-menu/find-lesson"><spring:message code="menu.find"/></a></li>

    <security:authorize
        access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
    <li><a href="${pageContext.request.contextPath}/lesson-menu/delete-lesson"><spring:message code="menu.delete"/></a></li>
    </security:authorize>

</menu>

<br>


<c:if test="${message != null}">
<p style="color:blue"><c:out value="${message}"/><p>
    </c:if>

</body>
</html>
