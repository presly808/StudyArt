<%@ page import="ua.artcode.model.codingbat.TaskTestResult" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check task</title>
</head>
<body>
<%TaskTestResult result = (TaskTestResult) request.getAttribute("result");%>
<table border="1" style="width:100%">
    <tr>
        <th>in args</th>
        <th>real</th>
        <th>expected</th>
        <th>done</th>
    </tr>
    <%
        for (int i = 0; i < result.getActualValues().size(); i++) { %>

    <tr>
        <td>
            0
        </td>
        <td>
            <%=result.getActualValues().get(i)%>
        </td>
        <td>
            <%=result.getExpectedValues().get(i).toString()%>
        </td>
        <td>
            <%=result.getResults().get(i)%>
        </td>
    </tr>

    <% }%>


</table>
<p><%=result.getStatus()%></p>

</body>
</html>