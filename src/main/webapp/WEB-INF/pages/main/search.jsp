<%@include file="../component/jsp-include.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Search</title>

    <%@include file="/WEB-INF/pages/component/css-include.jsp" %>

    <!-- Bootstrap Core CSS -->
    <link href="<c:out value="${bootstrapMinCss}"/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:out value="${metisMenuCss}"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:out value="${sbAdminCss}"/>" rel="stylesheet">

    <link href="<c:out value="${appCss}"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:out value="${fontAwesomeCss}"/>" rel="stylesheet">

    <!-- DataTables CSS -->

    <link href="${dataTablesBootstrapCss}" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="${dataTablesResponsiveCss}" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <%@include file="/WEB-INF/pages/component/navbar.jsp"%>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Search</h1>

                    <div class="row">
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

            <div class="panel panel-default">
                <div class="panel-heading">
                    <form action="${CONTEXT_PATH}/search" method="get" class="form-inline">
                        <div class="form-group">
                            <label class="sr-only" for="searchWord">Email address</label>
                            <input name="key" type="text" class="form-control" id="searchWord" value="${searchWord}">
                            <input type="hidden" name="type" value="${searchType}">
                        </div>
                        <button type="submit" class="btn btn-default">Search</button>
                    </form>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">

                    <ul class="nav nav-tabs">
                        <li role="presentation" class="${searchType == 'user' ? 'active' : ''}">
                            <a href="search?key=${searchWord}&type=user">Users <span
                                    class="badge">${foundUserSize} </span></a></li>

                        <li role="presentation" class="${searchType == 'task' ? 'active' : ''}">
                            <a href="search?key=${searchWord}&type=task">Tasks <span
                                    class="badge">${foundTaskSize} </span></a></li>

                        <li role="presentation" class="${searchType == 'course' ? 'active' : ''}">
                            <a href="search?key=${searchWord}&type=course">Courses <span
                                    class="badge">${foundCourseSize} </span></a></li>

                        <li role="presentation" class="${searchType == 'lesson' ? 'active' : ''}">
                            <a href="search?key=${searchWord}&type=lesson">Lessons <span
                                    class="badge">${foundLessonSize} </span></a></li>

                    </ul>


                    <div class="content">

                        <div class="row">
                            <table style="margin-left: 1em" width="100%" class="table-responsive table-striped">
                                <c:if test="${searchType=='user'}">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Type</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="iterUser" items="${foundUsers}">
                                        <tr class="odd gradeX">
                                            <td>
                                                <a href="${CONTEXT_PATH}/user-menu/show-user/${iterUser.name}">${iterUser.name}</a>
                                            </td>
                                            <td>${iterUser.email}</td>
                                            <td>${iterUser.userType}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </c:if>
                                <c:if test="${searchType=='course'}">
                                    <thead>
                                    <tr>
                                        <th>Title</th>
                                        <th>Owner</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="iterCourse" items="${foundCourses}">
                                        <tr class="odd gradeX">
                                            <td><a href="${CONTEXT_PATH}/course-menu/show-course/${iterCourse.title}">${iterCourse.title}</a></td>
                                            <td>owner - NOT FINISHED</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </c:if>
                                <c:if test="${searchType=='lesson'}">
                                    <thead>
                                    <tr>
                                        <th>Title</th>
                                        <th>Owner</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="iterLesson" items="${foundLessons}">
                                        <tr class="odd gradeX">
                                            <td>${iterLesson.title}</td>
                                            <td>owner - NOT FINISHED</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </c:if>
                                <c:if test="${searchType == 'task'}">
                                    <thead>
                                    <tr>
                                        <th>TaskName</th>
                                        <th>GroupName</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="task" items="${foundTasks}">
                                        <tr class="odd gradeX">
                                            <td>
                                                <a href="${CONTEXT_PATH}/task-menu/do-task/${task.title}">${task.title}</a>
                                            </td>
                                            <td>
                                                <a href="${CONTEXT_PATH}/task-menu/show-group/${task.groupName}">${task.groupName}</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </c:if>
                            </table>
                        </div>
                        <div class="row">
                            <nav>
                                <ul style="margin-left: 1em" class="pagination">
                                    <li><a href="search?key=${searchWord}&type=${searchType}&offset=0&length=50" aria-label="Previous"><span aria-hidden="true">Begin</span></a></li>
                                    <c:forEach var="item" items="${pagingLinks}">
                                        <li class="${item.highlighted == true ? 'active' : ''} ${item.disabled == true ? 'disabled' : ''}">
                                            <a href="search?key=${searchWord}&type=${searchType}&offset=${item.offset}&length=50">${item.offset}-${item.length}</a>
                                        </li>
                                    </c:forEach>
                                    <li><a href="#" aria-label="Next"><span aria-hidden="true">End</span></a></li>
                                </ul>
                            </nav>
                        </div>

                    </div>

                </div>
                <!-- /.panel-body -->
            </div>
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

<!-- DataTables JavaScript -->
<script src="${jqueryDataTablesMinJs}"></script>
<script src="=${dataTablesBootstrapMinJs}"></script>
<script src="${dataTablesResponsiveJs}"></script>

</body>

</html>
