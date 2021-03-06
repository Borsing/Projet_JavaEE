<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!-- jspf showing how to login. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<form action="login" method="POST">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--2-col  mdl-cell--5-offset">
            <h3>Se connecter</h3><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="email" id="email" name="1_email" required="true">
                <label class="mdl-textfield__label" for="email">Adresse mail</label>
                <span class="mdl-textfield__error">Veuillez rentrer une adresse valide.</span>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" id="password" name="2_password" required="true">
                <label class="mdl-textfield__label" for="password">Mot de passe</label>
            </div><br>

            <a style="font-size: xx-small" href="change-password">Mot de passe oublié</a><br>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Se connecter" />
        </div>
    </div>

</form>

</html>

