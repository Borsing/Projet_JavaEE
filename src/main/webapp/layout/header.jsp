<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">EventManager </span>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" href="events">
                <i class="material-icons" >event</i>
                Evenements
            </a>
        </nav>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" href="my-events">
                <i class="material-icons" >notifications</i>
                Mes participations
            </a>
        </nav>

        <c:choose>
            <c:when test="${session==null}">
                <div class="mdl-layout-spacer"></div>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a class="mdl-navigation__link" href="login">
                        <i class="material-icons" >person</i>
                        Se connecter
                    </a>
                    <a class="mdl-navigation__link" href="register">
                        <i class="material-icons" >input</i>
                        S'enregistrer
                    </a>

                </nav>
            </c:when>
            <c:otherwise>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a class="mdl-navigation__link" href="my-created-events?email=${session.mail}">
                        <i class="material-icons" >archive</i>
                        Mes creations
                    </a>
                </nav>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a class="mdl-navigation__link" href="create-event">
                        <i class="material-icons" >add</i>
                        Creer un event
                    </a>
                </nav>
                <div class="mdl-layout-spacer"></div>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a class="mdl-navigation__link" href="update-profil?email=${session.mail}">
                        <i class="material-icons" >settings</i>
                        Mon compte
                    </a>
                </nav>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a class="mdl-navigation__link" href="logout">
                        <i class="material-icons" >input</i>
                        Se deconnecter
                    </a>
                </nav>
            </c:otherwise>
        </c:choose>
    </div>
</header>