<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="modules.event.EventEntity" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jspf template to display a list of events -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    ServletContext sc = request.getServletContext();
    EventEntity event_entity = (EventEntity) sc.getAttribute("data");

    Date begin_date_value = event_entity.getBegin_date();
    Date end_date_value = event_entity.getEnd_date();

    SimpleDateFormat ft_date =
            new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat ft_time =
            new SimpleDateFormat("hh:mm");

    String begin_date_date = ft_date.format(begin_date_value);
    String begin_date_time = ft_time.format(begin_date_value);

    String end_date_date = ft_date.format(end_date_value);
    String end_date_time = ft_time.format(end_date_value);
%>

<form action="edit-event" method="post">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--5-col  mdl-cell--1-offset">
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
                <input class="mdl-textfield__input" type="date" id="begin_date" name="begin_date" required value="<%=begin_date_date%>">
                <label class="mdl-textfield__label" for="begin_date">Date de début</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="time" id="begin_time" name="begin_time" required value="<%=begin_date_time%>">
                <label class="mdl-textfield__label" for="begin_date">Date de début</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="date" id="end_date" name="end_date" required value="<%=end_date_date%>"/>
                <label class="mdl-textfield__label" for="end_date">Date de fin</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="time" id="end_time" name="end_time" required value="<%=end_date_time%>"/>
                <label class="mdl-textfield__label" for="end_date">Date de fin</label>
            </div><br>

            <input type="hidden" name="id" value="${data.id}"/>
            <input type="hidden" name="organizer_id" value="${session.mail}"/>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Editer l'évènement" /><br>
        </div>

    </div>
</form>

<div class="mdl-cell mdl-cell--4-col mdl-cell--1-offset">
    <h3>Participants</h3><br>

    <c:choose>
        <c:when test="${empty data.participants}">
            <h5 style="color: darkred">Aucun participant pour le moment.</h5>
        </c:when>
        <c:otherwise>
            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                <thead>
                <tr>
                    <th></th>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Mail</th>
                    <th>Société</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${data.participants}" var="item">
                    <tr>
                        <td><i class="material-icons" >person</i></td>
                        <td class="mdl-data-table__cell--non-numeric">${item.first_name}</td>
                        <td class="mdl-data-table__cell--non-numeric">${item.last_name}</td>
                        <td class="mdl-data-table__cell--non-numeric">${item.mail}</td>
                        <td class="mdl-data-table__cell--non-numeric">${item.company}</td>
                        <td>
                            <form action="remove-participant" method="post">
                                <input type="hidden" id="event_id" name="1_event_id" value="${data.id}"/>
                                <input type="hidden" id="email" name="2_email" value="${item.mail}"/>

                                <button class="mdl-button mdl-js-button mdl-button--icon"  type="submit">
                                    <i class="material-icons">delete</i>
                                </button>
                            </form>


                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>

<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--5-col mdl-cell--1-offset">
        <h3>Suppression de l'évènement</h3><br>
        <form action="delete-event" method="post">
            <input type="hidden" id="id" name="id" value="${data.id}"/>
            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Supprimer l'évènement" />
        </form>
    </div>
</div>

</html>

