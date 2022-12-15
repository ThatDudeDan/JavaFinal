/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joe
 */
public class QuizResult {
    
    private String resultId;
    private String quizID;
    private String username;
    private List<String> userAnswers;
    private Timestamp quizStartTime;
    private Timestamp quizEndTime;
    private int scoreNumerator;
    private int scoreDenumerator;
    
    //TODO add override to construct a QuizResult with a premade list of answers
    public QuizResult(
            String idIn, String quizIn, String userIn, List<String> userAnswers, Timestamp startTimeIn, Timestamp endTimeIn,
            int numeratorIn, int denumeratorIn){
        
        resultId = idIn;
        quizIn = quizIn;
        username = userIn;
        quizStartTime = startTimeIn;
        quizEndTime = endTimeIn;
        userAnswers = new ArrayList<String>();
        scoreNumerator = numeratorIn;
        scoreDenumerator = denumeratorIn;
    }
    
    public double calculateScore(){
        return scoreNumerator/scoreDenumerator;
    }
    
    //TODO finish this method.
    public void calculateTimeTaken(){
        
    }
    
    public void addAnswer(String answerIn){
        userAnswers.add(answerIn);
    }
    
    public void addAnswer(List<String> answerIn){
        userAnswers = answerIn;
    }
    
    public int getId(){
        return resultId;
    }
    
    public Quiz getQuiz(){
        return completedQuiz;
    }
    
    public QuizAppUser getUser(){
        return user;
    }
    
    public Date getStartTime(){
        return quizStartTime;
    }
    
    public Date getEndTime(){
        return quizEndTime;
    }
    
    public List<String> getUserAnswers(){
        return userAnswers;
    }
    
}
