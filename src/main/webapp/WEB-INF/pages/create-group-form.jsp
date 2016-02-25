<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.create.group"/></title>
</head>
<body>

<h1><h1><spring:message code="label.group.add.group"/></h1></h1>

<form:form action="${pageContext.request.contextPath}/group-menu/create-group" modelAttribute="userGroup"
           method="post">

    <spring:message code="label.create.group.title"/>:<br>
    <label>
        <form:input path="name" size="47" />
        <form:errors path="name" />
    </label><br>

    <spring:message code="label.create.group.description"/>:<br>
    <label>
        <form:textarea path="description" rows="10" cols="50"/>
        <form:errors path="description"/>
    </label><br>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <p><input type="submit" value="<spring:message code="label.create"/>"></p>

</form:form>
</body>
</html>
