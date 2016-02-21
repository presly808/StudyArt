<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.lesson"/></title>
</head>
<body>
<menu>
    <li><a href="${pageContext.request.contextPath}/lesson-menu/add-lesson"><spring:message code="label.lesson.menu.create"/></a></li>
    <li><a href="${pageContext.request.contextPath}/lesson-menu/show-lessons"><spring:message code="label.lesson.menu.show"/></a></li>
    <li><a href="${pageContext.request.contextPath}/lesson-menu/delete-lesson-form"><spring:message code="label.lesson.menu.delete"/></a></li>
</menu>

<br>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>

</body>
</html>
