<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.add.admin"/></title>
</head>

<body>
<div style="float:right;">
    <%--<form action="${pageContext.request.contextPath}/service-menu/registration-admin" modelAttribute="user"  method="post">--%>
    <%--<spring:message code="admin.name"/>:<p><input name="name"></p>--%>
    <%--<spring:message code="user.email"/>:<p><input name="email"></p>--%>
    <%--<spring:message code="user.password"/>:<p><input type="password" name="password"></p>--%>
    <%--<input type="hidden" name="userType" value="ROLE_ADMIN" input/>--%>
    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <%--<p><input type="submit" value="<spring:message code="registration"/>"></p>--%>
    <%--</form>--%>
    <%--</div>--%>

    <%--<c:if test="${message != null}">--%>
    <%--<p style="color:red"><c:out value="${message}"/><p>--%>
    <%--</c:if>--%>
    <form:form action="${pageContext.request.contextPath}/service-menu/registration-admin" modelAttribute="user"
               method="post">
    <table>
        <tr>
            <td><spring:message code="admin.name"/>:</td>
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
            <td><form:errors path="password"/></td>
        </tr>
        <td>User type:</td>
        <td><form:select path="userType">
            <form:option value="ROLE_ADMIN" label="Admin"/>
        </form:select></td>
        <td><form:errors path="userType"/></td>

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