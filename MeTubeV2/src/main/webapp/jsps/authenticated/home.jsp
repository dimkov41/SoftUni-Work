<%@ page import="meTube.util.Constants" %>
<%@ page import="meTube.util.ResourceConstants" %><%--
  Created by IntelliJ IDEA.
  User: Radostin Dimkov
  Date: 2/15/2019
  Time: 2:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MeTube</title>
    <style type="text/css">
        <%@include file="../../resources/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="<%= ResourceConstants.HEADER_PATH %>"/>
<main>
    <p>Welcome, <%= request.getSession().getAttribute(Constants.USERNAME_FIELD_NAME)%></p>
    <hr/>
</main>
<jsp:include page="<%= ResourceConstants.FOOTER_PATH %>"/>
</body>
</html>
