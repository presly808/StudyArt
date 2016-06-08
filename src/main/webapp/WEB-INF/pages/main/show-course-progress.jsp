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
                    <h1 class="page-header"><a href="${CONTEXT_PATH}/course-menu/show-course/${course.title}">${course.title}</a></h1>
                    <label>Progress of <a
                            href="${CONTEXT_PATH}/user-menu/show-user/${currentUser.name}">${currentUser.name}</a></label>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">

                    <h3>Lessons</h3>

                    <c:forEach var="lesson" items="${course.lessonList}">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <c:if test="${lesson.performedTasksSize == lesson.amountTasksSize}">
                                            <i style="color: green" class="glyphicon glyphicon-ok"></i></c:if>
                                        <a data-toggle="collapse" href="#${lesson.id}">${lesson.title}</a>
                                            <span style="float: right">
                                            ${lesson.performedTasksSize} / ${lesson.amountTasksSize}
                                            </span>
                                    </h4>
                                </div>
                                <div id="${lesson.id}" class="panel-collapse collapse">
                                    <ul class="list-group">
                                        <c:forEach var="task" items="${lesson.tasks}">
                                            <li class="list-group-item">
                                                <a href="${CONTEXT_PATH}/task-menu/do-task/${task.title}?lessonId=${lesson.id}&userId=${currentUser.name}"
                                                   >${task.title}<span style="float: right;">
                                                <c:if test="${task.performed == true}"><i style="color: green"
                                                                                  class="glyphicon glyphicon-ok"></i></c:if>
                                                </span>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </c:forEach>


                    <%--<ul class="nav nav-second-level collapse in" aria-expanded="true">

                        <c:forEach var="lesson" items="${course.lessonList}">
                            <li class="active">
                                <a href="${CONTEXT_PATH}/lesson-menu/show-lesson/${lesson.title}?courseId=${course.id}"
                                    class="list-group-item">${lesson.title}
                                    <span style="float: right">
                                    ${lesson.performedTasksSize} / ${lesson.amountTasksSize}
                                    </span>
                                </a>
                                <a href="#">Open <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level collapse in" aria-expanded="true">
                                    <c:forEach var="task" items="${lesson.tasks}">
                                        <li>
                                            <a href="${CONTEXT_PATH}/task-menu/do-task/${task.title}?lessonId=${lesson.id}"
                                               class="list-group-item">${task.title}<span style="float: right;">
                                                <c:if test="${task.performed}"><i style="color: green"
                                                                      class="glyphicon glyphicon-ok"></i></c:if>
                                                </span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>

                       &lt;%&ndash; <li>
                            <a href="#">Second Level Item</a>
                        </li>
                        <li class="active">
                            <a href="#">Third Level <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level collapse in" aria-expanded="true">
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                            </ul>
                            <!-- /.nav-third-level -->
                        </li>&ndash;%&gt;
                    </ul>--%>


                    <%--<c:forEach var="lesson" items="${course.lessonList}">
                        <div class="row">
                            <div class="col-xs-6">${lesson.title}</div>
                            <div class="col-xs-6">
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="0"
                                         aria-valuemin="0"
                                         aria-valuemax="100" style="min-width:2em; width: 2%;">
                                        0%
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>--%>


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

    $(".md").each(function () {
        var markdownText = $(this).html();
        $(this).html(markdown.toHTML(markdownText));
    });

</script>

</body>

</html>
