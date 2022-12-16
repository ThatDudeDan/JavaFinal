<%-- 
    Document   : DisplayQuizzes
    Created on : Dec. 14, 2022, 7:26:17 p.m.
    Author     : cliff
--%>

<%@page import="entity.Quiz"%>
<%@page import="entity.QuizResult"%>
<%@page import="java.util.List"%>
<%@page import="entity.QuizAppUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/logout.js"></script>
        <link rel="stylesheet" href="style/style.css">
        
    </head>
    <body>
        <button id="logoutBtn">Logout</button>
        <a href="mainMenu.jsp">Main Menu</a>
        <% QuizAppUser username = (QuizAppUser) session.getAttribute("activeUser");%>
        <span><%=username.getUsername()%></span>
        <% List<Quiz> quizzes = (List<Quiz>) session.getAttribute("allQuizzes"); %>
        <h1>View quiz results</h1>
        <table id="resultsTable">
            <div><input type="text" id="searchInput"></div>
            <select name="search" id="searchChoice">
                <option value="id">Quiz ID</option>
                <option value="text">Text</option>
            </select>
            <button id="searchBtn">Search</button><button id="resetBtn">Reset</button>
            <tr><th>QuizID</th><th>Quiz Title</th><th>Number of Questions</th><th>Points</th>
                        <%
                            for (Quiz m : quizzes) {
                            int total = 0;
                            for (int i = 0; i < m.getPoints().size(); i++) {
                                total += m.getPoints().get(i);
                            }
                        %>
                <tr>
                    <td><%= m.getQuizId() %></td>
                    <td><%= m.getQuizTitle() %></td>
                    <td><%= m.getQuestions().size() %></td>
                    <td><%= total %></td>
                    <td><button id="doQuiz">Do quiz</button></td>
                </tr>
               <% } %>
            </table>
            
            <script src="js/displayQuizStuff.js"></script>
    </body>
</html>
