<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.find.lesson"/></title>
</head>

<body>
<form action="${pageContext.request.contextPath}/task-menu/do-task" method="post">
    <spring:message code="label.lesson.menu.delete.id"/>:<p><input name="taskId" ></p>
    <p><input type="submit" value="<spring:message code="label.get.task"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:set var="error_msg" value="${error}"/>
<c:if test="${error_msg != null}">
<p style="color:red"><c:out value="${error_msg}"/><p>
    </c:if>

</body>
</html>
<%--TODO add succesfull delete--%>