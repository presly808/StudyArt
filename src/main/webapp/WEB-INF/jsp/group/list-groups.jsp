<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.show.groups"/></title>
</head>
<body>
<c:forEach var="group" items="${groups}">
    <div align="center">
        <li><a href="${pageContext.request.contextPath}/group-menu/show-group/${group.name}"> <p>${group.name}</p> </a></li>
    </div>
</c:forEach>
</body>
</html>
