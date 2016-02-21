<%@include file="include.jsp"%>

<html>
<head>
    <title>Setup</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/group-menu/add-users" method="post"
<%--TODO check empty lessons--%>
<c:forEach var="user" items="${users}">
    <div align="center">
        <p><input type="checkbox" name="${user.name}">${user.name}</p>
    </div>
</c:forEach>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" name="name" value="${name}"/>

<p><input type="submit" value="<spring:message code="label.create.course.add.user"/>"></p>
<form/>
</body>
</html>
