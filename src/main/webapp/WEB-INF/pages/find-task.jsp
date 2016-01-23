<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Find task page</title>
</head>
<body>
<form action="doTask">
    TaskId:<p><input name="taskId"></p>
    <p><input type="submit" value="get task"></p>
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
