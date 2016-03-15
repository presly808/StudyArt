<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.show.lesson"/></title>
</head>
<body>

<h1>${user.email}</h1>
<p>${user.name}</p>
<p>${user.userType}</p>

<c:forEach items="${result}" var="entry">
    ${entry.key}=${entry.value}<br>
</c:forEach>

</body>
</html>
