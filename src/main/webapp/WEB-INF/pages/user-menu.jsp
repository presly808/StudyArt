<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.group"/></title>
</head>
<body>
<menu>
    <li><a href="user-menu/add-user"><spring:message code="label.group.menu.create"/></a></li>
    <li><a href="user-menu/show-users"><spring:message code="label.group.menu.show"/></a></li>
    <li><a href="user-menu/delete-user-form"><spring:message code="label.group.menu.delete"/></a></li>
</menu>
</body>
</html>
