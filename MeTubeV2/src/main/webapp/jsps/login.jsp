<%--
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
        <%@include file="../resources/css/style.css"%>
        <%@include file="../resources/css/auth.css"%>
    </style>
</head>
<body>
<jsp:include page="templates/header.jsp" />
<main>
    <h1>Login</h1>
    <hr/>
    <form action="/login" method="post">
        <label for="username"><h3>Username</h3> </label>
        <input type="text" id="username" />
        <label for="password"><h3>Password</h3> </label>
        <input type="password" id="password" />
        <br/>
        <br/>
        <input type="submit" value="Login" id="button">
    </form>
</main>
<jsp:include page="templates/footer.jsp" />
</body>
</html>
