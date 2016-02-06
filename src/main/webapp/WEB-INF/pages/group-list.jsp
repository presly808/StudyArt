
<%@ page import="ua.artcode.model.codingbat.CodingBatTask" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group list</title>
</head>
<body>
<% List <String> groups= (List<String>) request.getAttribute("groups");%>
<%
    for (String group : groups) {
%>

<%--<p><%=group.toString()%></p>--%>
<li><a href="tasks-menu/show-group/<%=group%>"> <p><%=group%></p> </a></li>
<%--<div align="center">--%>
    <%--<li><a href="do-task/<%=group.getCodingBatId()%>"> <p><%=codingBatTask.getTitle()%></p> </a></li>--%>
<%--</div>--%>
<%
    }
%>
</body>
</html>
