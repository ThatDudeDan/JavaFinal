/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author joe
 */
public class QuizAppUser {
    
    private String username;
    private String password;
    private Permission permissionLevel;
    
    public QuizAppUser(String userIn, String passwordIn, Permission lvlIn){
        username = userIn;
        password = passwordIn;
        permissionLevel = lvlIn;
    }
    
    public String getUsername(){
        return username;
    }
    
    //TODO change to a more secure format later
    public String getpassword(){
        return password;
    }
    
    public Permission getPermission(){
        return permissionLevel;
    }
}
