<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:choose>
    <c:when test="${page =='login'}">
        <c:import url="../app/login.jspf"></c:import>
    </c:when>
    <c:when test="${page =='create-event'}">
        <c:import url="../app/create-event.jspf"></c:import>
    </c:when>

</c:choose>

</body>
</html>
