<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 17.09.2019
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Task</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<button onclick="location.href='/app/operation/main'"> Back</button>

<form method="post" action="/app/operation/addtask">
    <label>Task:
        <input type="text" name="discription"><br/>
    </label>
    <label>Date:
        <input type="date" name="taskAddDate">
    </label>
    <button type="submit">Submit</button>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
