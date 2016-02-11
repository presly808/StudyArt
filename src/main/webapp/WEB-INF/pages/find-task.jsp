<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>Find task page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/tasks-menu/do-task" method="post">
    TaskId:<p><input name="taskId" ></p>
    <p><input type="submit" value="get task"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:set var="error_msg" value="${error}"/>
<c:if test="${error_msg != null}">
<p style="color:red"><c:out value="${error_msg}"/><p>
    </c:if>

</body>
</html>
