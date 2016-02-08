<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/tasks-menu/delete" method="post">
    <spring:message code="label.task.menu.delete.id"/>:<p><input name="taskId"></p>
    <p><input type="submit" value="Send"></p>
</form>
</body>
</html>
