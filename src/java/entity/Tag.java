/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joe
 */
public class Tag {
    
    private int tagId;
    private String tagName;
    private List<String> tagCategories;
    
    public Tag(int idIn, String nameIn){
        tagId = idIn;
        tagName = nameIn;
        tagCategories = new ArrayList<String>();
    }
    
    public void addCategory(String catIn){
        tagCategories.add(catIn);
    }
    
    public void addCategorys(List<String> catIn){
        tagCategories = catIn;
    }
    
    public int getId(){
        return tagId;
    }
    
    public String getTagName(){
        return tagName;
    }
    
    public List<String> getTagCategories(){
        return tagCategories;
    }
    
}
