<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.task.list"/></title>
</head>
<body>

<c:forEach var="task" items="${taskList}">
    <div align="center">
    <li><a href="${pageContext.request.contextPath}/tasks-menu/do-task/${task.title}"> <p>${task.title}</p> </a></li>
    </div>
</c:forEach>

</body>
</html>
