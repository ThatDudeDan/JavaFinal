package DB;

public class ConnectString {

public static String getConnectionString()
{
        return "jdbc:mysql://localhost:3306/quizappdb?zeroDateTimeBehavior=CONVERT_TO_NULL&autoReconnect=true&useSSL=false";
}
public static String getUser()
    {
        return "root";
    }
public static String getPassword()
    {
        return "mysql";
    }
    
} // end class MenuItem
