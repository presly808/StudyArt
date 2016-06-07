<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.show.course"/></title>
    <script type="text/javascript">
        function redirect() {
            document.forms["form"].submit();
        }
    </script>
</head>
<body>

<form action="${pageContext.request.contextPath}/course-menu/edit-course" method="post" id="form">

    <h1>${course.title}</h1>
    <p>${course.description}</p>
    <br>

    <c:forEach var="lesson" items="${lessons}">
        <div align="center">
            <p>${lesson.title}</p>
        </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${course.id.toString()}"/>

    <input type="submit" id="myButton" onclick="redirect()" value="Edit" style="visibility: hidden;"/>

</form>


<script type="text/javascript">

    if (${edit}) {
        document.getElementById('myButton').style.visibility = 'visible';
    }
</script>


</body>
</html>
