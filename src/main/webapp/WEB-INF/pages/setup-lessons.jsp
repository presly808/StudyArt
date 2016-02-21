<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Setup</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/course-menu/add-lesson" method="post"
<%--TODO check empty lessons--%>
<c:forEach var="lesson" items="${lessons}">
    <div align="center">
        <p><input type="checkbox" name="${lesson.title}">${lesson.title}</p>
    </div>
</c:forEach>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" name="title" value="${title}"/>

<p><input type="submit" value="<spring:message code="label.create.course.add.lesson"/>"></p>
<form/>
</body>
</html>
