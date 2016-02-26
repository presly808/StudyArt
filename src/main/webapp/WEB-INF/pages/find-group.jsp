<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.find.group"/></title>
</head>

<body>
<form action="${pageContext.request.contextPath}/group-menu/show-group" method="post">
    <spring:message code="label.create.group.title"/>:<p><input type="text" name="name" ></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><input type="submit" value="<spring:message code="label.find"/>"></p>
</form>

<c:if test="${error != null}">
<p style="color:red"><c:out value="${error}"/><p>
    </c:if>

</body>
</html>
