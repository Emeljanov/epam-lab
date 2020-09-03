<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 08.09.2019
  TimeCalendar: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
<c:choose>
    <c:when test="${not empty user.login}">
        <c:out value="User:${user.getLogin()}"/>
        <button onclick="location.href='/app/logout'"> Logout </button>
    </c:when>
    <c:otherwise>
       <c:out value="User:guest"/>
        <button onclick="location.href='/app/login'"> Login </button>
        <button onclick="location.href='/app/registrate'" > Register</button>
    </c:otherwise>
</c:choose>
</div>
