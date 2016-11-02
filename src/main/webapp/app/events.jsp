<!-- jspf template to display a list of events -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
        <h3>Les évènements</h3>
    </div>
</div>

<div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
    <form action="events" method="get">

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable mdl-textfield--floating-label">
            <label class="mdl-button mdl-js-button mdl-button--icon" for="name">
                <i class="material-icons">search</i>
            </label>
            <div class="mdl-textfield__expandable-holder">

                <input class="mdl-textfield__input" type="text" id="name" name="1_name" required>

                <label class="mdl-textfield__label" for="name">Rechercher via le nom</label>
            </div>
        </div>
    </form><br>
</div>

<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
            <thead>
            <tr>
                <th></th>
                <th>Nom de l'évènement</th>
                <th>Début</th>
                <th>Fin</th>
                <th>Lieu</th>
                <th></th>
            </tr>
            </thead>

            <tbody>

            <c:forEach items="${data}" var="item">
                <tr>
                    <td><i class="material-icons" >event</i></td>
                    <td class="mdl-data-table__cell--non-numeric">${item.name}</td>
                    <td class="mdl-data-table__cell--non-numeric"><fmt:formatDate pattern="dd/MM/yyyy HH:mm"
                                                                                  value="${item.begin_date}" /></td>
                    <td class="mdl-data-table__cell--non-numeric"><fmt:formatDate pattern="dd/MM/yyyy HH:mm"
                                                                                  value="${item.end_date}" /></td>
               <td class="mdl-data-table__cell--non-numeric">${item.address}</td>
                    <td class="mdl-data-table__cell--non-numeric">
                        <button id="demo-menu-lower-left-${item.id}"
                                class="mdl-button mdl-js-button mdl-button--icon"
                                >
                            <i class="material-icons">more_vert</i>
                        </button>
                        <ul class="mdl-menu mdl-menu--bottom-left mdl-js-menu mdl-js-ripple-effect"
                            for="demo-menu-lower-left-${item.id}">
                            <li class="mdl-menu__item" onclick="location.href = 'join-event?1_id=${item.id}' ; ">
                                Participer à l'évènement
                            </li>
                            <li class="mdl-menu__item" onclick="location.href = 'detail-event?1_id=${item.id}' ; ">
                                Plus de détails
                            </li>
                        </ul>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
</html>