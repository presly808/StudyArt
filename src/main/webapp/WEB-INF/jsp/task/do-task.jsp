<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="check.task"/></title>
</head>
<body>

<c:set var="task" value="${task}"/>

<h1>${task.title}</h1>
<p>${task.groupName}</p>
<br>
<p>${task.description}</p>
<br>
<p>${task.examples}</p>
<br>

<c:if test="${message != null}">
<p style="color:red"><c:out value="${message}"/><p>
    </c:if>

<form action="${pageContext.request.contextPath}/task-menu/check-task" method="post">
    <textarea rows="24" cols="80" name="userCode">${template}</textarea>

    <input type="hidden" name="id" value="${task.id.toString()}">
    <p><input type="submit" value="<spring:message code="check.task"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form action="${pageContext.request.contextPath}/task-menu/edit-task" method="post">
    <input type="hidden" name="id" value="${task.id}">
    <input type="submit" value="<spring:message code="menu.edit"/>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>



</body>
</html>
