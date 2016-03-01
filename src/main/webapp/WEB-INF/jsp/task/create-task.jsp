<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../main/include.jsp"%>

<html>
<head>
    <title><spring:message code="title.create.task"/></title>
</head>
<body>
<%--<c:if var="${edit.equals("")}"></c:if>{--%>
<h1><spring:message code="title.create.new.task"/></h1>


<form:form action="${pageContext.request.contextPath}/task-menu/create-task" modelAttribute="task"
           method="post">
    <spring:message code="create.task.name"/>:<br>
    <label>

        <form:input path="title" size="30"/>
        <form:errors path="title" />
        <%--<input type="text" name="task_name">--%>
    </label><br>
    <spring:message code="create.task.group"/>:<br>
    <label>
        <form:input path="groupName" size="30"/>
        <form:errors path="groupName" />
        <%--<input type="text" name="task_group">--%>
    </label><br>
    <spring:message code="create.task.description"/>:<br>
    <label>
        <form:textarea path="description" rows="10" cols="50" />
        <form:errors path="description" />
        <%--<textarea name="task_description" rows="10" cols="50"></textarea>--%>
    </label><br>
    <spring:message code="create.task.examples"/>:<br>
    <label>
        <form:textarea path="examples" rows="5" cols="50" />
        <form:errors path="examples" />
        <%--<textarea name="examples" rows="5" cols="50"></textarea>--%>
    </label><br>

    <spring:message code="create.task.template"/>:<br>
    <code>public String methodName(int argName1, char argName2) {}</code><br>
    <label>
        <form:textarea path="template" rows="3" cols="50" />
        <form:errors path="template" />
        <%--<textarea name="method_template" rows="3" cols="50"></textarea>--%>
    </label><br>

    <spring:message code="create.task.datapoint"/>:<br>
    <code>25-2.45,12,12</code><br>
    <label>
        <textarea name="data_points" rows="10" cols="50"></textarea>
    </label><br>

    <input type="submit" value="<spring:message code="send"/>">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>

<c:set var="error_msg_list" value="${message}"/>
<c:if test="${error_msg_list != null}">
<c:forEach var="error_msg" items="${error_msg_list}" >
<p style="color:red"><c:out value="${error_msg}"/><p>
</c:forEach>
</c:if>

</body>
</html>
