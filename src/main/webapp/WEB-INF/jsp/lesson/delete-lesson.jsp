<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.delete.lesson"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/lesson-menu/delete" method="post">
    <spring:message code="lesson.menu.title"/>:<p><input name="lessonTitle"></p>
    <p><input type="submit" value="<spring:message code="delete"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<br>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:red"><c:out value="${msg}"/><p>
    </c:if>

</body>
</html>
