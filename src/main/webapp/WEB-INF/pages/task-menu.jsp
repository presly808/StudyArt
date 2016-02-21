<%@include file="include.jsp" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title><spring:message code="label.title.task.menu"/></title>
</head>
<body>
<menu>
    <li><a href="task-menu/add-task"><spring:message code="label.task.menu.add"/></a></li>
    <security:authorize
            access="hasRole('ROLE_ADMIN')">
        <li><a href="task-menu/find-task"/><spring:message code="label.task.menu.find"/></li>
    </security:authorize>


    <li><a href="task-menu/groups"><spring:message code="label.task.menu.tasks"/></a></li>


    <li><a href="task-menu/size"><spring:message code="label.task.menu.size"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
        <li><a href="task-menu/delete-form"><spring:message code="label.task.menu.delete"/></a></li>
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
