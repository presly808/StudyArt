<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html>
<head>
    <title>Menu page</title>
</head>

<body>
Hello <security:authentication property="principal.username" />!
<menu>

    <li><a href="tasks-menu">TasksMenu</a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
    <li><a href="course-menu">Course</a></li>
    </security:authorize>

</menu>
</body>
</html>