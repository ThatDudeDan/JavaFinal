<%-- 
    Document   : newjsp
    Created on : Dec. 15, 2022, 3:49:21 p.m.
    Author     : cliff
--%>

<%@page import="entity.QuizAppUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/main.js"></script>

    </head>
    <body>
        <% QuizAppUser user = (QuizAppUser) session.getAttribute("activeUser");%>
        <h1><%=user.getUsername()%></h1>
        <h1>Choose a item:</h1>
        <button id="quizShow">Go To Quizzes</button><br>
        <button id="resultsShow">Go To Results</button><br>
    </body>
</html>
