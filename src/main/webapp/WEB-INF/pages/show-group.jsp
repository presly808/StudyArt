<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.show.lesson"/></title>
</head>
<body>

<h1>${group.name}</h1>
<p>${group.description}</p>
<br>

<c:forEach var="user" items="${users}">
    <div align="center">
        <p>${user.name}</p>
    </div>
</c:forEach>

<br>
</body>
</html>
