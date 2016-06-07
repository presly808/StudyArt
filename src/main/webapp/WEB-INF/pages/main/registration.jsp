<%@ include file="/WEB-INF/pages/component/jsp-include.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.registration.form"/></title>

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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">

    <div class="panel panel-default">
        <div class="panel-heading">Registration</div>
        <div class="panel-body">
            <div class="row">
                <form:form action="${pageContext.request.contextPath}/registration" modelAttribute="user" method="post">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="InputName"><spring:message code="user.name"/></label>

                            <div class="input-group">
                                <form:input path="name" type="text" class="form-control" name="InputName" id="InputName"
                                            placeholder="Enter Name"
                                            required="true"/>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                            <form:errors path="name"/>
                        </div>
                        <div class="form-group">
                            <label for="InputEmail"><spring:message code="user.email"/></label>

                            <div class="input-group">
                                <form:input path="email" type="email" class="form-control" id="InputEmailFirst"
                                            name="InputEmail"
                                            placeholder="Enter Email" required="true"/>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                            <form:errors path="email"/>
                        </div>
                        <div class="form-group">
                            <label for="InputPassword">Input pass</label>

                            <div class="input-group">
                                <form:input path="password" type="password" class="form-control" id="InputPassFirst"
                                            name="InputPassword"
                                            placeholder="Input Pass" required="true"/>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                            <form:errors path="password" />
                        </div>
                        <div class="form-group">
                            <label for="ChooseType">Choose type</label>

                            <div class="input-group">
                                <form:select path="userType" id="ChooseType" class="form-control" multiple="true">
                                    <form:option value="ROLE_STUDENT" label="Student"/>
                                    <form:option value="ROLE_TEACHER" label="Teacher"/>
                                </form:select>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                            <form:errors  path="userType" />
                        </div>
                        <input type="submit" name="submit" id="submit" value="Register" class="btn btn-info pull-left">
                    </div>
                </form:form>

                <c:if test="${message != null}">
                <div class="alert alert-danger" role="alert">

                        <div class="alert alert-danger">
                            <span class="glyphicon glyphicon-remove"></span><strong><c:out value="${message}"/></strong>
                        </div>

                </div>
                </c:if>

            </div>
        </div>
    </div>


</div>


<%@include file="/WEB-INF/pages/component/js-include.jsp" %>
<script src="${jqueryJs}"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${bootstrapJs}"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${metisMenuJs}"></script>

<!-- Custom Theme JavaScript -->
<script src="${sbAdminJs}"></script>

</body>
</html>
