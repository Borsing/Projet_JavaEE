<!-- jspf template to display a list of events -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="edit-event" method="post">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
            <h3>Edition de l'évènement</h3><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="name" name="name" required value="${data.name}"/>
                <label class="mdl-textfield__label" for="name">Nom de l'évènement</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="address" name="address" required value="${data.address}"/>
                <label class="mdl-textfield__label" for="address">Lieu</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="description" name="description" required value="${data.description}"/>
                <label class="mdl-textfield__label" for="description">Description</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="date" id="begin_date" name="begin_date" required/>
                <label class="mdl-textfield__label" for="begin_date">Date de début</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="date" id="end_date" name="end_date" required/>
                <label class="mdl-textfield__label" for="end_date">Date de fin</label>
            </div><br>

            <input type="hidden" name="id" value="${data.id}"/>
            <input type="hidden" name="organizer_id" value="${session.mail}"/>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Editer l'évènement" /><br>
        </div>
    </div>
</form>

<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--6-col mdl-cell--3-offset">
        <form action="delete-event" method="post">
            <input type="hidden" id="id" name="id" value="${data.id}"/>
            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Supprimer l'event" />
        </form>
    </div>
</div>

</html>

