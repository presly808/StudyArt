<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="include.jsp"%>--%>

<html>
<head>
    <title><spring:message code="title.login"/></title>
</head>

<body>
<h1>StudyART</h1>
<div style="float:right;">

<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
    |
    <a href="?lang=ua">ua</a>
</span>

<br>
    <form name='loginForm' action="j_spring_security_check" method='POST'>
        <spring:message code="user.email"/>:<p><input  type='text' name="username"></p>
        <spring:message code="user.password"/>:<p><input type="password" name="password"></p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <p><input type="submit" value="<spring:message code="login"/>"></p>

    </form>
    <%--registration-form--%>
    <a href="registration-form"><spring:message code="registration"/></a>
    <br>
    <br>
    <c:if test="${not empty message}" >
        <div class="error" style="color: red">${message}</div>
    </c:if>

</div>
</body>
</html>