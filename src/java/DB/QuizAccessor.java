package controller;


import db.ConnectionManager;
import entity.ConnectString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Quiz;
public class SongItemAccessor {
    private static PreparedStatement getByIDStatement = null;
    private static Connection conn = null;
    private static PreparedStatement selectAllStatement = null;
    private static PreparedStatement deleteStatement = null;
    private static PreparedStatement insertStatement = null;
    private static PreparedStatement updateStatement = null;

    // constructor is private - no instantiation allowed
    private SongItemAccessor() {
    }

    private static void init() throws SQLException {
        if (conn == null) {
            try {
                conn = ConnectionManager.getConnection(ConnectString.getConnectionString(), ConnectString.getUser(), ConnectString.getPassword());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SongItemAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            getByIDStatement = conn.prepareStatement("select quizquestion.quizID,quizTitle, quizquestion.questionID, questionText, points from quizquestion \n" +
"inner join quiz on quizquestion.quizID = quiz.quizID inner join\n" +
"question on quizquestion.questionID = question.questionID where quiz.quizID = :quizID\"");
            selectAllStatement = conn.prepareStatement("select * from songitems");
            deleteStatement = conn.prepareStatement("delete from songitems where songID = ?");
            insertStatement = conn.prepareStatement("insert into songitems values (?,?,?,?,?)");
            updateStatement = conn.prepareStatement("update songitems set title = ?, Artist = ?, Price = ?, isSold = ? where songID = ?");
        }
    }
//
       private static List<SongItem> getQuizzesByQuery(String selectString) {
        $result = [];

        try {
            $quizAccess = new questionAccessor();
            //$stmt = $this->conn->prepare($selectString);
            $stmt = $this->conn->prepare($selectString);
            $stmt->execute();
            $dbresults = $stmt->fetchAll(PDO::FETCH_ASSOC);
            foreach ($dbresults as $r) {
                $quizID = $r['quizID'];
                $quizTitle = $r['quizTitle'];
                $quizQuestions = $quizAccess->getQuestionsfromQuiz($quizID);
                $quizPoints = $this->getQuizPoints($quizID);
                $obj = new Quiz($quizID, $quizTitle, $quizQuestions, $quizPoints);
                array_push($result, $obj);
            }
        }
        catch (Exception $e) {
            $result = [];
        }
        finally {
            if (!is_null($stmt)) {
                $stmt->closeCursor();
            }
        }

        return $result;
    }

    public function getAllQuizzes() {
        return $this->getQuizzesByQuery("select * from Quiz");
    }
    //

    public static List<SongItem> getAllSongItems() {
        List<SongItem> songs = new ArrayList();
        try {
            init();
            ResultSet rs = selectAllStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("songID");
                String title = rs.getString("title");
                String artist = rs.getString("Artist");
                int price = rs.getInt("price");
                boolean isSold = rs.getBoolean("isSold");
                SongItem item = new SongItem(id, title, artist, price, isSold);
                songs.add(item);
            }
        } catch (SQLException ex) {
            songs = new ArrayList();
            songs.add(new SongItem(1,ex.getMessage(),"dummy",12,false));
        }
        return songs;
    }

    public static boolean deleteItem(SongItem item) {
        boolean res;

        try {
            init();
            deleteStatement.setInt(1, item.getId());
            int rowCount = deleteStatement.executeUpdate();
            res = (rowCount == 1);
        } catch (SQLException ex) {
            res = false;
        }
        return res;
    }


    public static boolean deleteItemById(int id) {
        SongItem dummy = new SongItem(id, "title", "artist", 0, false);
        return deleteItem(dummy);
    }
    
    public static boolean insertItem(SongItem item) {
        boolean res;
        
        try {
            init();
            insertStatement.setInt(1, item.getId());
            insertStatement.setString(2, item.getTitle());
            insertStatement.setString(3, item.getArtist());
            insertStatement.setInt(4, item.getPrice());
            insertStatement.setBoolean(5, item.issold());
            int rowCount = insertStatement.executeUpdate();
            res = (rowCount == 1);
        }
        catch (SQLException ex) {
            res = false;
        }
        
        return res;
    }

    public static boolean updateItem(SongItem item) {
        boolean res;
        
        try {
            init();
            System.out.print(item.getArtist());
            System.out.print(item.getTitle());
            System.out.print(item.getPrice());

            updateStatement.setString(1, item.getTitle());
            updateStatement.setString(2, item.getArtist());
            updateStatement.setInt(3, item.getPrice());
            updateStatement.setBoolean(4, item.issold());
            updateStatement.setInt(5, item.getId());
            int rowCount = updateStatement.executeUpdate();
            res = (rowCount == 1);
        }
        catch (SQLException ex) {
            System.out.print(ex.getMessage());
            res = false;
        }
        
        return res;
    }

} 
