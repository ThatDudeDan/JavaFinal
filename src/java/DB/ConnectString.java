package DB;

public class ConnectString {

public static String getConnectionString()
{
        return "jdbc:mysql://localhost:3306/quizappdb?zeroDateTimeBehavior=CONVERT_TO_NULL&autoReconnect=true&useSSL=false";
}
public static String getUser()
    {
        return "app";
    }
public static String getPassword()
    {
        return "app";
    }
    
} // end class MenuItem
