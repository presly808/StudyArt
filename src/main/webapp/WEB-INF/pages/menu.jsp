<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html>
<head>
    <title>Menu page</title>
</head>

<body>
Hello <security:authentication property="principal.username" />!
<menu>
    <li><a href="add-task">Add task</a></li>
    <security:authorize
            access="hasRole('ROLE_ADMIN')">
        <li><a href="find-task">Find task</a></li>
    </security:authorize>


    <li><a href="groups">Tasks</a></li>


    <li><a href="size">Size</a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="delete-form">Delete</a></li>
    </security:authorize>
</menu>
</body>
</html>