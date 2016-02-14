<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.show.lessons"/></title>
</head>
<body>
<c:forEach var="lesson" items="${lessons}">
    <div align="center">
        <li><a href="${pageContext.request.contextPath}/lesson-menu/show-lesson/${lesson.title}"> <p>${lesson.title}</p> </a></li>
    </div>
</c:forEach>
</body>
</html>
