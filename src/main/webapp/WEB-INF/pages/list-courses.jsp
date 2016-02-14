<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.show.lessons"/></title>
</head>
<body>
<c:forEach var="course" items="${courses}">
    <div align="center">
        <li><a href="${pageContext.request.contextPath}/course-menu/show-course/${course.title}"> <p>${course.title}</p> </a></li>
    </div>
</c:forEach>
</body>
</html>
