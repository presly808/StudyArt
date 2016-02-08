
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

<li><a href="${pageContext.request.contextPath}/tasks-menu/show-group/<%=group%>"> <p><%=group%></p> </a></li>

<%
    }
%>
</body>
</html>
