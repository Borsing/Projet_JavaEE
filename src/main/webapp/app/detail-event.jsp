<!-- jspf template to display a list of events -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--3-col"></div>
    <div class="mdl-cell mdl-cell--6-col">
        <h3>Detail de l'event</h3><br>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="name" required value="${data.name}" readonly/>
            <label class="mdl-textfield__label" for="name">Nom de l'event</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="address" required value="${data.address}" readonly/>
            <label class="mdl-textfield__label" for="address">Lieu</label>
        </div><br>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="description" required value="${data.description}" readonly/>
            <label class="mdl-textfield__label" for="description">Description</label>
        </div><br>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="date" id="begin_date" required readonly/>
            <label class="mdl-textfield__label" for="begin_date">Date de debut</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="date" id="end_date" required readonly/>
            <label class="mdl-textfield__label" for="end_date">Date de fin</label>
        </div><br>

        <input type="hidden" name="mydata" id="id" value="${id}"/>

        <input type="hidden" name="mydata" id="organizer_id" value="${session.mail}"/>

        <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
               value="participer a l'event" onclick="location.href='join-event?id=${data.id}';"/>
    </div>
    <div class="mdl-cell mdl-cell--3-col"></div>
</div>
</html>
