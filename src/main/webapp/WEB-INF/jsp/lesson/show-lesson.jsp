<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.show.lesson"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/lesson-menu/edit-lesson"  method="post">

    <h1>${lesson.title}</h1>
    <p>${lesson.description}</p>
    <br>

    <c:forEach var="task" items="${tasks}">
        <div align="center">
            <p>${task.title}</p>
        </div>

    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${lesson.id.toString()}"/>
    <p><input type="submit" value="<spring:message code="edit"/>"></p>

</form>
<br>
</body>
</html>
