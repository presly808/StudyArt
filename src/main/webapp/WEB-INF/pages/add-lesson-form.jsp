<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.add.lesson"/></title>
</head>
<body>

<h1><h1><spring:message code="label.title.create.new.lesson"/></h1></h1>

<form action="${pageContext.request.contextPath}/lesson-menu/create-lesson" method="post">

    <spring:message code="label.create.course.lesson.title"/>:<br>
    <label>
        <textarea name="lesson_title"  rows="1" cols="50"></textarea>
    </label><br>

    <spring:message code="label.create.course.lesson.description"/>:<br>
    <label>
        <textarea name="lesson_description" rows="10" cols="50"></textarea>
    </label><br>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <p><input type="submit" value="<spring:message code="label.create.course.add.lesson"/>"></p>

</form>
</body>
</html>
