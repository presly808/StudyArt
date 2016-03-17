<%@include file="include.jsp" %>

<html>
<head>
    <title><spring:message code="service.menu"/></title>
</head>
<body>

<menu>
    <li><a href="user-menu/add-admin"><spring:message code="user.menu.create"/></a></li>
    <li><a href="service/createDump"><spring:message code="service.createDump"/></a></li>
    <li><a href="service/restoreDB"><spring:message code="service.restoreDB"/></a></li>
</menu>
<br>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>

</body>
</html>
