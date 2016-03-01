<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.edit.group"/></title>
</head>
<body>

<h1><h1><spring:message code="title.edit.group"/></h1></h1>

<form action="${pageContext.request.contextPath}/group-menu/edit-group" method="post">

    <spring:message code="create.group.title"/>:<br>
    <label>
        <textarea name="lesson_title" rows="1" cols="50" ><c:out value="${group.name}"/> </textarea>
    </label><br>

    <spring:message code="create.group.description"/>:<br>
    <label>
        <textarea name="lesson_description" rows="10" cols="50"><c:out value="${group.description}"/> </textarea>
    </label><br>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <p><input type="submit" value="<spring:message code="edit"/>"></p>


</form>
</body>
</html>
