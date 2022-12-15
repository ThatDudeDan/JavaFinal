/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DG
 */
public class QuizQuestion {
    
    private String quizID;
    private String questionID;
    private int points;
    
    public QuizQuestion(String quizID, String questionIDIn, int pointsIn){
        this.quizID = quizID;
        this.questionID = questionIDIn;
        this.points = pointsIn;
    }
    
    public String getQuizId(){
        return this.quizID;
    }
    
    public String getQuestionId(){
        return this.questionID;
    }
    
    public int getPoints(){
        return this.points;
    }
        
}
