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
    <form action="/register" method="post">
        <label for="username"><h3>Username</h3> </label>
        <input type="text" id="username" />
        <label for="password"><h3>Password</h3> </label>
        <input type="password" id="password" />
        <label for="password"><h3>Confirm password</h3> </label>
        <input type="password" id="confirmPassword" />
        <label for="password"><h3>Email</h3> </label>
        <input type="text" id="email" />
        <br/>
        <br/>
        <input type="submit" value="Register" id="button">
    </form>
</main>
<jsp:include page="templates/footer.jsp" />
</body>
</html>
