package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection conn = null;


    public static Connection getConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

} // end class ConnectionManager
