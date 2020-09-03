<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 18.09.2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<button onclick="location.href='/app/operation/main'"> Back </button>
<tr>
    <td><c:out value="old task disc:[ ${taskById.getName()} ]"/></td>
    <td><c:out value="old task date:[ ${taskById.getDate()} ]"/>
    </td>
</tr>
<form method="post" action="<c:url value="/app/operation/fixtask"/>">             <%--check if it works--%>
    <label>Task:
        <input type="text"  name="discription"><br />
    </label>
    <label>Date:
        <input type="date" name="taskAddDate">
    </label>
    <button type="submit">Submit</button>
</form>
<%@include file="footer.jsp"%>
</body>
</html>
