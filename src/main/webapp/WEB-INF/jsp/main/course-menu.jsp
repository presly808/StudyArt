<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="title.course.menu"/></title>
</head>
<body>
<menu>
    <li><a href="${pageContext.request.contextPath}/course-menu/add-course"><spring:message code="course.menu.create"/></a></li>
    <li><a href="${pageContext.request.contextPath}/course-menu/show-courses"><spring:message code="course.menu.show"/></a></li>
    <li><a href="${pageContext.request.contextPath}/course-menu/find-course"><spring:message code="course.menu.find"/></a></li>
    <li><a href="${pageContext.request.contextPath}/course-menu/delete-course"><spring:message code="course.menu.delete"/></a></li>
</menu>

<br>

<c:if test="${not empty message}"><div class="message green">${message}</div></c:if>

<%--<c:if test="${message != null}" >--%>
    <%--<p style="color:blue"><c:out value="${message}"/><p>--%>
    <%--</c:if>--%>

</body>
</html>
