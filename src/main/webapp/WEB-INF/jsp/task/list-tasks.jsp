<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.task.list"/></title>
</head>
<body>

<c:forEach var="task" items="${taskList}">
    <div align="center">
    <li><a href="${pageContext.request.contextPath}/task-menu/do-task/${task.title}"> <p>${task.title}</p> </a></li>
    </div>
</c:forEach>

</body>
</html>
