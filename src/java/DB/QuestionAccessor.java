package DB;


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
import java.util.Date;
import DB.ConnectString;
import DB.ConnectionManager;
public class QuestionAccessor {
    private static PreparedStatement getByIDStatement = null;
    private static Connection conn = null;
    private static PreparedStatement selectAllStatement = null;
    private static PreparedStatement deleteStatement = null;
    private static PreparedStatement insertStatement = null;
    private static PreparedStatement updateStatement = null;
    private static PreparedStatement getQuestionsByIdString;

    // constructor is private - no instantiation allowed
    public QuestionAccessor() {
    }

    private static void init() throws SQLException {
        if (conn == null) {
            try {
              conn = ConnectionManager.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(QuestionAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            getByIDStatement = conn.prepareStatement("select quizquestion.quizID, quiz.quizTitle, quizquestion.questionID, question.questionText, quizquestion.points, question.choices, question.answer\n" +
"from question join quizquestion on question.questionID = quizquestion.questionID\n" +
"join quiz on quizquestion.quizID = quiz.quizID\n" +
"where quiz.quizID = ?");
            selectAllStatement = conn.prepareStatement("select * from songitems");
            deleteStatement = conn.prepareStatement("delete from songitems where songID = ?");
            insertStatement = conn.prepareStatement("insert into songitems values (?,?,?,?,?)");
            updateStatement = conn.prepareStatement("update songitems set title = ?, Artist = ?, Price = ?, isSold = ? where songID = ?");
        }
    }
//
       private static List<Question> getQuestionsByQuery(String selectString) {
        List<Question> result = new ArrayList<>();

        try {
            init();
            PreparedStatement stmt = conn.prepareStatement(selectString);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String id = results.getString("questionID");
                String text = results.getString("questionText");
                String choices = results.getString("choices");
                int answer = results.getInt("answer");
                result.add(new Question(id, text, choices, answer));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            result = new ArrayList<>();
        }
        
        return result;
    }

    public List<Question> getAllQuestion() {
        return getQuestionsByQuery("Select * from question");
    }
    public static List<Question> getQuestionsByID(String QuizID)
    {
   List<Question> result = new ArrayList<>();

        try {
            init();
            getByIDStatement.setString(1, QuizID);
            ResultSet results = getByIDStatement.executeQuery();
            while(results.next()){
                String id = results.getString("questionID");
                String text = results.getString("questionText");
                String choices = results.getString("choices");
                int answer = results.getInt("answer");
                result.add(new Question(id, text, choices, answer));
            }
            System.out.println(result.get(0).getQId());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            result = new ArrayList<>();
        }
        
        return result;
    }
}
