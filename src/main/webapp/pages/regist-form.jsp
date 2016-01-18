<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>

<body>
<div style="float:right;">
    <form action="login" method="post">
        user name:<p><input name="userName"></p>
        email:<p><input name="email"></p>
        password:<p><input name="password"></p>
        <p><input type="submit" value="register"></p>
    </form>
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