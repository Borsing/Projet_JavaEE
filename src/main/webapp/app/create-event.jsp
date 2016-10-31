<!-- jspf showing how to create an event | Can only appear while connected. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="create-event" method="post">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--3-col"></div>
        <div class="mdl-cell mdl-cell--6-col">
            <h3>Creer un event</h3><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="name" name="1_name" required/>
                <label class="mdl-textfield__label" for="name">Nom de l'event</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="address" name="5_address" required/>
                <label class="mdl-textfield__label" for="address">Lieu</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="description" name="2_description" required/>
                <label class="mdl-textfield__label" for="description">Description</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="date" id="begin_date" name="3_begin_date" required/>
                <label class="mdl-textfield__label" for="begin_date">Date de debut</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="date" id="end_date" name="4_end_date" required />
                <label class="mdl-textfield__label" for="end_date">Date de fin</label>
            </div><br>

            <input type="hidden" name="6_organizer_id" value="${session.mail}"/>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Creer l'event" />

        </div>
        <div class="mdl-cell mdl-cell--3-col"></div>
    </div>

</form>

</html>

<!--
<dialog class="mdl-dialog" id="modal-create-event">
    <script>
        var dialogCreateEvent = document.querySelector('#modal-create-event');
        var showDialogButtonCreateEvent = document.querySelector('#show-dialog-create-event');
        if (! dialogCreateEvent.showModal) {
            dialogPolyfill.registerDialog(dialogLogin);
        }
        showDialogButtonCreateEvent.addEventListener('click', function() {
            dialogCreateEvent.showModal();
        });
    </script>
</dialog>
-->