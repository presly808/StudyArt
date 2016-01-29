
<%@ page import="ua.artcode.model.codingbat.CodingBatTask" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task list</title>
</head>
<body>
    <% List <CodingBatTask> taskList= (List<CodingBatTask>) request.getAttribute("taskList");%>
    <%
        for (CodingBatTask codingBatTask : taskList) {
    %>
                <p><%=codingBatTask.getTitle()%></p>
    <%
        }
    %>
</body>
</html>
