<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.create.course"/></title>
</head>
<body>
<h1><spring:message code="label.title.create.new.course"/></h1>


<form:form action="${pageContext.request.contextPath}/course-menu/create-course" modelAttribute="course"
           method="post">

    <spring:message code="label.create.course.title"/>:<br>
    <label>
        <form:input path="title" size="47" />
        <form:errors path="title" />
    </label><br>

    <spring:message code="label.create.course.description"/>:<br>
    <label>
        <form:textarea path="description" rows="10" cols="50"/>
        <form:errors path="description"/>
    </label><br>

<%--TODO If no lessons in db throw error no lessons to add to course create lessons first--%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><input type="submit" value="<spring:message code="label.create.course.add.course"/>"></p>
</form:form>

</body>
</html>
