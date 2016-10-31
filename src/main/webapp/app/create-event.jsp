<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!-- jspf showing how to create an event | Can only appear while connected. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    Date dNow = new Date();
    SimpleDateFormat ft_date =
            new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat ft_time =
            new SimpleDateFormat("hh:mm");
    String now_date = ft_date.format(dNow);
    String now_time = ft_time.format(dNow);
%>

<form action="create-event" method="post">

    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
            <h3>Créer un évènement</h3><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="name" name="1_name" required/>
                <label class="mdl-textfield__label" for="name">Nom de l'évènement</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="address" name="7_address" required/>
                <label class="mdl-textfield__label" for="address">Lieu</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="description" name="2_description" required/>
                <label class="mdl-textfield__label" for="description">Description</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="date" id="begin_date" name="3_begin_date" required value="<%=now_date%>" />
                <label class="mdl-textfield__label" for="begin_date">Date de début</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="time" id="begin_time" name="4_begin_time" required value="<%=now_time%>"/>
                <label class="mdl-textfield__label" for="begin_time">Heure de début</label>
            </div><br>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="date" id="end_date" name="5_end_date" required value="<%=now_date%>"/>
                <label class="mdl-textfield__label" for="end_date">Date de fin</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="time" id="end_time" name="6_end_time" required value="<%=now_time%>"/>
                <label class="mdl-textfield__label" for="end_time">Heure de fin</label>
            </div><br>

            <input type="hidden" name="8_organizer_id" value="${session.mail}"/>

            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect" type="submit"
                   value="Créer l'évènement" />

        </div>
    </div>

</form>

</html>