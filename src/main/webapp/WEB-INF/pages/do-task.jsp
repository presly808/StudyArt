<%@ page import="ua.artcode.model.codingbat.CodingBatTask" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Do task page</title>
</head>
<body>
<% CodingBatTask task = (CodingBatTask)request.getAttribute("task");%>

<h1><%=task.getTitle()%></h1>
<p><%=task.getGroupName()%></p>
<br>
<p><%=task.getDescription()%></p>
<br>
<p><%=task.getExamples()%></p>
<br>
<form action="${pageContext.request.contextPath}/tasks-menu/check-task" method="post">
    <textarea rows="24" cols="80" name="userCode"><%=task.getTemplate()%></textarea>
    <input type="hidden" name="id" value="<%=task.getCodingBatId()%>">
    <p><input type="submit" value="check"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>



</body>
</html>
