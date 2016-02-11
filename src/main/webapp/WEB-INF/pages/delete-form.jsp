<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/tasks-menu/delete" method="post">
    TaskId:<p><input name="taskId"></p>
    <p><input type="submit" value="Send"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:set var="error_msg" value="${error}"/>
<c:if test="${error_msg != null}">
<p style="color:red"><c:out value="${error_msg}"/><p>
    </c:if>

</body>
</html>
