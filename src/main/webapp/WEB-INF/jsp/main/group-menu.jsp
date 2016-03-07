<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="title.group"/></title>
</head>
<body>
<menu>
    <li><a href="${pageContext.request.contextPath}/group-menu/add-group"><spring:message code="group.menu.create"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/show-groups"><spring:message code="group.menu.show"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/find-group"><spring:message code="group.menu.find"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/delete-group"><spring:message code="group.menu.delete"/></a></li>
</menu>
<br>

    <c:set var="msg" value="${message}"/>
    <c:if test="${msg != null}">
    <p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>
</body>
</html>