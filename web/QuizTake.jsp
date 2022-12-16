<%-- 
    Document   : QuizTake
    Created on : Dec. 15, 2022, 9:33:39 p.m.
    Author     : cliff
--%>

<%@page import="entity.QuizAppUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <link rel="stylesheet" href="style/style.css">
    </head>
    <body>
        <% QuizAppUser username = (QuizAppUser) session.getAttribute("activeUser");%>
        <span id="User"><%=username.getUsername()%></span>
        <div id="quizContent"></div>
        <button id="prevBtn">Previous</button>
        <button id="nextBtn">Next</button>
        <button id="QuizSubmit">Submit</button>
        <script src="js/TakeQuiz.js"></script>
        

    </body>
</html>
