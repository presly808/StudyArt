<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<body>
<div style="float:right;">
    <form action="login" method="post">
        email:<p><input name="email"></p>
        password:<p><input type="password" name="password"></p>
        <p><input type="submit" value="log in"></p>
    </form>
    <form action="registration" method="get">
        <button type="submit">registration</button>
    </form>
        <menu>
            <li><a href="http://www.art-code.com.ua">art code</a></li>
        </menu>
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