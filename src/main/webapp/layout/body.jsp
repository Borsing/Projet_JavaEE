<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adric
  Date: 17/10/2016
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:choose>
    <c:when test="${page=='login'}">
        <c:import url="../app/login.jspf" />
    </c:when>

</c:choose>
</html>

