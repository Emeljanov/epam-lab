<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 29.08.2019
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Log into account.
<button onclick="location.href='..'"> Back </button>
<form method="post" action="<c:url value="/app/login"></c:url> ">
    <label>Name:
        <input type="text" name="login"><br />
    </label>
    <label>Password:
        <input type="password" name="password"><br />
    </label>
    <button type="submit">Submit</button>
</form>
<%@include file="footer.jsp"%>
</body>
</html>
