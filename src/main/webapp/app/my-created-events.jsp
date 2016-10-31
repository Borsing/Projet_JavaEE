<!-- jspf template to display a list of events -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--3-col"></div>
    <div class="mdl-cell mdl-cell--6-col">
        <h3>Mes créations</h3>
    </div>
    <div class="mdl-cell mdl-cell--3-col"></div>
</div>

<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
        <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
            <thead>
            <tr>
                <th></th>
                <th>Nom de l'évènement</th>
                <th>Debut</th>
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
                    <td class="mdl-data-table__cell--non-numeric">${item.begin_date}</td>
                    <td class="mdl-data-table__cell--non-numeric">${item.end_date}</td>
                    <td class="mdl-data-table__cell--non-numeric">${item.address}</td>
                    <td class="mdl-data-table__cell--non-numeric">
                        <button id="demo-menu-lower-left-${item.id}"
                                class="mdl-button mdl-js-button mdl-button--icon"
                                >
                            <i class="material-icons">more_vert</i>
                        </button>
                        <ul class="mdl-menu mdl-menu--bottom-left mdl-js-menu mdl-js-ripple-effect"
                            for="demo-menu-lower-left-${item.id}">
                            <li class="mdl-menu__item" onclick="location.href = 'edit-event?id=${item.id}' ; ">
                                Modifier l'évènement
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