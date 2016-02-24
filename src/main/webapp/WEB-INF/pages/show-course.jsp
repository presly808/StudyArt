<%@include file="include.jsp"%>

<html>
<head>
    <title><spring:message code="label.title.show.course"/></title>
</head>
<body>

<h1>${course.title}</h1>
<p>${course.description}</p>
<br>
<form> </form>
<c:forEach var="lesson" items="${lessons}">
    <div align="center">
        <p>${lesson.title}</p>
    </div>
</c:forEach>

<br>
</body>
</html>
