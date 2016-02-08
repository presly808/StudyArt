<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create task page</title>
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


<%
    List<String> error_list= (List<String>) request.getAttribute("error");
    if(error_list!=null){
        for (String msg : error_list) {
        %>

            <p style="color:red"><%=msg%></p>
<%
        }
%>
<%
    }
%>

</body>
</html>
