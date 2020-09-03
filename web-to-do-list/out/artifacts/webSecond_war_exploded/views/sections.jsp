<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 09.09.2019
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sections">
    <form action="/app/operation/main" method="get">
        <button type="submit" name="section" value="TODAY">TODAY</button>
        <button type="submit" name="section" value="TOMORROW">TOMORROW</button>
        <button type="submit" name="section" value="SOMEDAY">SOMEDAY</button>
        <button type="submit" name="section" value="FIXED">FIXED</button>
        <button type="submit" name="section" value="RECYCLE">RECYCLE BIN</button>
    </form>
</div>
