<%@include file="include.jsp" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title><spring:message code="title.menu"/></title>
    <form method="post" action="${pageContext.request.contextPath}/logout" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <p><input type="submit" value="logout"></p>
    </form>
</head>

<body>

<menu>

    <li><a href="task-menu"><spring:message code="task.menu"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="lesson-menu"><spring:message code="lesson"/></a></li>
    </security:authorize>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="course-menu"><spring:message code="course"/></a></li>
    </security:authorize>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="group-menu"><spring:message code="group"/></a></li>
    </security:authorize>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="user-menu"><spring:message code="user.menu"/></a></li>
    </security:authorize>

</menu>
</body>
</html>