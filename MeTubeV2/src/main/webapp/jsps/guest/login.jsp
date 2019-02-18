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
    <div class="form">
        <h1>Login</h1>
        <hr/>
        <form action="<%=Constants.LOGIN_URL %>" method="post">
            <label for="username"><h3>Username</h3></label>
            <input type="text" id="username" name=<%=Constants.USERNAME_FIELD_NAME %> />
            <label for="password"><h3>Password</h3></label>
            <input type="password" id="password" name=<%=Constants.PASSWORD_FIELD_NAME %> />
            <br/>
            <br/>
            <input type="submit" value="Login" id="button">
        </form>
    </div>
</main>
<jsp:include page="<%= ResourceConstants.FOOTER_PATH %>"/>
</body>
</html>
