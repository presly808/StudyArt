<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.show.lesson"/></title>
</head>
<body>

<h1>${lesson.title}</h1>
<p>${lesson.description}</p>
<br>

<c:forEach var="task" items="${tasks}">
    <div align="center">
         <p>${task.title}</p>
    </div>
</c:forEach>

<br>
</body>
</html>
