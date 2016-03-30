<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>

</head>
<body>
<spring:message code="hello"/> <security:authentication property="principal.username" />!
<li><a href="${pageContext.request.contextPath}/menu"><spring:message code="main.menu"/></a></li>
<br>
<br>
</body>
</html>
