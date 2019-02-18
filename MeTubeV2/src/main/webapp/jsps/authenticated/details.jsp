<%@ page import="meTube.util.ResourceConstants" %>
<%@ page import="meTube.util.Constants" %>
<%@ page import="meTube.domain.models.view.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        <%@include file="../../resources/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="<%= ResourceConstants.HEADER_PATH %>"/>
<div class="title">
    <% TubeDetailsViewModel tubeDetailsViewModel = (TubeDetailsViewModel) request.getSession().getAttribute(Constants.TUBES_DETAILS_FIELD_NAME); %>
    <h1><%=tubeDetailsViewModel.getTitle()%></h1>
</div>
<section class = "mainSection">
    <div class = "video">
        <iframe
                src="<%=tubeDetailsViewModel.getYoutubeLink() %>">
        </iframe>
    </div>
    <div class = "info">
        <h1 id="author"><%=tubeDetailsViewModel.getAuthor() %></h1>
        <h2 id="views"><%=tubeDetailsViewModel.getViews() %> views</h2>
        <p><%=tubeDetailsViewModel.getDescription() %></p>
    </div>
</section>
<jsp:include page="<%= ResourceConstants.FOOTER_PATH %>"/>
</body>
</html>