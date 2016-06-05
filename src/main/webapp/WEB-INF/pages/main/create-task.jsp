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
    <%@include file="/WEB-INF/pages/component/navbar.jsp"%>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Blank</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Task Page
                </div>

                <div class="panel-body">
                    <div class="col-lg-6">
                        <form:form action="${CONTEXT_PATH}/task-menu/add-task" modelAttribute="task"
                                   method="post" role="form">

                        <div class="form-group">
                            <label><spring:message code="create.task.name"/></label>
                            <form:input path="title" class="form-control"/>
                            <form:errors path="title"/>
                        </div>

                        <div class="form-group">
                            <label>
                                <spring:message code="create.task.group"/>:<br>
                            </label>
                            <form:input path="groupName" class="form-control"/>
                            <form:errors path="groupName"/>
                        </div>


                        <div class="form-group">
                            <label>
                                <spring:message code="create.task.description"/>
                            </label>
                            <form:textarea path="description" class="form-control" rows="3"/>
                            <form:errors path="description"/>
                        </div>

                        <div class="form-group">
                            <label>
                                <spring:message code="create.task.template"/>:<br>
                            </label>

                            <p class="help-block"><code>public int methodName(int a, int b) {}</code></p>
                            <form:textarea id="templateTextArea" path="template" class="form-control" rows="3"/>
                            <form:errors path="template"/>
                        </div>

                        <div class="form-group">
                            <label>Solution</label>

                            <p class="help-block"><code>public int methodName(int a, int b) { return a + b; }</code></p>
                            <form:textarea id="solutionTextArea" path="solution" class="form-control cm-s-bar"
                                           rows="10"/>
                            <form:errors path="solution"/>
                        </div>

                        <div class="form-group">
                            <label><spring:message code="create.task.datapoint"/></label>

                            <p class="help-block"><code>25-10,15</code></p>
                            <textarea name="data_points" class="form-control" rows="8"><c:if
                                    test="${testData != null}">${testData}</c:if></textarea>
                        </div>

                        <div class="form-group">
                            <label>
                                <spring:message code="create.task.examples"/>
                            </label>
                            <form:textarea path="examples" class="form-control" rows="3"/>
                            <form:errors path="examples"/>
                        </div>

                        <input type="submit" class="btn btn-default" value="<spring:message code="create"/>">
                    </div>

                    <c:if test="${task.id != null}">
                        <input type="hidden" name="id" value="${task.id}"/>
                    </c:if>
                    <input type="hidden" name="mainTitle" value="${mainTitle}"/>
                        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                    </form:form>
                </div>
            </div>
        </div>


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


<script src="${codemirrorJs}"></script>
<script src="${clikeJs}"></script>
<script src="${fullscreanJS}"></script>

<script>
    var editor1 = CodeMirror.fromTextArea(document.getElementById("solutionTextArea"), {
        lineNumbers: true,
        mode: "text/x-java"
    });

    var editor2 = CodeMirror.fromTextArea(document.getElementById("templateTextArea"), {
        lineNumbers: true,
        mode: "text/x-java"
    });
</script>

</body>

</html>
