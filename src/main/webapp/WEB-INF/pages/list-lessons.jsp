<%@include file="include.jsp"%>

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
