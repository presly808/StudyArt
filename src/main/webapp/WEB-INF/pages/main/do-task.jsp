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
    <%@include file="/WEB-INF/pages/component/navbar.jsp"%>

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

                            <form id="checkTaskForm" action="${CONTEXT_PATH}/task-menu/check-task/json" role="form"
                                  method="post">
                                <div class="form-group">
                                    <label for="codeTextArea">${task.title}</label>
                                    <textarea id="codeTextArea" class="form-control"
                                              name="userCode">${template}</textarea>
                                </div>
                                <input type="hidden" name="id" value="${task.id.toString()}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <div class="row">
                                    <div class="col-xs-6">
                                        <input id="checkTaskButton" type="button" class="btn btn-default"
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

                            <div id="errorContainer" class="panel panel-danger" style="display: none">
                                <div class="panel panel-heading">
                                    <label id="errorTitle"></label>
                                </div>
                                <div class="panel panel-body">
                                    <p id="errorMessage" class="help-block"></p>
                                </div>
                            </div>

                            <div id="testResults" class="panel panel-default" style="display: none">
                                <div id="panel-heading">
                                    <label id="taskName"><spring:message code="check.task"/></label>
                                </div>

                                <div class="panel-body">
                                    <table class="table-responsive" style="width:100%">
                                        <thead>
                                        <tr>
                                            <th>in args</th>
                                            <th>real</th>
                                            <th>expected</th>
                                            <th>done</th>
                                        </tr>
                                        </thead>
                                        <tbody id="resultTableBody">
                                        </tbody>

                                    </table>
                                </div>
                                <p id="testStatus"></p>
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



<script src="${codemirrorJs}"></script>
<script src="${clikeJs}"></script>
<script src="${fullscreanJS}"></script>
<script src="${showHintJS}"></script>
<script src="${anyWordHintJs}"></script>

<script>

    /*CodeMirror.commands.autocomplete = function(cm) {
        cm.showHint({hint: CodeMirror.hint.anyword});
    }*/

    var editor1 = CodeMirror.fromTextArea(document.getElementById("codeTextArea"), {
        lineNumbers: true,
        mode: "text/x-java",
        extraKeys: {
            "Ctrl-Space": "autocomplete",
            "F11": function(cm) {
                cm.setOption("fullScreen", !cm.getOption("fullScreen"));
                $("#mainNavbar").css("z-index",0);
            },
            "Esc": function(cm) {
                if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
                $("#mainNavbar").css("z-index",1000);
            }
        }
    });

    $("#checkTaskButton").on("click", function () {
        editor1.save();

        var data = $("#checkTaskForm").serialize();
        // todo check without save
        data.userCode = editor1.getValue();

        $("#resultTableBody").html("");
        $("#testResults").hide();
        $("#errorContainer").hide();
        $.ajax({
            type: "POST",
            url: "${CONTEXT_PATH}/task-menu/check-task/json",
            data: data,
            success: function (data, textStatus, jqXHR) {

                var testResultObject = data;
                var att = $(testResultObject).attr("message");
                if (typeof att !== typeof undefined && att !== false) {
                    $("#errorTitle").text(testResultObject.title);
                    $("#errorMessage").text(testResultObject.message);
                    $("#errorContainer").show();
                    return;
                }

                $("#testStatus").text(testResultObject.status);
                $("#taskName").text(testResultObject.taskName);
                var tableTestBody = $("#resultTableBody");
                tableTestBody.innerHTML = "";

                var resultArray = testResultObject.resultTablePartList;
                for (var dataRowIndex in resultArray) {
                    var dataRow = resultArray[dataRowIndex];
                    var rowResult = "<td>" + dataRow.inArgsTemplate + "</td>" +
                            "<td>" + dataRow.actualValue + "</td>" +
                            "<td>" + dataRow.expectedValue + "</td>" +
                            "<td>" + dataRow.done + "</td>";

                    var rowCssType = dataRow.done === "OK" ? "\"alert alert-success\"" : "\"alert alert-danger\"";

                    tableTestBody.append("<tr class=" + rowCssType + " role=\"alert\">" + rowResult + "</tr>");
                }

                $("#testResults").show();

            }
        });
    });

</script>

</body>

</html>
