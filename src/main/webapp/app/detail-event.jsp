<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="modules.event.EventEntity" %>
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

<div class="mdl-grid">
    <div class="mdl-cell mdl-cell--6-col  mdl-cell--3-offset">
        <h3>Détails de l'évènement</h3><br>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="name" required value="${data.name}" readonly/>
            <label class="mdl-textfield__label" for="name">Nom de l'évènement</label>
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
            <input class="mdl-textfield__input" type="date" id="begin_date" required readonly value="<%=begin_date_date%>"/>
            <label class="mdl-textfield__label" for="begin_date">Date de début</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="time" id="begin_time" required readonly value="<%=begin_date_time%>"/>
            <label class="mdl-textfield__label" for="begin_date">Date de début</label>
        </div><br>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="date" id="end_date" required readonly value="<%=end_date_date%>"/>
            <label class="mdl-textfield__label" for="end_date">Date de fin</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <input class="mdl-textfield__input" type="time" id="end_time" required readonly value="<%=end_date_time%>"/>
        <label class="mdl-textfield__label" for="end_date">Date de fin</label>
    </div><br>

        <input type="hidden" name="mydata" id="id" value="${id}"/>

        <input type="hidden" name="mydata" id="organizer_id" value="${session.mail}"/>

        <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"
               value="Participer à l'évènement" onclick="location.href='join-event?1_id=${data.id}';"/>
    </div>
</div>
</html>
