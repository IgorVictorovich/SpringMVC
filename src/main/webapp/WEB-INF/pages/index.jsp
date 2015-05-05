<%@ page contentType="text/html;charset=UTF-8" language="java" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
       <html>
       <body>
       	<h1>${message}</h1>

       <table style="border: 1px solid; width: 100px; text-align:center">
        <thead style="background:#808583">
         <tr>
          <th>Participants</th>
          <th colspan="1"></th>
         </tr>
        </thead>
        <tbody>
        <c:forEach items="${initList}" var="coffeeLoversList">
         <tr>
          <td><c:out value="${coffeeLoversList}" /></td>
         </tr>
        </c:forEach>
        </tbody>
       </table>
        <br><br>
       Timestamp: ${timestamp}<br>

      <c:choose>
      <c:when test="${counter<='1'}">
      <br/>
      </c:when>
      <c:otherwise>You have randomized this list ${counter} times.
      <br/>
      </c:otherwise>
        </c:choose>

       <br/>
        <form action="coffee" method="post">
         <input class="button" type="submit" value="Again!"/>
         </form>
        <form action="send_result" method="post">
         <input class="button" type="submit" value="Send result via e-mail"/>
         </form>
       <br>

       <div align="right">Version: ${version}</div>
       </body>
       </html>