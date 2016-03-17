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

    <security:authentication property="principal.username" var="name" />

    <li><a href="user-menu/show-user/${name}"><spring:message code="account"/></a></li>

    <li><a href="task-menu"><spring:message code="task.menu"/></a></li>

    <li><a href="lesson-menu"><spring:message code="lesson"/></a></li>

    <li><a href="course-menu"><spring:message code="course"/></a></li>

    <li><a href="group-menu"><spring:message code="group"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="user-menu"><spring:message code="user.menu"/></a></li>
    </security:authorize>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN')">
        <li><a href="service-menu"><spring:message code="service.menu"/></a></li>
    </security:authorize>

</menu>
</body>
</html>