<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jspf" />

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
<script type="text/javascript" src="../style.css"></script>


<script type="text/javascript">
    $.material.init();
</script>
<div class="container">
    <div class="bs-docs-section">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="well bs-component">
                    <form class="form-horizontal">
                        <fieldset>
                            <legend>Please log in </legend>
                            <div class="form-group is-empty">
                                <label for="mail" class="col-md-2 control-label">Email</label>

                                <div class="col-md-10">
                                    <label for="mail" class="control-label">Email address</label>
                                    <input type="email" class="form-control" id="mail" placeholder="adri.cadoret@gmail.com">
                                    <span class="help-block">Please enter a valid email address</span>
                                </div>
                            </div>
                            <div class="form-group is-empty">
                                <label for="password" class="col-md-2 control-label">Password</label>

                                <div class="col-md-10">
                                    <label for="password" class="control-label">Password</label>
                                    <input type="password" class="form-control" id="password">
                                    <span class="help-block">Please enter a password</span>                                </div>
                            </div>
                            <div class="form-group" style="margin-top: 0;"> <!-- inline style is just to demo custom css to put checkbox below input above -->
                                <div class="col-md-offset-2 col-md-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"><span class="checkbox-material"><span class="check"></span></span> Remember me
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-2">
                                    <button type="submit" class="btn btn-primary">Login</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>

</body>


</html>