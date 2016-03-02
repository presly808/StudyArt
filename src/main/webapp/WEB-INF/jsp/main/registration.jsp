

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><spring:message code="title.registration.form"/></title>
</head>

<body>

<form:form action="${pageContext.request.contextPath}/registration" modelAttribute="user">
    <table>
        <tr>
            <td><spring:message code="user.name"/>:</td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
        </tr>

        <tr>
            <td><spring:message code="user.email"/>:</td>
            <td><form:input path="email"/></td>
            <td><form:errors path="email"/></td>
        </tr>

        <tr>
            <td><spring:message code="user.password"/>:</td>
            <td><form:input path="password"/></td>
            <td><form:errors path="password" /></td>
        </tr>

        <tr>
            <td>User type:</td>
            <td><form:select path="userType">
                <form:option value="" label="Select user type"/>
                <form:option value="ROLE_USER" label="Student"/>
                <form:option value="ROLE_TEACHER" label="Teacher"/>
            </form:select></td>
            <td><form:errors path="userType" /></td>
        </tr>

        <tr>
            <td><input type="submit" value="<spring:message code="registration"/>"/></td>
        </tr>

    </table>
</form:form>

<c:if test="${message != null}">
<p style="color:red"><c:out value="${message}"/><p>
    </c:if>

</body>
</html>