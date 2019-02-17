<%@ page import="meTube.util.ResourceConstants" %>
<%@ page import="meTube.util.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MeTube</title>

    <style type="text/css">
        <%@include file="../../resources/css/style.css"%>
    </style>
</head>
<body>
    <jsp:include page="<%= ResourceConstants.HEADER_PATH %>" />
    <main>
        <div class="main">
            <h1>Welcome to MeTube&reg;!</h1>
            <h3>The simplest, easiest to use, most comfortable Multimedia Application.</h3>
            <hr/>
            <p><a href="<%=Constants.LOGIN_URL %>">Login</a> if you have an account or <a href="<%=Constants.REGISTER_URL %>">Register</a> now and start tubing.</p>
        </div>
        <hr/>
    </main>
    <jsp:include page="<%= ResourceConstants.FOOTER_PATH %>" />
</body>
</html>
