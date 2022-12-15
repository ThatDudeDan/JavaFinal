package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import DB.ConnectString;

public class ConnectionManager {

    private static Connection conn = null;


    public static Connection getConnection() {
        if (conn == null) {
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(ConnectString.getConnectionString(), ConnectString.getUser(), ConnectString.getPassword());
            }
            catch(SQLException sql){
                System.err.println("************************");
                System.err.println("** Error opening DB");
                System.err.println("** " + sql.getMessage());
                System.err.println("************************");
                return null;
            }
            catch (ClassNotFoundException ce)
            {
                System.err.println("************************");
                System.err.println("**Failing to load DB Driver");
                System.err.println("** " + ce.getMessage());
                System.err.println("************************");
                return null;
            }
            }
        return conn;
    }

} // end class ConnectionManager
