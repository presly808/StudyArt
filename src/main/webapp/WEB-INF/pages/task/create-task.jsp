<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.create.task"/></title>
</head>
<body>

<c:if test="${mainTitle != null}">
    <h1><c:out value="${mainTitle}"/>
    </h1>
</c:if>


<form:form action="${pageContext.request.contextPath}/task-menu/add-task" modelAttribute="task"
           method="post">
    <spring:message code="create.task.name"/>:<br>
    <label>

        <form:input path="title" size="30"/>
        <form:errors path="title"/>
    </label><br>
    <spring:message code="create.task.group"/>:<br>
    <label>
        <form:input path="groupName" size="30"/>
        <form:errors path="groupName"/>
    </label><br>
    <spring:message code="create.task.description"/>:<br>
    <label>
        <form:textarea path="description" rows="10" cols="50"/>
        <form:errors path="description"/>
    </label><br>
    <spring:message code="create.task.examples"/>:<br>
    <label>
        <form:textarea path="examples" rows="5" cols="50"/>
        <form:errors path="examples"/>
    </label><br>

    <spring:message code="create.task.template"/>:<br>
    <code>public int methodName(int a, int b) {}</code><br>
    <label>
        <form:textarea path="template" rows="3" cols="50"/>
        <form:errors path="template"/>
    </label><br>

    <spring:message code="create.task.datapoint"/>:<br>
    <code>25-10,15</code><br>
    <label>
            <textarea name="data_points" rows="10" cols="50"><c:if
                    test="${testData != null}">${testData}</c:if></textarea>
    </label><br>

    Solution: <br>
    <code>public int methodName(int a, int b) { return a + b; }</code><br>
    <label>
        <form:textarea path="solution" rows="3" cols="50"/>
        <form:errors path="solution"/>
    </label><br>

    <input type="submit" value="<spring:message code="create"/>">
    <c:if test="${task.id != null}">
        <input type="hidden" name="id" value="${task.id}"/>
    </c:if>
    <input type="hidden" name="mainTitle" value="${mainTitle}"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>

<c:set var="error_msg_list" value="${message}"/>
<c:if test="${error_msg_list != null}">
<c:forEach var="error_msg" items="${error_msg_list}">

<p style="color:red"><c:out value="${error_msg}"/><p>
    </c:forEach>
    </c:if>

</body>
</html>
