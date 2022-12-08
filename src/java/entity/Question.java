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
public class Question {
    
    private String questionId;
    private String questionText;
    private String choices;
    private int answer;
    private List<Tag> questionTags;
    
    public Question(String idIn, String textIn, String choicesIn, int answerIn){
        questionId = idIn;
        questionText = textIn;
        choices = choicesIn;
        answer = answerIn;
        questionTags = new ArrayList<Tag>();
    }
    
    public void addTag(Tag tagIn){
        questionTags.add(tagIn);
    }
    
    public void addTags(List<Tag> tagsIn){
        questionTags = tagsIn;
    }
    
    public String getQId(){
        return questionId;
    }
    
    public String getQText(){
        return questionText;
    }
    
    public String getChoices(){
        return choices;
    }
    
    public int getAnswer(){
        return answer;
    }
    
    public List<Tag> getTags(){
        return questionTags;
    }
    
}
