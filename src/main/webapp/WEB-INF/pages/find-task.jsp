<%@include file="include.jsp" %>

<html>
<head>
    <title><spring:message code="label.title.find.task"/></title>
</head>

<body>
<form action="${pageContext.request.contextPath}/task-menu/do-task" method="post">
    <spring:message code="label.lesson.menu.delete.id"/>:<p><input name="lessonId"></p>
    <p><input type="submit" value="<spring:message code="label.find"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:if test="${error!= null}">
<p style="color:red"><c:out value="${error}"/><p>
    </c:if>

</body>
</html>
<%--TODO add succesfull delete--%>