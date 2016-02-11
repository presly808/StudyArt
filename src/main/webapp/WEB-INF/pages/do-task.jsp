<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <title>Do task page</title>
</head>
<body>

<c:set var="task" value="${task}"/>

<h1>${task.title}</h1>
<p>${task.groupName}</p>
<br>
<p>${task.description}</p>
<br>
<p>${task.examples}</p>
<br>
<%--TODO add compile error--%>
<form action="${pageContext.request.contextPath}/tasks-menu/check-task" method="post">
    <textarea rows="24" cols="80" name="userCode">${task.template}</textarea>
    <input type="hidden" name="id" value="${task.codingBatId}">
    <p><input type="submit" value="check"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
