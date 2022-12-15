/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;
import DB.QuestionAccessor;
import DB.ConnectionManager;
import DB.ConnectString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Quiz;
import entity.QuizQuestion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rfcla
 */
public class QuizQuestionAccessor {
    private static PreparedStatement getByIDStatement = null;
    private static Connection conn = null;
    private static PreparedStatement selectAllStatement = null;
    private static PreparedStatement deleteStatement = null;
    private static PreparedStatement insertStatement = null;
    private static PreparedStatement updateStatement = null;

    public QuizQuestionAccessor(){
        
    }
    private static void init() throws SQLException {
        if (conn == null) {
            try {
                conn = ConnectionManager.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(QuizAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public static List<QuizQuestion> getAllQuizQuestions()
    {
        List<QuizQuestion> quiz = new ArrayList<QuizQuestion>();
        try{
            init();
           PreparedStatement getq = conn.prepareStatement("select * from quizquestion");
           ResultSet rs= getq.executeQuery();
           while(rs.next()){
               String quizid = rs.getString("quizID");
               String questionid= rs.getString("questionID");
               int points = rs.getInt("points");
               QuizQuestion obj = new QuizQuestion(quizid, questionid, points);
               quiz.add(obj);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            quiz = new ArrayList<>();
        }
     return quiz;   
    }
    public static List<QuizQuestion> getQuestionByID(String QuizID){
        List<QuizQuestion> quiz = new ArrayList<>();
        try{
            init();
           PreparedStatement getq = conn.prepareStatement("select * from quizquestion where quizID = ?");
           getq.setString(1, QuizID);
           ResultSet rs= getq.executeQuery();
           while(rs.next()){
               String quizid = rs.getString("quizID");
               String questionid= rs.getString("questionID");
               int points = rs.getInt("points");
               QuizQuestion obj = new QuizQuestion(quizid, questionid, points);
               quiz.add(obj);
               System.out.println(obj.getQuestionId());
           }
           
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            quiz = new ArrayList<>();
        }
     return quiz; 
    }
    public static List<Integer> getPoints(String QuizID){
        List<Integer> points = new ArrayList<>();
        try
        {
            init();
           PreparedStatement getq = conn.prepareStatement("select points from quizquestion where quizID = ?");
           getq.setString(1, QuizID);
           ResultSet rs= getq.executeQuery();
           while(rs.next()){
               int point = rs.getInt("points");
               points.add(point);
           }
           
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            points = new ArrayList<>();
        }
     return points; 
    }
}
