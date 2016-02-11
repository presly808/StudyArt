<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ua.artcode.model.codingbat.CodingBatTask" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.task.list"/></title>
</head>
<body>
    <% List <CodingBatTask> taskList= (List<CodingBatTask>) request.getAttribute("taskList");%>
    <%
        for (CodingBatTask codingBatTask : taskList) {
    %>
    <div align="center">
    <li><a href="${pageContext.request.contextPath}/tasks-menu/do-task/<%=codingBatTask.getCodingBatId()%>"> <p><%=codingBatTask.getTitle()%></p> </a></li>
    </div>
    <%
        }
    %>
</body>
</html>
