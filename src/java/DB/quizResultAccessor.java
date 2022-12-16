/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package controller;
//
//
//import db.ConnectionManager;
//import entity.ConnectString;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import entity.SongItem;
//import java.util.logging.Level;
//import java.util.logging.Logger;
package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Quiz;
import entity.QuizResult;
import java.sql.Timestamp;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cliff
 */
public class quizResultAccessor {

    private static PreparedStatement getByIDStatement = null;
    private static Connection conn = null;
    private static PreparedStatement selectAllStatement = null;
    private static PreparedStatement getCountStatement = null;

    private static PreparedStatement deleteStatement = null;
    private static PreparedStatement insertStatement = null;
    private static PreparedStatement updateStatement = null;
    private static PreparedStatement insertStatementString = null;

    public quizResultAccessor() {
    }

    private static boolean init() {
        if (conn != null) {
            return true;
        }
        conn = ConnectionManager.getConnection();
        if (conn != null) {
            try {

                selectAllStatement = conn.prepareStatement("select * from quizresult");
                getByIDStatement = conn.prepareStatement("select * from quizResult where resultID = ?");
                getCountStatement = conn.prepareStatement("select count(distinct resultID) as count from quizResult");

                //1: ResultID
                //2: quizID
                //3: username
                //4: quizStartTime
                //5: quizEndTime
                //6: userAnswers
                //7 Score Num
                //8 score den
                insertStatement = conn.prepareStatement("insert INTO quizresult values (?,?,?,?,?,?,?,?)");
            } catch (SQLException ex) {
                System.out.print(ex.getMessage() + "Lith");
            }
            return true;

        }
        return false;
    }

    public ArrayList<QuizResult> getQuizResultsbyQuery() {
        ResultSet rs = null;
        ArrayList<QuizResult> allResults = new ArrayList<QuizResult>();
        if (!init()) {
            return allResults;
        }
        try {
            rs = selectAllStatement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("*************");
            System.out.println("*************");
            System.out.println("*****" + ex.getMessage() + "***");
            System.out.println("*************");
            System.out.println("*************");
        }
        try {
            while (rs.next()) {
                String resultID = rs.getString("resultID");
                String quizID = rs.getString("quizID");
                String username = rs.getString("username");
                Timestamp quizStartTime = rs.getTimestamp("quizStartTime");
                Timestamp quizEndTime = rs.getTimestamp("quizEndTime");
                String userAnswers = rs.getString("userAnswers");
                String[] answers = userAnswers.replace("|", "").split("");
                List<String> userAnswer = Arrays.asList(answers);
                int scoreNum = rs.getInt("scoreNumerator");
                int scoreDen = rs.getInt("scoreDenominator");
                QuizResult result = new QuizResult(resultID, quizID, username, userAnswer, quizStartTime, quizEndTime, scoreNum, scoreDen);
                allResults.add(result);
            }
        } catch (SQLException ex) {
            System.err.println("************************");
            System.err.println("** Error opening DB");
            System.err.println("** " + ex.getMessage());
            System.err.println("************************");
            return null;
        }
        return allResults;
    }

    public String getResultCount() {
        String str = "";
        int count = 0;
        try {
            ResultSet rs = getCountStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            System.out.print(count);
            str = "QR-" + String.valueOf(count + 1001);
            System.out.print(str);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "Dog");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Catter");
        }
        System.out.print(str + "normal");

        return str;
    }

    public boolean insertResult(QuizResult obj) {
        boolean success = false;
        System.out.print(obj.getQuiz() + "This fucks");
        if (!init()) {
            return false;
        }
        try {
            insertStatement.setString(1, getResultCount());
            System.out.print(getResultCount());
            insertStatement.setString(2, obj.getQuiz());
            System.out.print(obj.getQuiz() + "testlllll");
            insertStatement.setString(3, obj.getUser());
            insertStatement.setTimestamp(4, obj.getStartTime());
            insertStatement.setTimestamp(5, obj.getEndTime());
            insertStatement.setString(6, obj.getUserAnswersString());
            insertStatement.setInt(7, obj.getNum());
            insertStatement.setInt(8, obj.getDen());

            int bool = insertStatement.executeUpdate();
            success = (bool == 1);
        } catch (SQLException ex) {
            System.out.println("*************");
            System.out.println("*************");
            System.out.println("*****" + ex.getMessage() + obj.getId() + "***");
            System.out.println("*************");
            System.out.println("*************");
            success = false;
        }
        return success;
    }

    public QuizResult getQuizResultsByID(String myresultID) {
        init();
        QuizResult result = null;
        try {
            getByIDStatement.setString(1, myresultID);
            ResultSet rs = getByIDStatement.executeQuery();
            while (rs.next()) {
                String resultID = rs.getString("resultID");
                String quizID = rs.getString("quizID");
                String username = rs.getString("username");
                Timestamp quizStartTime = rs.getTimestamp("quizStartTime");
                Timestamp quizEndTime = rs.getTimestamp("quizEndTime");
                String userAnswers = rs.getString("userAnswers");
                String[] answers = userAnswers.replace("|", "").split("|");
                List<String> userAnswer = Arrays.asList(answers);
                int scoreNum = rs.getInt("scoreNumerator");
                int scoreDen = rs.getInt("scoreDenominator");
                result = new QuizResult(resultID, quizID, username, userAnswer, quizStartTime, quizEndTime, scoreNum, scoreDen);
            }
        } catch (SQLException ex) {
            System.out.println("----------");
            System.out.println("----------");
            System.out.println(ex.getMessage());
            System.out.println("----------");
            System.out.println("----------");
        }
        return result;
    }
}
