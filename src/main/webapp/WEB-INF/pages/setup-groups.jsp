<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Setup</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/group-menu/add-users" method="post"
<%--TODO check empty lessons--%>
<c:forEach var="user" items="${users}">
    <div align="center">
        <p><input type="checkbox" name="${user.name}">${user.name}</p>
    </div>
</c:forEach>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" name="name" value="${name}"/>

<p><input type="submit" value="<spring:message code="label.create.course.add.lesson"/>"></p>
<form/>
</body>
</html>
