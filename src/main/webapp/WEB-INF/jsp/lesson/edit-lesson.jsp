<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="textarea" uri="http://www.springframework.org/tags/form" %>
<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.edit.lesson"/></title>
</head>
<body>

<h1><h1><spring:message code="title.edit.lesson"/></h1></h1>

<form:form action="${pageContext.request.contextPath}/lesson-menu/update-lesson" modelAttribute="lesson" method="post">

    <spring:message code="create.course.lesson.title"/>:<br>
    <label>
        <form:input path="title" size="47"/>
        <form:errors path="title"/>
    </label><br>

    <spring:message code="create.course.lesson.description"/>:<br>
    <label>
        <form:textarea path="description" rows="10" cols="50"/>
        <form:errors path="description"/>
    </label><br>

    <c:forEach var="task" items="${tasksInLesson}">
        <div align="center">
            <p><input type="checkbox" checked="checked" name="${task.title}">${task.title}</p>
        </div>
    </c:forEach>

    <c:forEach var="task" items="${allTasks}">
        <div align="center">
            <p><input type="checkbox" name="${task.title}">${task.title}</p>
        </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${lesson.id}"/>

    <p><input type="submit" value="<spring:message code="update"/>"></p>

</form:form>

</body>
</html>
