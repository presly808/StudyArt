<%@ taglib prefix="security"  uri="http://www.springframework.org/security/tags"  %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html>
<head>
    <title><spring:message code="label.title.menu"/></title>
</head>

<body>
<spring:message code="label.hello"/> <security:authentication property="principal.username" />!
<menu>

    <li><a href="my-menu"><spring:message code="label.task.menu"/></a></li>

    <security:authorize
            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
    <li><a href="course-menu"><spring:message code="label.course"/></a></li>
    </security:authorize>

</menu>
</body>
</html>