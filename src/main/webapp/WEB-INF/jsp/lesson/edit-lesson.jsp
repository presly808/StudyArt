<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.edit.lesson"/></title>
</head>
<body>

<h1><h1><spring:message code="title.edit.lesson"/></h1></h1>

<form action="${pageContext.request.contextPath}/lesson-menu/create-lesson" method="post">

    <spring:message code="create.course.lesson.title"/>:<br>
    <label>
        <textarea name="lesson_title" rows="1" cols="50" ><c:out value="${lesson.title}"/> </textarea>
    </label><br>

    <spring:message code="create.course.lesson.description"/>:<br>
    <label>
        <textarea name="lesson_description" rows="10" cols="50"><c:out value="${lesson.description}"/> </textarea>
    </label><br>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <p><input type="submit" value="<spring:message code="create"/>"></p>


</form>
</body>
</html>
