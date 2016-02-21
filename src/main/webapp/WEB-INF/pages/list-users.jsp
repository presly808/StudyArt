<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.task.list"/></title>
</head>
<body>

<c:forEach var="user" items="${users}">
    <div align="center">
        <li><a href="${pageContext.request.contextPath}/user-menu/show-user/${user.email}"> <p>${user.email}</p> </a></li>
    </div>
</c:forEach>

</body>
</html>
