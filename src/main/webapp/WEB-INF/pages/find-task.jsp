<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html>
<head>
</head><spring:message code="label.title.find.task"/>
<br><br/>
<body>
<form action="${pageContext.request.contextPath}/tasks-menu/do-task" method="post">
    <spring:message code="label.task.menu.delete.id"/>:<p><input name="taskId" ></p>
    <p><input type="submit" value="<spring:message code="label.get.task"/>"></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
