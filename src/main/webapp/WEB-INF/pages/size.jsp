<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.title.size"/></title>
</head>
<body>
    <p><spring:message code="label.size.message"/>=<%=request.getAttribute("size")%><p/>
</body>
</html>
