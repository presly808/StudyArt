<%@include file="include.jsp"%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/course-menu/delete" method="post">
    <spring:message code="label.course.menu.delete"/>:<p><input name="courseTitle"></p>
    <p><input type="submit" value="<spring:message code="label.send"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<br>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:red"><c:out value="${msg}"/><p>
    </c:if>

</body>
</html>
