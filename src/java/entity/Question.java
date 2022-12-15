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
    
    public Question(String idIn, String textIn, String choicesIn, int answerIn){
        this.questionId = idIn;
        this.questionText = textIn;
        this.choices = choicesIn;
        this.answer = answerIn;
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
    
}
