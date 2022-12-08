/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author joe
 */
public class Permission {
    
    private String permissionLevel;
    
    public Permission(String lvl){
        permissionLevel = lvl;
    }
    
    public String getPermissionLevel(){
        return permissionLevel;
    }
}
