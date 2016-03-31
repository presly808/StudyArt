<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.find.task"/></title>
</head>

<body>
<form action="${pageContext.request.contextPath}/task-menu/do-task" method="post">
    <spring:message code="title"/>:<p><input name="title"></p>
    <p><input type="submit" value="<spring:message code="menu.find"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:if test="${error!= null}">
<p style="color:red"><c:out value="${error}"/><p>
    </c:if>

</body>
</html>
