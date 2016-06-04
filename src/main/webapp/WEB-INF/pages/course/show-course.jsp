<%@include file="../main/include.jsp" %>

<html>
<head>
    <title><spring:message code="title.show.course"/></title>
</head>
<body>

<form action="${pageContext.request.contextPath}/course-menu/edit-course" method="post">

    <h1>${course.title}</h1>
    <h1>111</h1>
    <p>${course.description}</p>
    <br>

    <c:forEach var="lesson" items="${lessons}">
        <div align="center">
            <p>${lesson.title}</p>
        </div>
    </c:forEach>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${course.id.toString()}"/>

</form>

<input type="submit"  id="myButton" value="Edit"/>

<script type="text/javascript">

    var myBtn = document.getElementById('myButton');

    //add event listener
    myBtn.addEventListener('click', function() {
        window.location.href='www.google.com.ua';
    });

//    var button_my_button = "#myButton";
//    $(button_my_button).click(function(){
//        window.location.href='www.google.com.ua';
//    });

    var k=${edit};

    if (k) {

    }
</script>

</body>
</html>
