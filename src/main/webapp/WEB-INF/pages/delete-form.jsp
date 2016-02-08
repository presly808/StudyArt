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
</form>
</body>
</html>
