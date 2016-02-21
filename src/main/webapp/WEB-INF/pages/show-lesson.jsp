<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.show.lesson"/></title>
</head>
<body>

<h1>${lesson.title}</h1>
<p>${lesson.description}</p>
<br>

<c:forEach var="task" items="${tasks}">
    <div align="center">
         <p>${task.title}</p>
    </div>
</c:forEach>

<br>
</body>
</html>