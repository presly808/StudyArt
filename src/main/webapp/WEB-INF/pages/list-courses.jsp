<%@include file="include.jsp"%>

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
