<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.delete.group"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/group-menu/delete-group" method="post">
    <spring:message code="label.group.menu.delete.name"/>:<p><input name="groupName"></p>
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
