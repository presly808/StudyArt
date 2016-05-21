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
    <spring:url value="/resources/codemirror-5.15.0/lib/codemirror.css" var="codemirrorCss"/>
    <link href="<c:out value="${codemirrorCss}"/>" rel="stylesheet">

    <spring:url value="/resources/codemirror-5.15.0/addon/display/fullscreen.css" var="fullscreanCSS"/>
    <link href="<c:out value="${fullscreanCSS}"/>" rel="stylesheet">

    <style type="text/css">
        .CodeMirror {
            border: 1px solid #ccc;
            border-radius: 4px;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075)
        }
    </style>

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
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${CONTEXT_PATH}/menu">ArtStudy</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages">
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>Read All Messages</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-messages -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-tasks">
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 1</strong>
                                    <span class="pull-right text-muted">40% Complete</span>
                                </p>

                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                        <span class="sr-only">40% Complete (success)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 2</strong>
                                    <span class="pull-right text-muted">20% Complete</span>
                                </p>

                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                        <span class="sr-only">20% Complete</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 3</strong>
                                    <span class="pull-right text-muted">60% Complete</span>
                                </p>

                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60% Complete (warning)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 4</strong>
                                    <span class="pull-right text-muted">80% Complete</span>
                                </p>

                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                        <span class="sr-only">80% Complete (danger)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Tasks</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-tasks -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-comment fa-fw"></i> New Comment
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                <span class="pull-right text-muted small">12 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-envelope fa-fw"></i> Message Sent
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-tasks fa-fw"></i> New Task
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-alerts -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <%--todo replace via get request--%>
                    <li>

                        <form method="post" action="${pageContext.request.contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <p><input type="submit" style="margin-left: 38px" class="btn" value="logout"></p>
                        </form>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div id="demo" class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <form action="${CONTEXT_PATH}/search" method="get">
                            <div class="input-group custom-search-form">

                                <input type="text" name="key" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                                <input type="hidden" name="type" value="user">
                            </div>
                        </form>
                        <!-- /input-group -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw"></i>Course<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li>
                                    <a href="${pageContext.request.contextPath}/course-menu/create-course"><spring:message
                                            code="menu.add"/></a></li>
                            </security:authorize>
                            <li><a href="${pageContext.request.contextPath}/course-menu/show-courses"><spring:message
                                    code="course.menu.show"/></a></li>

                            <li><a href="${pageContext.request.contextPath}/course-menu/find-course"><spring:message
                                    code="menu.find"/></a></li>

                            <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li>
                                    <a href="${pageContext.request.contextPath}/course-menu/delete-course"><spring:message
                                            code="menu.delete"/></a></li>
                            </security:authorize>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw"></i>Task Group<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li><a href="${pageContext.request.contextPath}/group-menu/create-group"><spring:message
                                        code="menu.add"/></a></li>
                            </security:authorize>

                            <li><a href="${pageContext.request.contextPath}/group-menu/show-groups"><spring:message
                                    code="group.menu.show"/></a></li>

                            <li><a href="${pageContext.request.contextPath}/group-menu/find-group"><spring:message
                                    code="menu.find"/></a></li>

                            <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li><a href="${pageContext.request.contextPath}/group-menu/delete-form"><spring:message
                                        code="menu.delete"/></a></li>
                            </security:authorize>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw"></i>Lessons<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li>
                                    <a href="${pageContext.request.contextPath}/lesson-menu/create-lesson"><spring:message
                                            code="menu.add"/></a></li>
                            </security:authorize>

                            <li><a href="${pageContext.request.contextPath}/lesson-menu/show-lessons"><spring:message
                                    code="lesson.menu.show"/></a></li>

                            <li><a href="${pageContext.request.contextPath}/lesson-menu/find-lesson"><spring:message
                                    code="menu.find"/></a></li>

                            <security:authorize
                                    access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li>
                                    <a href="${pageContext.request.contextPath}/lesson-menu/delete-lesson"><spring:message
                                            code="menu.delete"/></a></li>
                            </security:authorize>
                        </ul>
                    </li>
                    <%--todo remove teacher from user menu--%>
                    <security:authorize
                            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                        <li><a href="#"><i class="fa fa-fw"></i>User menu<span
                                class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="user-menu/show-users"><spring:message code="user.menu.show"/></a></li>
                                <li><a href="user-menu/delete-form"><spring:message code="menu.delete"/></a></li>
                            </ul>
                        </li>
                    </security:authorize>
                    <security:authorize
                            access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                        <li>
                            <a href="#"><i class="fa fa-fw"></i>Admin Menu<span
                                    class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="service-menu/add-admin"><spring:message code="user.menu.create"/></a></li>
                                <li><a href="service-menu/createDump"><spring:message code="service.createDump"/></a>
                                </li>
                                <li><a href="service-menu/restoreDB"><spring:message code="service.restoreDB"/></a></li>
                            </ul>
                        </li>
                    </security:authorize>
                    <li>
                        <a href="#"><i class="fa fa-fw"></i>Task Menu<samp class="fa arrow"></samp></a>
                        <ul class="nav nav-second-level">
                            <security:authorize
                                    access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li>
                                    <a href="${pageContext.request.contextPath}/task-menu/create-task"><spring:message
                                            code="menu.add"/></a></li>
                            </security:authorize>

                            <li><a href="${pageContext.request.contextPath}/task-menu/find-task"><spring:message
                                    code="menu.find"/></a></li>

                            <li><a href="${pageContext.request.contextPath}/task-menu/groups"><spring:message
                                    code="task.menu.show"/></a></li>

                            <li><a href="${pageContext.request.contextPath}/task-menu/size"><spring:message
                                    code="menu.size"/></a></li>

                            <security:authorize
                                    access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                                <li>
                                    <a href="${pageContext.request.contextPath}/task-menu/delete-form"><spring:message
                                            code="menu.delete"/></a></li>
                            </security:authorize>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>


    <c:set var="task" value="${task}"/>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Perform Task</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">

                </div>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">

                            <label>Task ${task.title}</label>

                            <p><a href="${CONTEXT_PATH}/task-menu/show-group/${task.groupName}">${task.groupName}</a>
                            </p>

                            <p>${task.description}</p>

                            <p>${task.examples}</p>

                            <c:if test="${message != null}">
                            <p class="help-block"><c:out value="${message}"/><p>
                            </c:if>


                            <form action="${CONTEXT_PATH}/task-menu/check-task" role="form" method="post">
                                <div class="form-group">
                                    <label for="codeTextArea">${task.title}</label>
                                    <textarea id="codeTextArea" class="form-control"
                                              name="userCode">${template}</textarea>
                                </div>
                                <input type="hidden" name="id" value="${task.id.toString()}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <div class="row">
                                    <div class="col-xs-6">
                                        <input id="checkTaskButton" type="submit" class="btn btn-default"
                                               value="<spring:message code="check.task"/>">
                                        <form action="${CONTEXT_PATH}/task-menu/edit-task" role="form" method="post">
                                            <input type="hidden" name="id" value="${task.id}">
                                            <input type="submit" class="btn btn-default"
                                                   value="<spring:message code="menu.edit"/>">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </form>
                                    </div>

                                </div>
                            </form>

                            <%-- todo why do we need this shit--%>

                        </div>

                        <div class="col-lg-6">
                            <div id="testResults">
                                <label><spring:message code="check.task"/></label>
                                <table border="1" style="width:40%">
                                    <tr>
                                        <th>in args</th>
                                        <th>real</th>
                                        <th>expected</th>
                                        <th>done</th>
                                    </tr>
                                    <c:forEach var="result" items="${resultList}" >
                                        <tr>
                                            <td>
                                                    ${result.inArgsTemplate}
                                            </td>
                                            <td>
                                                    ${result.actualValue}
                                            </td>
                                            <td>
                                                    ${result.expectedValue}
                                            </td>
                                            <td>
                                                    ${result.done}
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </table>

                                <p>${status}</p>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<%@include file="/WEB-INF/pages/component/js-include.jsp" %>
<script src="${jqueryJs}"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${bootstrapJs}"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${metisMenuJs}"></script>

<!-- Custom Theme JavaScript -->
<script src="${sbAdminJs}"></script>

<spring:url value="/resources/codemirror-5.15.0/lib/codemirror.js" var="codemirrorJs"/>
<spring:url value="/resources/codemirror-5.15.0/mode/clike/clike.js" var="clikeJs"/>
<spring:url value="/resources/codemirror-5.15.0/addon/display/fullscreen.js" var="fullscreanJS"/>

<script src="${codemirrorJs}"></script>
<script src="${clikeJs}"></script>
<script src="${fullscreanJS}"></script>

<script>
    var editor1 = CodeMirror.fromTextArea(document.getElementById("codeTextArea"), {
        lineNumbers: true,
        mode: "text/x-java"
    });

    /*$("#checkTaskButton").on("click",function(){
        $.ajax({
            type: "POST",
            url: "",
            data: data,
            success: success,
            dataType: dataType
        });
    });*/

</script>

</body>

</html>
