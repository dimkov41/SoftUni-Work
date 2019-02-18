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
        <h1>Upload</h1>
        <hr/>
        <form action="<%=Constants.TUBE_UPLOAD_URL %>" method="post">
            <label for="title"><h3>Title</h3></label>
            <input type="text" id="title" name=<%= Constants.TUBE_TITLE_FIELD_NAME %> />

            <label for="password"><h3>Author</h3></label>
            <input type="text" id="password" name=<%= Constants.TUBE_AUTHOR_FIELD_NAME %> />

            <label for="youtubeLink"><h3>Youtube Link</h3></label>
            <input type="text" id="youtubeLink" name=<%= Constants.TUBE_YOUTUBE_LINK_FIELD_NAME %> />

            <label for="description"><h3>Description</h3></label>
            <input type="text" id="description" name=<%= Constants.TUBE_DESCRIPTION_FIELD_NAME %> />
            <br/>
            <br/>
            <input type="submit" value="Upload" id="button">
        </form>
    </div>
</main>
<jsp:include page="<%= ResourceConstants.FOOTER_PATH %>"/>
</body>
</html>
