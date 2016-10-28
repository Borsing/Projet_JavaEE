<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:choose>
    <c:when test="${page=='login'}">
        <c:import url="../app/login.jspf"/>
    </c:when>
    <c:when test="${page=='events'}">
        <c:import url="../app/events.jspf"/>
    </c:when>
    <c:when test="${page=='404'}">
        <c:import url="../app/404.jspf"/>
    </c:when>
    <c:when test="${page=='my-events'}">
        <c:import url="../app/my-events.jspf"/>
    </c:when>
</c:choose>
</html>