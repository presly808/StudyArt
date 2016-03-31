<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.group.list"/></title>
</head>
<body>
<c:forEach var="group" items="${groupList}">
    <li><a href="${pageContext.request.contextPath}/task-menu/show-group/${group}"> <p>${group}</p> </a></li>
</c:forEach>
</body>
</html>
