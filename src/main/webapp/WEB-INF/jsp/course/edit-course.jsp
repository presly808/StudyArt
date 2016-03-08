<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.edit.course"/></title>
</head>
<body>

<h1><h1><spring:message code="title.edit.course"/></h1></h1>

<form:form action="${pageContext.request.contextPath}/course-menu/update-course" modelAttribute="course" method="post">
    <spring:message code="create.course.title"/>:<br>
    <label>
        <form:input path="title" size="47"/>
        <form:errors path="title"/>
    </label><br>

    <spring:message code="create.course.description"/>:<br>
    <label>
        <form:textarea path="description" rows="10" cols="50"/>
        <form:errors path="description"/>
    </label><br>

    <c:forEach var="lesson" items="${lessonInCourse}">
        <div align="center">
            <p><input type="checkbox" checked="checked" name="${lesson.title}">${lesson.title}</p>
        </div>
    </c:forEach>

    <c:forEach var="lesson" items="${allLessons}">
        <div align="center">
            <p><input type="checkbox" name="${lesson.title}">${lesson.title}</p>
        </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${course.id.toString()}"/>

    <p><input type="submit" value="<spring:message code="update"/>"></p>
</form:form>

<form action="${pageContext.request.contextPath}/course-menu/edit-course" method="post">

</form>
</body>
</html>
