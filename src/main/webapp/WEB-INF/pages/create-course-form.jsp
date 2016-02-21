<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.create.course"/></title>
</head>
<body>
<h1><spring:message code="label.title.create.new.course"/></h1>


<form action="${pageContext.request.contextPath}/course-menu/create-course" method="post">

    <spring:message code="label.create.course.title"/>:<br>
    <label>
        <textarea name="course_title" rows="1" cols="50"></textarea>
    </label><br>

    <spring:message code="label.create.course.description"/>:<br>
    <label>
        <textarea name="course_description" rows="10" cols="50"></textarea>
    </label><br>
<%--TODO If no lessons in db throw error no lessons to add to course create lessons first--%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><input type="submit" value="<spring:message code="label.create.course.add.course"/>"></p>
</form>

</body>
</html>
