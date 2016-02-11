<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Task list</title>
</head>
<body>

<c:forEach var="task" items="${taskList}">
    <div align="center">
    <li><a href="${pageContext.request.contextPath}/tasks-menu/do-task/${task.codingBatId}"> <p>${task.title}</p> </a></li>
    </div>
</c:forEach>

</body>
</html>
