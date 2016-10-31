<!-- jspf showing how to login. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<form action="login" method="POST">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--3-col"></div>
        <div class="mdl-cell mdl-cell--6-col">
            <h3>Se connecter</h3><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="email" id="email" name="email" required="true">
                <label class="mdl-textfield__label" for="email">Adresse mail</label>
                <span class="mdl-textfield__error">Veuillez rentrer une adresse valide.</span>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" id="password" name="password" required="true">
                <label class="mdl-textfield__label" for="password">Mot de passe</label>
            </div><br>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Se connecter" />
        </div>
        <div class="mdl-cell mdl-cell--3-col"></div>
    </div>

</form>
</html>

