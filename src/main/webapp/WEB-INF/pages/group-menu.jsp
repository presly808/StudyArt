<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.group"/></title>
</head>
<body>
<menu>
    <li><a href="${pageContext.request.contextPath}/group-menu/add-group"><spring:message code="label.group.menu.create"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/show-groups"><spring:message code="label.group.menu.show"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/delete-group-form"><spring:message code="label.group.menu.delete"/></a></li>
</menu>
</body>
</html>
