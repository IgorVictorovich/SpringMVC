<%@ page contentType="text/html;charset=UTF-8" language="java" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
       <html>
       <body>
       Generated list was successfully send.
       <br/>
       <table style="border: 1px solid; width: 100px; text-align:center">
               <thead style="background:#808583">
                <tr>
                 <th>Participants</th>
                 <th colspan="1"></th>
                </tr>
               </thead>
               <tbody>
               <c:forEach items="${shuffledList}" var="coffeeLoversList">
                <tr>
                 <td><c:out value="${coffeeLoversList}" /></td>
                </tr>
               </c:forEach>
               </tbody>
              </table>
       <br/>

       </body>
       </html>