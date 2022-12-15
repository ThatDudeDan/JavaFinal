package entity;

public class ConnectString {
    private int id;
    private String title;
    private String artist;
    private double price;
    private boolean issold;

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
