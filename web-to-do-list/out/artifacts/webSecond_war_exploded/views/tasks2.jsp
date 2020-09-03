<%--
  Created by IntelliJ IDEA.
  User: Anton Emeljanov
  Date: 12.09.2019
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="tasks2">
          <c:choose>
           <c:when test="${taskList != null}">
               <c:if test="${section == 'TODAY'}">
                   <c:out value="${todayDay}"/>
               <form id="tList">
               <table bordercolor="black" border="2">
                   <tr>
                       <td></td>
                       <td>task</td>
                       <td>Date</td>
                       <td>File</td>
                   </tr>
               <c:forEach items="${taskList}" var="task" >
                        <tr>
                            <td><input type="checkbox"  name="idtask" value="${task.getId()}"> </td>
                            <td>${task.getName()}<input type="hidden" name="taskname" value="${task.getName()}"></td>
                            <td>${task.getDate()}<input type="hidden" name="taskdate" value="${task.getDate()}"></td>
                            <td><button type="submit" name="idtask" value="${task.getId()}" formmethod="get" formaction="<c:url value="/app/operation/file"/> "> File </button></td>
                        </tr>
                    </c:forEach>
               </table>
               </form>
                   <button type="submit" form="tList" formaction="/app/operation/remove" formmethod="post"> Remove Task</button>
                   <button type="submit" form="tList" formaction="/app/operation/addtask"  formmethod="get"> Add Task</button>
                   <button type="submit" form="tList" formaction="/app/operation/fixtask"  formmethod="post"> Fix Task</button>
               </c:if>
               <c:if test="${section == 'TOMORROW'}">
                   <c:out value="${tomorrowDay}"/>
                   <form id="tList">
                       <table bordercolor="black" border="2">
                           <tr>
                               <td></td>
                               <td>task</td>
                               <td>Date</td>
                               <td>File</td>
                           </tr>
                           <c:forEach items="${taskList}" var="task" >
                               <tr>
                                   <td><input type="checkbox" name="idtask" value="${task.getId()}"> </td>
                                   <td>${task.getName()}</td>
                                   <td>${task.getDate()}</td>
                                   <td><button type="submit" name="idtask" value="${task.getId()}" formmethod="get" formaction="<c:url value="/app/operation/file"/> "> File </button></td>
                               </tr>
                           </c:forEach>
                       </table>
                   </form>
                   <button type="submit" form="tList" formaction="/app/operation/remove" formmethod="post"> Remove Task</button>
                   <button type="submit" form="tList" formaction="/app/operation/addtask"  formmethod="get"> Add Task</button>
                   <button type="submit" form="tList" formaction="/app/operation/fixtask"  formmethod="post"> Fix Task</button>
               </c:if>
               <c:if test="${section == 'FIXED'}">
                   <c:out value="fixed"/>
                   <form id="tList">
                       <table bordercolor="black" border="2">
                           <tr>
                               <td></td>
                               <td>task</td>
                               <td>Date</td>
                               <td>File</td>
                           </tr>
                           <c:forEach items="${taskList}" var="task" >
                               <tr>
                                   <td><input type="checkbox" name="idtask" value="${task.getId()}"> </td>
                                   <td>${task.getName()}</td>
                                   <td>${task.getDate()}</td>
                                   <td><button type="submit" name="idtask" value="${task.getId()}" formmethod="get" formaction="<c:url value="/app/operation/file"/> "> File </button></td>
                               </tr>
                           </c:forEach>
                       </table>
                   </form>
                   <button type="submit" form="tList" formaction="/app/operation/remove" formmethod="post"> Remove Task</button>
               </c:if>
               <c:if test="${section == 'SOMEDAY'}">
                   <c:out value="someday"/>
                   <form id="tList">
                       <table bordercolor="black" border="2">
                           <tr>
                               <td></td>
                               <td>task</td>
                               <td>Date</td>
                               <td>File</td>
                           </tr>
                           <c:forEach items="${taskList}" var="task" >
                               <tr>
                                   <td><input type="checkbox" name="idtask" value="${task.getId()}"> </td>
                                   <td>${task.getName()}</td>
                                   <td>${task.getDate()}</td>
                                   <td><button type="submit" name="idtask" value="${task.getId()}" formmethod="get" formaction="<c:url value="/app/operation/file"/> "> File </button></td>
                               </tr>
                           </c:forEach>
                       </table>
                   </form>
                   <button type="submit" form="tList" formaction="/app/operation/remove" formmethod="post"> Remove Task</button>
                   <button type="submit" form="tList" formaction="/app/operation/addtask"  formmethod="get"> Add Task</button>
                   <button type="submit" form="tList" formaction="/app/operation/fixtask"  formmethod="post"> Fix Task</button>
               </c:if>
               <c:if test="${section == 'RECYCLE'}">
                   <c:out value="recycle bin"/>
                   <form id="tList">
                       <table bordercolor="black" border="2">
                           <tr>
                               <td></td>
                               <td>task</td>
                               <td>Date</td>
                               <td>File</td>
                           </tr>
                           <c:forEach items="${taskList}" var="task" >
                               <tr>
                                   <td><input type="checkbox" name="idtask" value="${task.getId()}"> </td>
                                   <td>${task.getName()}</td>
                                   <td>${task.getDate()}</td>
                                   <td><button type="submit" name="idtask" value="${task.getId()}" formmethod="get" formaction="<c:url value="/app/operation/file"/> "> File </button></td>
                               </tr>
                           </c:forEach>
                       </table>
                   </form>
                   <button type="submit" form="tList" formaction="<c:url value="/app/operation/resumetask"/>" formmethod="post"> Resume Task</button>
                   <button type="submit" form="tList" formaction="<c:url value="/app/operation/recycletask"/>"  formmethod="post"> Recycle</button>
                   <button type="submit" form="tList" formaction="<c:url value="/app/operation/recyclealltask"/>"  formmethod="post"> Recycle all</button>
               </c:if>

           </c:when>
           <c:otherwise>
               <form name = "autotoday" action="<c:url value="/app/operation/main"/> " method="get">
               </form>
                <script type="text/javascript">
                   window.onload = function() {
                       document.forms["autotoday"].submit();
                   }
               </script>
           </c:otherwise>
       </c:choose>
</div>
