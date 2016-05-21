<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="check.task"/></title>
</head>
<body>

<label><spring:message code="check.task"/></label>
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

</body>
</html>
