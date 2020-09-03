<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 30.08.2019
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c"%>
<html>
<head>
    <title>main</title>
</head>
<body>
<div class="main">
    <jsp:include page="header.jsp"></jsp:include>

    <%@include file="sections.jsp"%>

    <%@include file="tasks2.jsp"%>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
