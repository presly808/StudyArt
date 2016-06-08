<!-- Navigation -->
<nav id="mainNavbar" class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
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

        <!-- /.dropdown -->
        <li class="dropdown">
            <security:authorize access="isAuthenticated()">
                <a class="dropdown-toggle"
                   href="${CONTEXT_PATH}/user-menu/show-user/<security:authentication property="principal.username"/>">
                    <security:authentication property="principal.username"/>
                </a>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <a class="dropdown-toggle"
                   href="${CONTEXT_PATH}/login">Sign In!
                </a>
            </security:authorize>
        </li>

        <security:authorize access="isAuthenticated()">
            <li class="dropdown">

                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                        <%--add link to owner page--%>
                    <li>
                        <a href="${CONTEXT_PATH}/user-menu/show-user/<security:authentication property="principal.username"/>">
                            <i class="fa fa-user fa-fw"></i> User Profile</a>
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
            </li>


        </security:authorize>

        <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
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

                        <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                            <li>
                                <a href="${pageContext.request.contextPath}/course-menu/delete-course"><spring:message
                                        code="menu.delete"/></a></li>
                        </security:authorize>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw"></i>Task Tags<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                            <li><a href="${pageContext.request.contextPath}/group-menu/create-group">
                                Create Task Tag
                            </a></li>
                        </security:authorize>

                        <li><a href="${pageContext.request.contextPath}/group-menu/show-groups">Show tags</a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                    <li>
                        <a href="#"><i class="fa fa-fw"></i>Lessons<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">

                            <li>
                                <a href="${pageContext.request.contextPath}/lesson-menu/create-lesson"><spring:message
                                        code="menu.add"/></a></li>

                        </ul>
                    </li>
                </security:authorize>
                <%--todo remove teacher from user menu--%>
                <li>
                    <a href="#"><i class="fa fa-fw"></i>Task Menu<samp class="fa arrow"></samp></a>
                    <ul class="nav nav-second-level">
                        <security:authorize
                                access="hasAnyRole('ROLE_ADMIN,ROLE_TEACHER')">
                            <li>
                                <a href="${pageContext.request.contextPath}/task-menu/create-task"><spring:message
                                        code="menu.add"/></a></li>
                        </security:authorize>

                        <li><a href="${pageContext.request.contextPath}/task-menu/groups"><spring:message
                                code="task.menu.show"/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>
