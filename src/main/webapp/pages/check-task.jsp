<%@ page import="ua.artcode.model.codingbat.TaskTestResult" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check task</title>
</head>
<body>
<%TaskTestResult result = (TaskTestResult) request.getAttribute("result");%>

<p><%=result.getStatus()%></p>
</body>
</html>
