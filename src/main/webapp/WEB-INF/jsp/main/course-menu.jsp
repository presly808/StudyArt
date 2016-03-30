<%@include file="include.jsp" %>

<html>
<head>
    <title><spring:message code="title.course.menu"/></title>
</head>
<body>
<menu>
    <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="${pageContext.request.contextPath}/course-menu/create-course"><spring:message code="menu.add"/></a></li>
    </security:authorize>

    <li><a href="${pageContext.request.contextPath}/course-menu/show-courses"><spring:message code="course.menu.show"/></a></li>

    <li><a href="${pageContext.request.contextPath}/course-menu/find-course"><spring:message code="menu.find"/></a></li>

    <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="${pageContext.request.contextPath}/course-menu/delete-course"><spring:message code="menu.delete"/></a></li>
    </security:authorize>
</menu>

<br>

<c:if test="${message != null}">
<p style="color:blue"><c:out value="${message}"/><p>
    </c:if>

</body>
</html>
