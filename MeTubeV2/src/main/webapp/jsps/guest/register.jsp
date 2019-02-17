<%@ page import="meTube.util.Constants" %>
<%@ page import="meTube.util.ResourceConstants" %>
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
    <h1>Register</h1>
    <hr/>
    <form action="<%=Constants.REGISTER_URL %>" method="post">
        <label for="username"><h3>Username</h3> </label>
        <input type="text" id="username" name=<%=Constants.USERNAME_FIELD_NAME%> />
        <label for="password"><h3>Password</h3> </label>
        <input type="password" id="password" name=<%=Constants.PASSWORD_FIELD_NAME%> />
        <label for="password"><h3>Confirm password</h3> </label>
        <input type="password" id="confirmPassword" name=<%=Constants.CONFIRM_PASSWORD_FIELD_NAME%> />
        <label for="password"><h3>Email</h3> </label>
        <input type="text" id="email" name=<%=Constants.EMAIL_FIELD_NAME%> />
        <br/>
        <br/>
        <input type="submit" value="Register" id="button">
    </form>
</main>
<jsp:include page="<%= ResourceConstants.FOOTER_PATH %>" />
</body>
</html>
