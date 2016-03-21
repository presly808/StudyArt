<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="title.lesson"/></title>
</head>
<body>
<menu>
    <li><a href="${pageContext.request.contextPath}/lesson-menu/add-lesson"><spring:message code="menu.add"/></a></li>
    <li><a href="${pageContext.request.contextPath}/lesson-menu/show-lessons"><spring:message code="lesson.menu.show"/></a></li>
    <li><a href="${pageContext.request.contextPath}/lesson-menu/find-lesson"><spring:message code="menu.find"/></a></li>
    <li><a href="${pageContext.request.contextPath}/lesson-menu/delete-lesson"><spring:message code="menu.delete"/></a></li>
</menu>

<br>


<c:if test="${message != null}">
<p style="color:blue"><c:out value="${message}"/><p>
    </c:if>

</body>
</html>
