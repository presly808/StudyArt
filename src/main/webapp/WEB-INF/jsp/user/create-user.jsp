<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.do.task"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/group-menu/add-users" method="post">
    <spring:message code="task.menu.delete.id"/>:<p><input name="taskId"></p>
    <p><input type="submit" value="<spring:message code="send"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:red"><c:out value="${msg}"/><p>
    </c:if>

</body>
</html>
