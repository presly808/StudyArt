<%@include file="include.jsp" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title><spring:message code="title.task.menu"/></title>
</head>
<body>

<menu>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
    <li><a href="${pageContext.request.contextPath}/task-menu/create-task"><spring:message code="menu.add"/></a></li>
    </security:authorize>

    <li><a href="${pageContext.request.contextPath}/task-menu/find-task"/><spring:message code="menu.find"/></li>

    <li><a href="${pageContext.request.contextPath}/task-menu/groups"><spring:message code="task.menu.show"/></a></li>

    <li><a href="${pageContext.request.contextPath}/task-menu/size"><spring:message code="menu.size"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="${pageContext.request.contextPath}/task-menu/delete-form"><spring:message code="menu.delete"/></a></li>
    </security:authorize>
</menu>

<br>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>

 <br/>
</body>
</html>
