<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.check.task"/></title>
</head>
<body>

<table border="1" style="width:40%">
    <tr>
        <th>in args</th>
        <th>real</th>
        <th>expected</th>
        <th>done</th>
    </tr>
    <c:forEach var="result" items="${resultList}" >
        <tr>
            <td>
                    ${result.inArgsTemplate}
            </td>
            <td>
                    ${result.actualValue}
            </td>
            <td>
                    ${result.expectedValue}
            </td>
            <td>
                    ${result.done}
            </td>
        </tr>
    </c:forEach>

</table>

<p>${status}</p>


<%--<table border="1" style="width:40%">--%>
    <%--<tr>--%>
        <%--<th>in args</th>--%>
        <%--<th>real</th>--%>
        <%--<th>expected</th>--%>
        <%--<th>done</th>--%>
    <%--</tr>--%>

<%--<%TaskTestResult result = (TaskTestResult) request.getAttribute("result");%>--%>
<%--<table border="1" style="width:40%">--%>
    <%--<tr>--%>
        <%--<th>in args</th>--%>
        <%--<th>real</th>--%>
        <%--<th>expected</th>--%>
        <%--<th>done</th>--%>
    <%--</tr>--%>
    <%--<%--%>
        <%--for (int i = 0; i < result.getActualValues().size(); i++) { %>--%>

    <%--<tr>--%>
        <%--<td>--%>
            <%--0--%>
        <%--</td>--%>
        <%--<td>--%>
            <%--<%=result.getActualValues().get(i)%>--%>
        <%--</td>--%>
        <%--<td>--%>
            <%--<%=result.getExpectedValues().get(i).toString()%>--%>
        <%--</td>--%>
        <%--<td>--%>
            <%--<%=result.getResults().get(i)%>--%>
        <%--</td>--%>
    <%--</tr>--%>

    <%--<% }%>--%>


<%--</table>--%>
<%--<p><%=result.getStatus()%></p>--%>

</body>
</html>