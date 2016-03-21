<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="title.group"/></title>
</head>
<body>
<menu>
    <li><a href="${pageContext.request.contextPath}/group-menu/add-group"><spring:message code="menu.add"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/show-groups"><spring:message code="group.menu.show"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/find-group"><spring:message code="menu.find"/></a></li>
    <li><a href="${pageContext.request.contextPath}/group-menu/delete-group-form"><spring:message code="menu.delete"/></a></li>
</menu>
<br>

    <c:set var="msg" value="${message}"/>
    <c:if test="${msg != null}">
    <p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>
</body>
</html>
