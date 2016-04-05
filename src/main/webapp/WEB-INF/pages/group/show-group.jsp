<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.show.lesson"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/group-menu/edit-group" method="post">

    <h1>${group.name}</h1>
    <p>${group.description}</p>
    <br>

    <c:forEach var="user" items="${users}">
        <div align="center">
            <p>${user.name}</p>
        </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${group.id.toString()}"/>
    <p><input type="submit" value="<spring:message code="menu.edit"/>"></p>
</form>
<br>
</body>
</html>
