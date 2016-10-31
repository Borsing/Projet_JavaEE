<!-- jspf showing how to register. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="register" method="POST">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
            <h3>S'enregistrer</h3><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="fname" name="4_first_name" required="true">
                <label class="mdl-textfield__label" for="fname">Prénom</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="lname" name="3_last_name" required="true">
                <label class="mdl-textfield__label" for="lname">Nom</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="company" name="5_company" required>
                <label class="mdl-textfield__label" for="company">Société</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="email" id="email" name="1_email" required="true">
                <label class="mdl-textfield__label" for="email">Adresse mail</label>
                <span class="mdl-textfield__error">Veuillez rentrer une adresse valide.</span>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" id="password" name="2_password" required="true">
                <label class="mdl-textfield__label" for="password">Mot de passe</label>
            </div><br>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="S'enregistrer" />
        </div>
    </div>

</form>
</html>