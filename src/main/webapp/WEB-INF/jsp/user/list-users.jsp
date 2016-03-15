<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.task.list"/></title>
</head>
<body>

<c:forEach var="user" items="${users}">
    <div align="center">
        <li><a href="${pageContext.request.contextPath}/user-menu/show-user/${user.name}"> <p>${user.name}</p> </a></li>
    </div>
</c:forEach>

<c:if test="${message != null}">
<p style="color:red"><c:out value="${message}"/><p>
    </c:if>

</body>
</html>
