<%-- 
    Document   : DisplayResults
    Created on : Dec. 15, 2022, 2:58:05 p.m.
    Author     : cliff
--%>
<%@page import="entity.QuizAppUser"%>
<%@page import="java.util.List"%>
<%@page import="entity.QuizResult"%>
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
        <% QuizAppUser username = (QuizAppUser) session.getAttribute("activeUser");%>
        <span><%=username.getUsername()%></span>
        <% List<QuizResult> quizResults = (List<QuizResult>) session.getAttribute("allQuizResults"); %>
        <h1>View quiz results</h1>
        <table id="resultsTable">
                <tr><th>ResultID</th><th>QuizID</th><th>User</th><th>StartTime</th><th>Time Taken</th><th>UserAnswer</th><th>Score</th></tr>
                        <%
                            for (QuizResult m : quizResults) {
                        %>
                <tr>
                    <td><%= m.getId() %></td>
                    <td><%= m.getQuiz() %></td>
                    <td><%= m.getUser() %></td>
                    <td><%= m.getStartTime() %></td>
                    <td><%= m.calculateTimeTaken() + " seconds"%></td>
                    <td><%= m.getUserAnswers() %></td>
                    <td><%= Math.round(m.calculateScore()) + "%"%></td>
                    <td><button id="displayResult">Check Result</button></td>
                </tr>
                <% } %>
            </table>
            <script src="js/resultsTable.js"></script>
    </body>
</html>
