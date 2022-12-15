package DB;


import DB.QuestionAccessor;
import DB.ConnectionManager;
import DB.ConnectString;
import entity.Question;
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
public class QuizAccessor {
    private static PreparedStatement getByIDStatement = null;
    private static Connection conn = null;
    private static PreparedStatement selectAllStatement = null;
    private static PreparedStatement deleteStatement = null;
    private static PreparedStatement insertStatement = null;
    private static PreparedStatement updateStatement = null;

    // constructor is private - no instantiation allowed
    private QuizAccessor() {
    }

    private static void init() throws SQLException {
        if (conn == null) {
            try {
                conn = ConnectionManager.getConnection(ConnectString.getConnectionString(), ConnectString.getUser(), ConnectString.getPassword());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuizAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            getByIDStatement = conn.prepareStatement("select quizquestion.quizID,quizTitle, quizquestion.questionID, questionText, points from quizquestion \n" +
"inner join quiz on quizquestion.quizID = quiz.quizID inner join\n" +
"question on quizquestion.questionID = question.questionID where quiz.quizID = :quizID\"");
            selectAllStatement = conn.prepareStatement("select * from Quiz");
            
        }
    }
//
       private static List<Quiz> getQuizzesByQuery(String selectString) {
        List<Quiz> result = new ArrayList<>();

        try {
            init();
            //$stmt = $this->conn->prepare($selectString);
            PreparedStatement prep = conn.prepareStatement(selectString);
            ResultSet rs=prep.executeQuery();
            while (rs.next()){
                String id = rs.getString("quizID");
                String title = rs.getString("quizTitle");
                List<Question> q = QuestionAccessor.getQuestionsByID(id);
                 List<Integer> points = QuizQuestionAccessor.getPoints(id);
                Quiz item = new Quiz(id, title, q, points);
                result.add(item);
            }
        }
        catch (Exception e) {
            e.getMessage();
        }

        return result;
    }

    public static List<Quiz> getAllQuizzes() {
       List<Quiz> result = new ArrayList<>();

        try {
            init();
            PreparedStatement prep = conn.prepareStatement("select * from quiz");
            ResultSet rs=prep.executeQuery();
            while (rs.next()){
                String id = rs.getString("quizID");
                String title = rs.getString("quizTitle");
                List<Question> q = QuestionAccessor.getQuestionsByID(id);
                List<Integer> points = QuizQuestionAccessor.getPoints(id);
                Quiz item = new Quiz(id, title, q, points);
                //System.out.println(item.getQuestions());
                result.add(item);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
} 
