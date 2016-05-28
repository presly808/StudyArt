<%@include file="../component/jsp-include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="title.add.lesson"/></title>

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

    <link href="<c:out value="${simplemdeCss}"/>" rel="stylesheet">

    <style type="text/css">
        .CodeMirror {
            border: 1px solid #ccc;
            border-radius: 4px;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075)
        }

        .navbar-static-top {
            z-index: 5;
        }

        /*.ui-autocomplete-loading {
            background: white url("images/ui-anim_basic_16x16.gif") right center no-repeat;
        }*/
    </style>

    <link href="<c:out value="${jqueryUiCss}"/>" rel="stylesheet">



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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/menu">ArtStudy</a>
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

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"> Create Lesson
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Create Lesson
                </div>

                <div class="panel-body">
                    <div class="col-lg-6">
                        <form:form action="${CONTEXT_PATH}/lesson-menu/add-lesson" modelAttribute="lesson"
                                   method="post" role="form">

                        <div class="form-group">
                            <label><spring:message code="create.course.lesson.title"/></label>
                            <form:input path="title" class="form-control"/>
                            <form:errors path="title"/>
                        </div>

                        <div class="form-group">
                            <label>
                                <spring:message code="create.course.lesson.description"/>
                            </label>
                            <form:textarea id="description" path="description" class="form-control" rows="10"/>
                            <form:errors path="description"/>
                        </div>

                        <div class="form-group">
                            <div class="ui-widget">
                                <label for="tasks">Attach tasks</label>
                                <input id="tasks" name="lessonTasks" class="form-control" size="50">
                            </div>
                        </div>


                        <input type="submit" class="btn btn-default" value="<spring:message code="create"/>">
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    </form:form>
                </div>
            </div>
        </div>


        <%--todo replace by ajax --%>
        <c:set var="error_msg_list" value="${message}"/>
        <c:if test="${error_msg_list != null}">
        <c:forEach var="error_msg" items="${error_msg_list}">

        <p style="color:red"><c:out value="${error_msg}"/><p>
        </c:forEach>
        </c:if>
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

<%--JQueryUi--%>
<script src="${jqueryUiJs}"></script>


<%--<script src="${codemirrorJs}"></script>--%>
<%--<script src="${clikeJs}"></script>--%>
<%--<script src="${fullscreanJS}"></script>--%>
<script src="${simplemdeJs}"></script>
<%--<script data-main="${simplemdeJs}" src="${requireJS}"></script>--%>

<script>

    var simplemde = new SimpleMDE({ element: $("#description")[0] , autosave: {
        enabled: true,
        uniqueId: "MyUniqueID",
        delay: 1000,
    }});

    $(function() {
        function split( val ) {
            return val.split( /,\s*/ );
        }
        function extractLast( term ) {
            return split( term ).pop();
        }

        $( "#tasks" )
            // don't navigate away from the field on tab when selecting an item
                .bind( "keydown", function( event ) {
                    if ( event.keyCode === $.ui.keyCode.TAB &&
                            $( this ).autocomplete( "instance" ).menu.active ) {
                        event.preventDefault();
                    }
                })
                .autocomplete({
                    source: function( request, response ) {
                        $.getJSON( "${CONTEXT_PATH}/search/json?type=task", { // todo replace url
                            key: extractLast( request.term )
                        }, function (data) {
                            // assuming data is a JavaScript array such as
                            // ["one@abc.de", "onf@abc.de","ong@abc.de"]
                            // and not a string
                            var arr = [];
                            for(var iter in data.list){
                                arr.push(data.list[iter].title)
                            }
                            response(arr);
                        } );
                    },
                    search: function() {
                        // custom minLength
                        var term = extractLast( this.value );
                        if ( term.length < 2 ) {
                            return false;
                        }
                    },
                    focus: function() {
                        // prevent value inserted on focus
                        return false;
                    },
                    select: function( event, ui ) {
                        var terms = split( this.value );
                        // remove the current input
                        terms.pop();
                        // add the selected item
                        terms.push( ui.item.value );
                        // add placeholder to get the comma-and-space at the end
                        terms.push( "" );
                        this.value = terms.join( ", " );
                        return false;
                    }
                });
    });
</script>

</body>

</html>
