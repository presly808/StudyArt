
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create task page</title>
</head>
<body>
<h1>Create New Task</h1>

<form action=${pageContext.request.contextPath}/main/create-task method="post">
    Task name:<br>
    <label>
        <input type="text" name="task_name">
    </label><br>
    Task group:<br>
    <label>
        <input type="text" name="task_group">
    </label><br>
    Description:<br>
    <label>
        <textarea name="task_description" rows="10" cols="50"></textarea>
    </label><br>
    Examples:<br>
    <label>
        <textarea name="examples" rows="5" cols="50"></textarea>
    </label><br>

    Method template:<br>
    <code>public String methodName(int argName1, char argName2) {}</code><br>
    <label>
        <textarea name="method_template" rows="3" cols="50"></textarea>
    </label><br>

    Set test data point:<br>
    <code>25-2.45,12,12</code><br>
    <label>
        <textarea name="data_points" rows="10" cols="50"></textarea>
    </label><br>

    <input type="submit">
</form>

<%
    String error_msg=(String)request.getAttribute("error");
    if(error_msg!=null){
%>
<p style="color:red"><%=error_msg%></p>
<%
    }
%>

</body>
</html>
