<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 29.08.2019
  TimeCalendar: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registrate</title>
</head>
<body>
Register form.
<button onclick="location.href='..'"> Back </button>
<form method="post">
    <label>Name:
        <input type="text" name="login"><br />
    </label>
    <label>Password:
        <input type="password" name="password"><br />
    </label>
    <button type="submit">Submit</button>
</form>
</body>
</html>
