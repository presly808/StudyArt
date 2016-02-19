<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:errors path="user.*"/>

<form action="registration" method="get">
    <table>
        <tr>
            <td>Name:<input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Password: <input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Email: <input type="text" name="email"/></td>
        </tr>
    </table>
    <input type="submit" value="register">
</form>
</body>
</html>
