<%@include file="include.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="label.title.registration.form"/></title>
</head>

<body>
<%--<div id="container">--%>

<%--<c:if test="${not empty message}"><div class="message green">${message}</div></c:if>--%>

<form:form action="${pageContext.request.contextPath}/registration" modelAttribute="user">
    <table>
        <tr>
            <td><spring:message code="label.user.name"/>:</td>
                <%--<label for="nameInput"></label>--%>
                <%--<form:input path="name" id="nameInput"/>--%>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
        </tr>

        <tr>
            <td><spring:message code="label.user.email"/>:</td>
            <td><form:input path="email"/></td>
            <td><form:errors path="email"/></td>
        </tr>

        <tr>
            <td><spring:message code="label.user.password"/>:</td>
            <td><form:input path="password"/></td>
            <td><form:errors path="password" /></td>
        </tr>

        <tr>
            <td>User type:</td>
            <td><form:select path="userType">
                <form:option value="ROLE_USER" label="Select user type"/>
                <form:option value="ROLE_USER" label="Student"/>
                <form:option value="ROLE_TEACHER" label="Teacher"/>
            </form:select></td>
            <td><form:errors path="userType" /></td>
        </tr>

        <tr>
            <td><input type="submit" value="<spring:message code="label.registration"/>"/></td>
        </tr>

        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

    </table>
</form:form>
<%--</div>--%>

<%--<springForm:form method="post" commandName="user" action="${pageContext.request.contextPath}/registration">--%>
<%--<springForm:input path="name"/>--%>
<%--<springForm:errors path="name" />--%>

<%--</springForm:form>--%>

</body>
</html>