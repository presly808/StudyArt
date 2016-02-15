<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="label.title.registration.form"/></title>
</head>

<body>
<div style="float:right;">
    <form action="registration" method="post">
        <spring:message code="label.user.name"/>:<p><input name=${user.userName}></p>
        <spring:message code="label.user.email"/>:<p><input name=${user.email}></p>
        <spring:message code="label.user.password"/>:<p><input type="password" name=${user.password}></p>
        <p><input type="checkbox" name="role" value="teacher"> <spring:message code="label.teacher"/></p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <p><input type="submit" value="<spring:message code="label.registration"/>"></p>
    </form>
</div>

<c:set var="error_msg_list" value="${error}"/>
<c:if test="${error_msg_list != null}">
<c:forEach var="error_msg" items="${error_msg_list}" >
<p style="color:red"><c:out value="${error_msg}"/><p>
    </c:forEach>
    </c:if>

</body>
</html>