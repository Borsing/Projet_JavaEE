<%@ page import="modules.organizer.OrganizerEntity" %>
<!-- jspf template to display a list of events -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>



<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--4-col"></div>

    <div class="mdl-cell mdl-cell--4-col">
        <div class="mdl-tabs__panel" id="events">
            <label class="mdl-button mdl-js-button mdl-button--icon" for="sample6">
                <i class="material-icons">search</i>
            </label>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" id="sample6">
            </div>
            <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Event</th>
                    <th>Debut</th>
                    <th>Fin</th>
                    <th>Lieu</th>
                    <th>Participants</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">Web 2 Day</td>
                    <td>25/06/2016 : 10h30</td>
                    <td>28/06/2016 : 22h00</td>
                    <td>Roubaix</td>
                    <td>2 700</td>
                </tr>
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">Vieilles charrues</td>
                    <td>12/07/2016 : 10h30</td>
                    <td>16/07/2016 : 15h00</td>
                    <td>Carhaix</td>
                    <td>385 000</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="mdl-cell mdl-cell--4-col"></div>
</div>