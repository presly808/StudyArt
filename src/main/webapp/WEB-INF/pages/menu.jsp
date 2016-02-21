<%@include file="include.jsp"%>
<%@ taglib prefix="security"  uri="http://www.springframework.org/security/tags"  %>

<html>
<head>
    <title><spring:message code="label.title.menu"/></title>
</head>

<body>
<spring:message code="label.hello"/> <security:authentication property="principal.username" />!
<menu>

    <li><a href="task-menu"><spring:message code="label.task.menu"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="lesson-menu"><spring:message code="label.lesson"/></a> </li>
    </security:authorize>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
    <li><a href="course-menu"><spring:message code="label.course"/></a></li>
    </security:authorize>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="group-menu"><spring:message code="label.group"/></a></li>
    </security:authorize>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="user-menu"><spring:message code="label.user"/></a></li>
    </security:authorize>


</menu>
</body>
</html>