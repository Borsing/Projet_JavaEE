<!-- Main html entrance. -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="header.jsp"/>
    <head>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/material.css">
    </head>

    <body>
        <jsp:include page="body.jsp"/>
        <%--<script defer src="${pageContext.servletContext.contextPath}/bootstrap-material-design-master/dist/js/material.min.js"></script>--%>
        <script src="${pageContext.servletContext.contextPath}/material.min.js"></script>
    </body>
</html>