<%@ page import="meTube.util.Constants" %>
<%@ page import="meTube.util.ResourceConstants" %>
<%@ page import="meTube.domain.models.view.TubeHomeViewModel" %>
<%@ page import="java.util.List" %><%--
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
    <h1 class="welcomeMessage">Welcome, <%= request.getSession().getAttribute(Constants.USERNAME_FIELD_NAME)%></h1>
    <hr/>
    <section class="cards">
        <%for (TubeHomeViewModel tubeHomeViewModel : (List<TubeHomeViewModel>)request.getSession().getAttribute(Constants.TUBES_HOME_FIELD_NAME)) { %>
        <article class="card">
            <img src="<%=tubeHomeViewModel.getYoutubeThumbnail() %>" alt="Youtube Thumbnail">
            <div class="text">
                <h3><%=tubeHomeViewModel.getTitle() %></h3>
                <p><%=tubeHomeViewModel.getAuthor() %></p>
            </div>
        </article>
        <% } %>
    </section>
</main>
<jsp:include page="<%= ResourceConstants.FOOTER_PATH %>"/>
</body>
</html>
