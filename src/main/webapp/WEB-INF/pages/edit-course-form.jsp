<%@include file="include.jsp" %>

<html>
<head>
    <title><spring:message code="label.title.edit.course"/></title>
</head>
<body>

<h1><h1><spring:message code="label.title.edit.course"/></h1></h1>

<form action="${pageContext.request.contextPath}/course-menu/edit-course" method="post">

    <spring:message code="label.create.course.title"/>:<br>
    <label>
        <textarea name="course_title" rows="1" cols="50" ><c:out value="${course.title}"/> </textarea>
    </label><br>

    <spring:message code="label.create.course.description"/>:<br>
    <label>
        <textarea name="course_description" rows="10" cols="50"><c:out value="${course.description}"/> </textarea>
    </label><br>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <p><input type="submit" value="<spring:message code="label.edit"/>"></p>


</form>
</body>
</html>
