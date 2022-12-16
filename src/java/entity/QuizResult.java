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
            String idIn, String quizIn, String userIn, List<String> inUserAnswers, Timestamp startTimeIn, Timestamp endTimeIn,
            int numeratorIn, int denumeratorIn){
        resultId = idIn;
        quizID = quizIn;
        username = userIn;
        quizStartTime = startTimeIn;
        quizEndTime = endTimeIn;
        userAnswers = inUserAnswers;
        scoreNumerator = numeratorIn;
        scoreDenumerator = denumeratorIn;
    }
    
    public double calculateScore(){
        return ((double) scoreNumerator/scoreDenumerator)*100;
    }
    public int getNum()
    {
        return scoreNumerator;
    }
    public int getDen()
    {
        return scoreDenumerator;
    }
    //TODO finish this method.
    public long calculateTimeTaken(){
        return (quizEndTime.getTime() - quizStartTime.getTime()) / 1000;
    }
    
    public void addAnswer(String answerIn){
        userAnswers.add(answerIn);
    }
    
    public void addAnswer(List<String> answerIn){
        userAnswers = answerIn;
    }
    
    public String getId(){
        return resultId;
    }
    
    public String getQuiz(){
        return quizID;
    }
    
    public String getUser(){
        return username;
    }
    
    public Timestamp getStartTime(){
        return quizStartTime;
    }
    
    public Timestamp getEndTime(){
        return quizEndTime;
    }
    
    public List<String> getUserAnswers(){
        return userAnswers;
    }
    public String getUserAnswersString()
    {
        return String.join("|", userAnswers);
    }
    
}
