<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<body>
<h1>StudyART</h1>
<div style="float:right;">

    <form name='loginForm'
          action="j_spring_security_check" method='POST'>
        email:<p><input  type='text' name="username"></p>
        password:<p><input type="password" name="password"></p>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <p><input type="submit" value="log in"></p>
    </form>
    <a href="registration-form">registration</a>

    <c:if test="${not empty error}" >
        <div class="error" style="color: red">${error}</div>
    </c:if>
</div>
</body>
</html>