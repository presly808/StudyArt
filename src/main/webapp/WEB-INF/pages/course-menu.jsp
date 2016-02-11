<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.course.menu"/></title>
</head>
<body>
<menu>
    <li><a href="course-menu/create"><spring:message code="label.course.menu.create"/></a></li>
    <li><a href="course-menu/show"><spring:message code="label.course.menu.show"/></a></li>
    <li><a href="course-menu/delete"><spring:message code="label.course.menu.delete"/></a></li>
</menu>
</body>
</html>
