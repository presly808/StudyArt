<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.show.course"/></title>
</head>
<body>

<h1>${course.title}</h1>
<p>${course.description}</p>
<br>

<c:forEach var="lesson" items="${lessons}">
    <div align="center">
        <p>${lesson.title}</p>
    </div>
</c:forEach>

<br>
</body>
</html>
