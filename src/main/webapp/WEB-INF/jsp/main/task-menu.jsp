<%@include file="include.jsp" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title><spring:message code="title.task.menu"/></title>
</head>
<body>

<menu>
    <li><a href="${pageContext.request.contextPath}/task-menu/add-task"><spring:message code="task.menu.add"/></a></li>

    <security:authorize
            access="hasRole('ROLE_ADMIN')">
        <li><a href="${pageContext.request.contextPath}/task-menu/find-task"/><spring:message code="task.menu.find"/></li>
    </security:authorize>


    <li><a href="${pageContext.request.contextPath}/task-menu/groups"><spring:message code="task.menu.show.tasks"/></a></li>


    <li><a href="${pageContext.request.contextPath}/task-menu/size"><spring:message code="task.menu.size"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="${pageContext.request.contextPath}/task-menu/delete-form"><spring:message code="task.menu.delete"/></a></li>
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
