<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.edit.group"/></title>
</head>
<body>

<h1><h1><spring:message code="title.edit.group"/></h1></h1>

<form:form action="${pageContext.request.contextPath}/group-menu/update-group" modelAttribute="userGroup" method="post">

        <spring:message code="title"/>:<br>
        <label>
            <form:input path="name" size="47"/>
            <form:errors path="name"/>
        </label><br>

        <spring:message code="create.group.description"/>:<br>
        <label>
            <form:textarea path="description" rows="10" cols="50"/>
            <form:errors path="description"/>
        </label><br>

    <c:forEach var="user" items="${usersInGroup}">
        <div align="center">
            <p><input type="checkbox" checked="checked" name="${user.email}">${user.email}</p>
        </div>
    </c:forEach>

    <c:forEach var="users" items="${allUsers}">
        <div align="center">
            <p><input type="checkbox" name="${users.email}">${users.email}</p>
        </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${userGroup.id}"/>

    <p><input type="submit" value="<spring:message code="menu.edit"/>"></p>


</form:form>
</body>
</html>
