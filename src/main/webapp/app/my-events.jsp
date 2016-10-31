<!-- jspf showing my events while connected | Cannot appear while not connected. -->
<!-- jspf template to display a list of events -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--3-col"></div>
    <div class="mdl-cell mdl-cell--6-col">
        <h3>Mes events</h3><br>

        <form action="my-events" method="post">
            <div class="mdl-textfield mdl-js-textfield">
                <label class="mdl-button mdl-js-button mdl-button--icon" for="email">
                    <i class="material-icons">search</i>
                </label>
                <c:choose>
                    <c:when test="${session==null}">
                        <input class="mdl-textfield__input" type="email" id="email" name="email" required>
                    </c:when>
                    <c:otherwise>
                        <input class="mdl-textfield__input" type="email" id="email" name="email" value="${session.mail}" required>
                    </c:otherwise>
                </c:choose>
                <label class="mdl-textfield__label" for="email">Rechercher via l'email</label>
            </div>
        </form><br>

        <c:choose>
            <c:when test="${data==null}">
                <h3>Aucun event à afficher.</h3>
            </c:when>
            <c:otherwise>
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Nom de l'event</th>
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
                                        <li class="mdl-menu__item" onclick="location.href = 'join-event?id=${item.id}' ; ">
                                            Participer a l'event
                                        </li>
                                        <li class="mdl-menu__item" onclick="location.href = 'detail-event?id=${item.id}' ; ">
                                            Plus de details
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="mdl-cell mdl-cell--3-col"></div>
</div>
</html>