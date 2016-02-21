<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.create.group"/></title>
</head>
<body>

<h1><h1><spring:message code="label.group.add.group"/></h1></h1>

<form action="${pageContext.request.contextPath}/group-menu/create-group" method="post">

    <spring:message code="label.create.group.title"/>:<br>
    <label>
        <textarea name="group_name"  rows="1" cols="50"></textarea>
    </label><br>

    <spring:message code="label.create.group.description"/>:<br>
    <label>
        <textarea name="group_description" rows="10" cols="50"></textarea>
    </label><br>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <p><input type="submit" value="<spring:message code="label.group.menu.add.users"/>"></p>

</form>
</body>
</html>
