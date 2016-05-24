<%@include file="/WEB-INF/pages/component/jsp-include.jsp"%>

<html>
<head>

    <title><spring:message code="title.login"/></title>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <%@include file="/WEB-INF/pages/component/css-include.jsp"%>

    <!-- Bootstrap Core CSS -->
    <link href="<c:out value="${bootstrapMinCss}"/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:out value="${metisMenuCss}"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:out value="${sbAdminCss}"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:out value="${fontAwesomeCss}"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <span style="float: right">
                        <a href="?lang=en">en</a>
                        |
                        <a href="?lang=ru">ru</a>
                        |
                        <a href="?lang=ua">ua</a>
                    </span>
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty message}">
                        <div class="error" style="color: red">${message}</div>
                    </c:if>
                    <form role="form" name='loginForm' action="j_spring_security_check" method='POST'>
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="<spring:message code="user.email"/>"
                                       name="username" type="email" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<spring:message code="user.password"/>"
                                       name="password" type="password" value="">
                            </div>
                            <div class="checkbox">
                                <label><a href="registration-form"><spring:message code="registration"/></a></label>
                            </div>



                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <!-- Change this to a button or input when using this as a form -->
                            <input  type="submit" class="btn btn-lg btn-success btn-block" value="<spring:message code="login"/>">
                            <%--<a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>--%>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/component/js-include.jsp"%>
<script src="${jqueryJs}"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${bootstrapJs}"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${metisMenuJs}"></script>

<!-- Custom Theme JavaScript -->
<script src="${sbAdminJs}"></script>

</body>
</html>
