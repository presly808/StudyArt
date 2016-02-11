<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"  %>
<html>
<head>
    <title><spring:message code="label.title.registration.form"/></title>
</head>

<body>
<div style="float:right;">
    <form action="registration" method="post">
        <spring:message code="label.user.name"/>:<p><input name="userName"></p>
        <spring:message code="label.user.email"/>:<p><input name="email"></p>
        <spring:message code="label.user.password"/>:<p><input type="password" name="password"></p>
        <p><input type="checkbox" name="role" value="teacher"> <spring:message code="label.teacher"/></p>
        <p><input type="submit" value="<spring:message code="label.registration"/>"></p>
    </form>
</div>
<%
    String regist_msg =(String)request.getAttribute("error");
    if(regist_msg !=null){
%>
<p style="color:red"><%=regist_msg%></p>
<%
    }
%>
</body>
</html>