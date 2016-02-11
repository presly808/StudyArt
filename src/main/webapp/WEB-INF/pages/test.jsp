<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>\

<html>
<head>
</head>

<body>
<h2>Customer SignUp Form - JSR303 @Valid example</h2>

<form action="#" th:action="@{/registration}" th:object="${user}" method="get"  >
    <table>
        <tr>
            Name :<input type="text" name ="userName" th:field="*{userName}" />
            <p th:if="${fields.hasErrors('userName')}" th:errors="*{name}">Name Error</p>
        </tr>
        <%--First Name: <input type="text" name="firstName" th:field="*{firstName)">
        <p th:if="${#fields.hasErrors('firstName')}" th:errors="*{firstName}">First Name Error</p>--%>
        <tr>
            <td>Password :</td>
            <td><input type="password" th:field="*{password}" /></td>
        </tr>
        <td>Email:</td>
        <td><input type="email" th:field="*{email}"/></td>
        <tr>
            <td colspan="3"><input type="submit" /></td>
        </tr>
    </table>
</form>

</body>
</html>