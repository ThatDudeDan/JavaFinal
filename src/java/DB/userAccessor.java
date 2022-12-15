/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package controller;
//
//
//import db.ConnectionManager;
//import entity.ConnectString;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import entity.SongItem;
//import java.util.logging.Level;
//import java.util.logging.Logger;


package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.QuizAppUser;
import java.sql.Timestamp;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


/**
 *
 * @author cliff
 */
public class userAccessor {
    private static PreparedStatement getByIDStatement = null;
    private static Connection conn = null;
    private static PreparedStatement selectUserStatement = null;
    private static PreparedStatement deleteStatement = null;
    private static PreparedStatement insertStatement = null;
    private static PreparedStatement updateStatement = null; 
    
    public userAccessor() {
    }

    private static boolean init(){
        if (conn!= null)
            return true;
         conn = ConnectionManager.getConnection();
         if (conn != null)
         {
            try {
            
            selectUserStatement = conn.prepareStatement("select * from QuizAppUser where username = ?");
            
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
            return true;
           
        }
         return false;
    }
    
    
        public QuizAppUser getUserByID(String username) {
            ResultSet rs = null;
            QuizAppUser user = null;
            if (!init())
            {
                return user;
            }
            try {
              selectUserStatement.setString(1, username);
              rs = selectUserStatement.executeQuery();
            } catch(SQLException ex)
            {
                System.out.println("*************");
                System.out.println("*************");
                System.out.println("*****" + ex.getMessage() + "***");
                System.out.println("*************");
                System.out.println("*************"); 
            }
            try{
                while (rs.next()) {
                    String tempUser = rs.getString("username");
                    String tempPass = rs.getString("password");
                    user = new QuizAppUser(tempUser, tempPass);
                }
            } catch (SQLException ex) {
                System.err.println("************************");
                System.err.println("** Error opening DB");
                System.err.println("** " + ex.getMessage());
                System.err.println("************************");
                return null;
            }
            return user;
    }
        
}