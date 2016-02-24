<%@include file="include.jsp" %>

<html>
<head>
    <title><spring:message code="label.title.show.lesson"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/lesson-menu/edit-lesson" method="post" modelAttribute="${lesson}">

    <h1>${lesson.title}</h1>
    <p>${lesson.description}</p>
    <br>
    <c:forEach var="task" items="${tasks}">
        <div align="center">
            <p>${task.title}</p>
        </div>
    </c:forEach>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="title" value="${lesson.title}"/>
    <p><input type="submit" value="<spring:message code="label.edit"/>"></p>
</form>
<br>
</body>
</html>
