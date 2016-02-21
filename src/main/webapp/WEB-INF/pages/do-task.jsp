<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.send"/></title>
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
<%--TODO add compile error--%>
<form action="${pageContext.request.contextPath}/task-menu/check-task" method="post">
    <textarea rows="24" cols="80" name="userCode">${task.template}</textarea>
    <input type="hidden" name="title" value="${task.title}">
    <p><input type="submit" value="check"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>



</body>
</html>
