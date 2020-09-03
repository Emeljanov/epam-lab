<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 31.08.2019
  TimeCalendar: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="error">
    <c:out value="ERROR: ${error}"/>
    <%@include file="footer.jsp" %>
</div>
