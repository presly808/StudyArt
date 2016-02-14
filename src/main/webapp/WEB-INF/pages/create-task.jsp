<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.create.task"/></title>
</head>
<body>
<h1><spring:message code="label.title.create.new.task"/></h1>

<form action="${pageContext.request.contextPath}/tasks-menu/create-task" method="post">
    <spring:message code="label.user.name"/>:<br>
    <label>
        <input type="text" name="task_name">
    </label><br>
    <spring:message code="label.create.task.group"/>:<br>
    <label>
        <input type="text" name="task_group">
    </label><br>
    <spring:message code="label.create.task.description"/>:<br>
    <label>
        <textarea name="task_description" rows="10" cols="50"></textarea>
    </label><br>
    <spring:message code="label.create.task.examples"/>:<br>
    <label>
        <textarea name="examples" rows="5" cols="50"></textarea>
    </label><br>

    <spring:message code="label.create.task.template"/>:<br>
    <code>public String methodName(int argName1, char argName2) {}</code><br>
    <label>
        <textarea name="method_template" rows="3" cols="50"></textarea>
    </label><br>

    <spring:message code="label.create.task.datapoint"/>:<br>
    <code>25-2.45,12,12</code><br>
    <label>
        <textarea name="data_points" rows="10" cols="50"></textarea>
    </label><br>

    <input type="submit" value="<spring:message code="label.send"/>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:set var="error_msg_list" value="${error}"/>
<c:if test="${error_msg_list != null}">
<c:forEach var="error_msg" items="${error_msg_list}" >
<p style="color:red"><c:out value="${error_msg}"/><p>
</c:forEach>
</c:if>

</body>
</html>
