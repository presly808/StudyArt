<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.add.admin"/></title>
</head>

<body>
<div style="float:right;">
    <form action="registration" method="post">
        <spring:message code="admin.name"/>:<p><input name="name"></p>
        <spring:message code="user.email"/>:<p><input name="email"></p>
        <spring:message code="user.password"/>:<p><input type="password" name="password"></p>
        <input type="hidden" name="role" value="ROLE_ADMIN" input/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <p><input type="submit" value="<spring:message code="registration"/>"></p>
    </form>
</div>

<c:set var="error_msg_list" value="${message}"/>
<c:if test="${error_msg_list != null}">
<c:forEach var="error_msg" items="${error_msg_list}" >
<p style="color:red"><c:out value="${error_msg}"/><p>
    </c:forEach>
    </c:if>

</body>
</html>