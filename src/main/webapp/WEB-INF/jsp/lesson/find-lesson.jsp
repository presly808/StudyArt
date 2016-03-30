<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.find.lesson"/></title>
</head>

<body>
<form action="${pageContext.request.contextPath}/lesson-menu/show-lesson" method="post">
    <spring:message code="title"/>:<p><input type="text" name="title" ></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><input type="submit" value="<spring:message code="menu.find"/>"></p>
</form>

<c:if test="${error != null}">
<p style="color:red"><c:out value="${error}"/><p>
    </c:if>

</body>
</html>
