<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.find.lesson"/></title>
</head>

<body>
<form action="${pageContext.request.contextPath}/course-menu/show-course" method="post">
    <spring:message code="label.lesson.menu.title"/>:<p><input type="text" name="title" ></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><input type="submit" value="<spring:message code="label.find"/>"></p>
</form>

<c:if test="${error != null}">
<p style="color:red"><c:out value="${error}"/><p>
    </c:if>

</body>
</html>
