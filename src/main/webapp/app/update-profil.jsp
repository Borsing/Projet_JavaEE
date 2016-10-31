<!-- jspf showing account parameters while connected | Cannot appear while not connected. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="update-profil" method="POST">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
            <h3>Informations du compte</h3><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="first_name" name="first_name" required="true" value="${session.first_name}">
                <label class="mdl-textfield__label" for="first_name">Prénom</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="last_name" name="last_name" required="true" value="${session.last_name}">
                <label class="mdl-textfield__label" for="last_name">Nom</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="company" name="company" value="${session.company}">
                <label class="mdl-textfield__label" for="company">Société</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="email" id="new_email" name="new_email" required="true" value="${session.mail}">
                <label class="mdl-textfield__label" for="new_email">Adresse mail</label>
                <span class="mdl-textfield__error">Veuillez rentrer une adresse valide.</span>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" id="password" name="password" required="true" value="${session.password}">
                <label class="mdl-textfield__label" for="password">Mot de passe</label>
            </div><br>

            <input type="hidden" name="mydata" id="last_email" name="last_email" value="${session.mail}"/>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Enregistrer les modifications" />
        </div>
    </div>

</form>

<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
        <form action="unregister" method="post">
            <input type="hidden" name="email" value="${session.mail}"/>
            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
                   value="Se désinscrire" type="submit"/>
        </form>
    </div>
</div>