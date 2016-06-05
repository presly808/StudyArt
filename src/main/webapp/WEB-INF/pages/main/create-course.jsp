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

    <link href="<c:out value="${simplemdeCss}"/>" rel="stylesheet">

    <link href="<c:out value="${appCss}"/>" rel="stylesheet">



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
    <%@include file="/WEB-INF/pages/component/navbar.jsp"%>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"> Create Course
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Create Course
                </div>

                <div class="panel-body">
                    <div class="col-lg-6">
                        <form:form action="${CONTEXT_PATH}/course-menu/add-course" modelAttribute="course"
                                   method="post" role="form">

                        <div class="form-group">
                            <label><spring:message code="create.course.title"/></label>
                            <form:input path="title" class="form-control"/>
                            <form:errors path="title"/>
                        </div>

                        <div class="form-group">
                            <label>
                                <spring:message code="create.course.description"/>:<br>
                            </label>
                            <form:textarea id="description" path="description" class="form-control" rows="10"/>
                            <form:errors path="description"/>
                        </div>

                            <div class="form-group">
                                <div class="ui-widget">
                                    <label for="lessons">Attach lessons</label>
                                    <input id="lessons" name="courseLessons" class="form-control" size="50">
                                </div>
                            </div>


                        <input type="submit" class="btn btn-default" value="<spring:message code="create.course.add.course"/>">
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

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

<spring:url value="/resources/codemirror-5.15.0/lib/codemirror.js" var="codemirrorJs"/>
<spring:url value="/resources/codemirror-5.15.0/mode/clike/clike.js" var="clikeJs"/>
<spring:url value="/resources/codemirror-5.15.0/addon/display/fullscreen.js" var="fullscreanJS"/>

<script src="${codemirrorJs}"></script>
<script src="${clikeJs}"></script>
<script src="${fullscreanJS}"></script>

<script src="${jqueryUiJs}"></script>

<script src="${simplemdeJs}"></script>

<script>
    var simplemde = new SimpleMDE({ element: $("#description")[0] , autosave: {
        enabled: true,
        uniqueId: "CreateCourseId",
        delay: 1000
    }});

    $(function() {
        function split( val ) {
            return val.split( /,\s*/ );
        }
        function extractLast( term ) {
            return split( term ).pop();
        }

        $( "#lessons" )
            // don't navigate away from the field on tab when selecting an item
                .bind( "keydown", function( event ) {
                    if ( event.keyCode === $.ui.keyCode.TAB &&
                            $( this ).autocomplete( "instance" ).menu.active ) {
                        event.preventDefault();
                    }
                })
                .autocomplete({
                    source: function( request, response ) {
                        $.getJSON( "${CONTEXT_PATH}/search/json?type=lesson", { // todo replace url
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
