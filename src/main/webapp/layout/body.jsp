<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:choose>
    <c:when test="${page=='events'}">
        <c:import url="${pageContext.servletContext.contextPath}/app/events.jsp"/>
    </c:when>
    <c:when test="${page=='404'}">
        <c:import url="${pageContext.servletContext.contextPath}/app/404.jsp"/>
    </c:when>
    <c:when test="${page=='my-events'}">
        <c:import url="${pageContext.servletContext.contextPath}/app/my-events.jsp"/>
    </c:when>
    <c:when test="${page=='login'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/login.jsp"/>
    </c:when>
    <c:when test="${page=='register'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/register.jsp"/>
    </c:when>
    <c:when test="${page=='detail-event'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/detail-event.jsp"/>
    </c:when>
    <c:when test="${page=='join-event'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/join-event.jsp"/>
    </c:when>
    <c:when test="${page=='create-event'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/create-event.jsp"/>
    </c:when>
    <c:when test="${page=='edit-event'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/edit-event.jsp"/>
    </c:when>
    <c:when test="${page=='update-profil'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/update-profil.jsp"/>
    </c:when>
    <c:when test="${page=='unauthorized'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/unauthorized.jsp"/>
    </c:when>
    <c:when test="${page=='my-created-events'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/app/my-created-events.jsp"/>
    </c:when>
</c:choose>

<c:if test="${exception != null}">
    <button id="demo-show-toast" class="mdl-button mdl-js-button mdl-button--raised" type="button" hidden>Show Toast</button>
    <div id="demo-toast-example" class="mdl-js-snackbar mdl-snackbar">
        <div class="mdl-snackbar__text"></div>
    </div>
    <script>
        (function() {
            'use strict';
            var snackbarContainer = document.querySelector('#demo-toast-example');
            var showToastButton = document.querySelector('#demo-show-toast');
            showToastButton.addEventListener('click', function() {
                'use strict';
                var data = {message: ${exception.name}};
                snackbarContainer.MaterialSnackbar.showSnackbar(data);
            });
            showToastButton.click();
        }());
    </script>
</c:if>

</html>