<%@include file="../component/jsp-include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Main Menu</title>

    <%@include file="/WEB-INF/pages/component/css-include.jsp" %>

    <!-- Bootstrap Core CSS -->
    <link href="<c:out value="${bootstrapMinCss}"/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:out value="${metisMenuCss}"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:out value="${sbAdminCss}"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:out value="${fontAwesomeCss}"/>" rel="stylesheet">

    <%--CodeMirror online editor--%>
    <link href="<c:out value="${codemirrorCss}"/>" rel="stylesheet">

    <link href="<c:out value="${fullscreanCSS}"/>" rel="stylesheet">
    <link href="<c:out value="${showHintCss}"/>" rel="stylesheet">

    <link href="<c:out value="${appCss}"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">
    <!-- Navigation -->
    <%@include file="/WEB-INF/pages/component/navbar.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><a href="${CONTEXT_PATH}/course-menu/show-course/${course.title}">${course.title}</a>
                        <c:choose>
                            <c:when test="${subscribed == true}">
                                <i style="color: #00b3ee" class="glyphicon glyphicon-flag"></i>
                            </c:when>
                            <c:otherwise>
                                <a href="${CONTEXT_PATH}/course-menu/subscribe/${course.title}" class="btn btn-primary">Follow</a>
                            </c:otherwise>
                        </c:choose>
                    </h1>
                    <label>Progress of <a
                            href="${CONTEXT_PATH}/user-menu/show-user/${currentUser.name}">${currentUser.name}</a></label>
                </div>

            </div>


            <div class="row">
                <div class="col-lg-6">

                    <div class="md">${course.description}</div>
                    <br><br>

                    <h3>Lessons</h3>
                    <ul class="list-group">
                        <c:forEach var="lesson" items="${course.lessonList}">
                            <a href="${CONTEXT_PATH}/lesson-menu/show-lesson/${lesson.title}?courseId=${course.id}"
                               class="list-group-item">${lesson.title}
                                    <span style="float: right">
                                    ${lesson.performedTasksSize} / ${lesson.amountTasksSize}
                                    </span>
                            </a>
                        </c:forEach>
                    </ul>

                    <input type="hidden" name="id" value="${course.id.toString()}"/>

                </div>

            </div>

        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->


<!-- /#wrapper -->

<%@include file="/WEB-INF/pages/component/js-include.jsp" %>
<script src="${jqueryJs}"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${bootstrapJs}"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${metisMenuJs}"></script>

<!-- Custom Theme JavaScript -->
<script src="${sbAdminJs}"></script>


<script src="${codemirrorJs}"></script>
<script src="${clikeJs}"></script>
<script src="${fullscreanJS}"></script>
<script src="${showHintJS}"></script>
<script src="${anyWordHintJs}"></script>
<script src="${markdownMinJs}"></script>


<script>

    $(document).on("load", function(){
        $(".md").each(function () {
            var markdownText = $(this).html();
            $(this).html(markdown.toHTML(markdownText));
        });
    });

</script>

</body>

</html>
