<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<menu>
    <li><a href="tasks-menu/add-task"><spring:message code="label.task.menu.add"/></a></li>
    <security:authorize
            access="hasRole('ROLE_ADMIN')">
        <li><a href="tasks-menu/find-task"/><spring:message code="label.task.menu.find"/></li>
    </security:authorize>


    <li><a href="tasks-menu/groups"><spring:message code="label.task.menu.tasks"/></a></li>


    <li><a href="tasks-menu/size"><spring:message code="label.task.menu.size"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="tasks-menu/delete-form"><spring:message code="label.task.menu.delete"/></a></li>
    </security:authorize>
</menu>
</body>
</html>
