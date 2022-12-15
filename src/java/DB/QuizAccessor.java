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
            QuestionAccessor quizAccess = new QuestionAccessor();
            //$stmt = $this->conn->prepare($selectString);
            ResultSet rs=getByIDStatement.executeQuery();
            while (rs.next()){
                String id = rs.getString("quizID");
                String title = rs.getString("quizTitle");
                Quiz item = new Quiz(id, title);
                item.addQuestion(quizAccess.getQuestionsByID(id));
                result.add(item);
            }
        }
        catch (Exception e) {
            e.getMessage();
        }

        return result;
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> li= this.getQuizzesByQuery(selectAllStatement.toString());
        return li;
    }
} 
