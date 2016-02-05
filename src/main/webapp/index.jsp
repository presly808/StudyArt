<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<body>
<div style="float:right;">
    <form action="j_spring_security_check" method="post">
        email:<p><input name="j_username"></p>
        password:<p><input type="password" name="j_password"></p>
        <p><input type="submit" value="log in"></p>
    </form>
    <a href="registration-form">registration</a>
</div>
<%
    String login_msg=(String)request.getAttribute("error");
    if(login_msg!=null){
    %>
       <p style="color:red"><%=login_msg%></p>
    <%
        }
    %>
</body>
</html>