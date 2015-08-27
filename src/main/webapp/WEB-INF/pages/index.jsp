<%@ page contentType="text/html;charset=UTF-8" language="java" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
       <html xmlns="http://www.w3.org/1999/xhtml"
                   xmlns:th="http://www.thymeleaf.org">
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
       <br/>
       Generated with <i>${generatedMethod}</i> method
       <br><br>
       Timestamp: <fmt:formatDate value="${timestamp}" pattern="dd.MM.yyyy HH:mm:ss" /><br>
        <c:choose>
            <c:when test="${empty headOfList || headOfList=='empty'}">Statistics is empty. Generate and save some list.<br/></c:when>
            <c:otherwise>The participant, who often occupied the first place is ${headOfList}<br/></c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${counter<='1'}"><br/></c:when>
            <c:otherwise>You have randomized this list ${counter} times.<br/></c:otherwise>
        </c:choose>

       <br/>
        <form action="coffee" method="post">
         <input class="button" name="generate" type="submit" value="Again!"/>
         </form>
        <form action="send_result" method="post">
         <input class="button" disabled="disabled" name="send" type="submit" value="Send result via e-mail"/>
         </form>
        <form action="persist_data" method="post">
         <input class="button" name="persist" type="submit" value="Save result"/>
         </form>
       <br>

       <div align="right">Version: ${version}</div>
       <div align="right">
       <c:if test="${!empty statusString}">Status: ${statusString}<br/> </c:if></div>

<%--Powered by--%>
<div align="right"><a
                   href="http://glassfish.org" title="Runs on GlassFish!">
                   <img src="SpringMVC/../resources/images/runs_on_glassfish_button.gif"
                   alt="Runs on GlassFish!" width="120" height="50" /></a></div>

</body>
</html>