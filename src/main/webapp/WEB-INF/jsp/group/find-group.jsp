<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.find.group"/></title>
</head>

<body>
<form action="${pageContext.request.contextPath}/group-menu/show-group" method="post">
    <spring:message code="create.group.title"/>:<p><input type="text" name="name" ></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><input type="submit" value="<spring:message code="find"/>"></p>
</form>

<c:if test="${message != null}">
<p style="color:red"><c:out value="${message}"/><p>
    </c:if>

</body>
</html>
