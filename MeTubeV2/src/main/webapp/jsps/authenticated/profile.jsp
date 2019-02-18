<%@ page import="meTube.util.Constants" %>
<%@ page import="meTube.util.ResourceConstants" %>
<%@ page import="java.util.List" %>
<%@ page import="meTube.domain.models.view.TubeProfileViewModel" %>
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
    <span class="welcomeMessage">
        <h1>@<%=request.getSession().getAttribute(Constants.USERNAME_FIELD_NAME) %></h1>
        <h1>(<%=request.getSession().getAttribute(Constants.EMAIL_FIELD_NAME) %>)</h1>
    </span>
    <hr/>
    <table class="table">
        <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Author</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% List<TubeProfileViewModel> tubes = (List<TubeProfileViewModel>) request.getSession().getAttribute(Constants.TUBES_PROFILE_FIELD_NAME); %>
            <% for (int i = 0; i < tubes.size(); i++) { %>
                <%TubeProfileViewModel currentTube = tubes.get(i); %>
                <tr>
                    <th><%=i+1%></th>
                    <th><%=currentTube.getTitle()%></th>
                    <th><%=currentTube.getAuthor()%></th>
                    <th><a href="/tube/details/<%=currentTube.getId()%>">Details</a></th>
                </tr>
            <% } %>
        </tbody>
    </table>

</main>
<jsp:include page="<%= ResourceConstants.FOOTER_PATH %>"/>
</body>
</html>
