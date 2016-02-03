<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"

         uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Menu page</title>
</head>

<body>
<menu>
    <li><a href="add-task">Add task</a></li>
    <security:authorize
            access=hasRole('ROLE_SPITTER')">
        <a href="find-task">Administration</a>
    </security:authorize>
    <li><a href="find-task">Find task</a></li>
    <li><a href="groups">Show all</a></li>
    <li><a href="size">Size</a></li>
    <li><a href="delete-form">Delete</a></li>
</menu>
</body>
</html>