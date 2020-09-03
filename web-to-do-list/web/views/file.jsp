<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 26.09.2019
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<jsp:include page="header.jsp"></jsp:include>
<button onclick="location.href='/app/operation/main'"> Back </button>
<br/>
<c:choose>
    <c:when test="${not empty fileName}">
        <c:out value="File name: ${fileName}"/>
        <form action="/app/operation/file/deletefile" method="post">
            <button type="submit"> Delete </button>
        </form>
        <form action="/app/operation/file/downloadfile" method="get">
            <button type="submit"> Download </button>
        </form>
    </c:when>
    <c:otherwise>
        <c:out value="You can upload file to task:"/>
        <form action="<c:url value="/app/operation/file/upload"/>" method="post" enctype="multipart/form-data">
            <input type="file" name="uploadFile">
            <button type="submit"> Upload </button>
        </form>
    </c:otherwise>
</c:choose>
<%@include file="footer.jsp" %>
</body>
</html>

