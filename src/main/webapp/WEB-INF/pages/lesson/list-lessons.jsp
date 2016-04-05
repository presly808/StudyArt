<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.show.lessons"/></title>
</head>
<body>
<c:forEach var="lesson" items="${lessons}">
    <div align="center">
        <li><a href="${pageContext.request.contextPath}/lesson-menu/show-lesson/${lesson.title}"> <p>${lesson.title}</p> </a></li>
    </div>
</c:forEach>

<c:set var="msg" value="${message}"/>
    <c:if test="${msg != null}">
        <p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>
</body>
</html>
