<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.show.lesson"/></title>
</head>
<body>

<h1>${user.email}</h1>
<p>${user.name}</p>
<p>${role}</p><br>

<c:if test="${result!=null}">
<p><spring:message code="passed.tasks"/>:</p>

<c:forEach items="${result}" var="entry">
<a href="${pageContext.request.contextPath}/task-menu/show-solution/${entry.key}">${entry.key}<br>
    </c:forEach>
    </c:if>
</body>
</html>
