<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">EventManager </span>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" href="events">Evenements</a>
        </nav>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" href="my-events">Mes events</a>
        </nav>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" id="show-dialog-create-event">Creer un event</a>
        </nav>
        <div class="mdl-layout-spacer"></div>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" id="show-dialog-login">Se connecter</a>
            <a class="mdl-navigation__link" id="show-dialog-register">S'enregistrer</a>
        </nav>
    </div>
</header>

<jsp:include page="${pageContext.servletContext.contextPath}/app/create-event.jspf"/>
<jsp:include page="${pageContext.servletContext.contextPath}/app/login.jspf"/>
<jsp:include page="${pageContext.servletContext.contextPath}/app/register.jspf"/>
