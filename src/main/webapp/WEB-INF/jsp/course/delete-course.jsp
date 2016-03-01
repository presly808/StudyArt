<%@include file="../main/include.jsp"%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/course-menu/delete" method="post">
    <spring:message code="course.menu.delete"/>:<p><input name="courseTitle"></p>
    <p><input type="submit" value="<spring:message code="delete"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<br>

<c:if test="${message != null}">
<p style="color:red"><c:out value="${message}"/><p>
    </c:if>

</body>
</html>
