/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author joe
 */
public class Quiz {
    
    private String quizId;
    private String quizTitle;
    private List<Question> quizQuestions;
    private List<Integer> points;
    
    
    public Quiz(String idIn, String titleIn){
        quizId = idIn;
        quizTitle = titleIn;
    }
    
    public Quiz(String idIn, String titleIn, List<Question> questionsIn, List<Integer> inpoints){
        quizId = idIn;
        quizTitle = titleIn;
        quizQuestions = questionsIn;
        points=inpoints;
        
    }    
    public String getQuizId(){
        return quizId;
    }
    
    public String getQuizTitle(){
        return quizTitle;
    }
    
    public List<Question> getQuestions(){
        return quizQuestions;
    }
    public List<Integer> getPoints(){
        return points;
    }
}
