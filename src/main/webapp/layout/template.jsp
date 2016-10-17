<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jspf"></jsp:include>

<html>
<head>
    <title>EventManager</title>
    <!-- Material Design fonts -->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/icon?family=Material+Icons">

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- Bootstrap Material Design -->
    <!-- Bootstrap Material Design -->
    <link rel="stylesheet" type="text/css" href="../bootstrap-material-design-master/dist/css/bootstrap-material-design.css">
    <link rel="stylesheet" type="text/css" href="../bootstrap-material-design-master/dist/css/ripples.min.css">

</head>
<body>
<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../bootstrap-material-design-master/dist/js/material.js"></script>
<script type="text/javascript" src="../bootstrap-material-design-master/dist/js/ripples.min.js"></script>
<script type="text/javascript">
    $.material.init();
</script>

<jsp:include page="body.jsp" />

</body>

</html>