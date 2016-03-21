<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.show.course"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/course-menu/edit-course" method="post">

    <h1>${course.title}</h1>
    <p>${course.description}</p>
    <br>

    <c:forEach var="lesson" items="${lessons}">
        <div align="center">
            <p>${lesson.title}</p>
        </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${course.id.toString()}"/>

    <p><input type="submit" value="<spring:message code="menu.edit"/>"></p>
</form>
<br>
</body>
</html>
