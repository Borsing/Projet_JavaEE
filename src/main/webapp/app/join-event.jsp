<!-- jspf to join an event. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="join-event" method="POST">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
            <h3>Joindre l'évènement</h3><br>

            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" id="fname" name="first_name" required="true">
                <label class="mdl-textfield__label" for="fname">Prénom</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" id="lname" name="last_name" required="true">
                <label class="mdl-textfield__label" for="lname">Nom</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" id="company" name="company" required>
                <label class="mdl-textfield__label" for="company">Société</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="email" id="email" name="email" required="true">
                <label class="mdl-textfield__label" for="email">Adresse mail</label>
                <span class="mdl-textfield__error">Veuillez rentrer une adresse valide.</span>
            </div><br>

            <input type="hidden" name="event_id" value="${id}"/>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Joindre l'évènement" />
        </div>
    </div>

</form>
</html>