<%@include file="include.jsp"%>

<html>
<head>
    <title>Setup</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/lesson-menu/add-task" method="post"

    <c:forEach var="task" items="${tasks}">
    <div align="center">
        <p><input type="checkbox" name="${task.title}">${task.title}</p>
    </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="title" value="${title}"/>

    <p><input type="submit" value="<spring:message code="label.course.add.tasks"/>"></p>
</form>
</body>
</html>
