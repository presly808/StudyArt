<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.course.menu"/></title>
</head>
<body>
<menu>
    <li><a href="${pageContext.request.contextPath}/course-menu/add-course"><spring:message code="label.course.menu.create"/></a></li>
    <li><a href="${pageContext.request.contextPath}/course-menu/show-courses"><spring:message code="label.course.menu.show"/></a></li>
    <li><a href="${pageContext.request.contextPath}/course-menu/delete-course"><spring:message code="label.course.menu.delete"/></a></li>
    <li><a href="${pageContext.request.contextPath}/course-menu/add-lesson-to-course"><spring:message code="label.course.menu.add.lesson"/></a></li>
</menu>

<br>

<c:set var="msg" value="${message}"/>
<c:if test="${msg != null}">
<p style="color:blue"><c:out value="${msg}"/><p>
    </c:if>

</body>
</html>
