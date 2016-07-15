package com.lbbw.scenari;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by neegbeahreeves on 6/25/16.
 */
@ParseClassName("Questions")
public class QuestionData extends ParseObject {

    public QuestionData(){

    }
/*
    private ParseFile profilePicFile;
    private String username;
    private Date date;

    private String question;
    private String answerA;
    private String answerB;
    private Integer answerAScore;
    private Integer answerBScore;
*/

    /*
    public ParseFile getProfilePicFile() {
        return profilePicFile;
    }

    public void setProfilePicFile(ParseFile profilePicFile) {
        this.profilePicFile = profilePicFile;
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
*/
    public String getQuestion() {
        return getString("question");
    }

    public void setQuestion(String question) {
        put("question",question);
    }

    public String getAnswerA() {
        return getString("answer_a");
    }

    public void setAnswerA(String answerA) {
        put("answer_a",answerA);
    }

    public String getAnswerB() {
        return getString("answer_b");
    }

    public void setAnswerB(String answerB) {
        put("answer_b", answerB);
    }

    /*
    public Integer getAnswerAScore() {
        return getInt("answer_a_total");
    }

    public void setAnswerAScore(Integer answerAScore) {
       put("answer_a_total",answerAScore);
    }

    public Integer getAnswerBScore() {
        return getInt("answer_b_score");
    }

    public void setAnswerBScore(Integer answerBScore) {
        put("answer_b_score",answerBScore);
    }

    public void setObjectID(String objectID){
        put("objectId", objectID);
    }

*/
    public static ParseQuery<QuestionData> getQuery(){
        return ParseQuery.getQuery(QuestionData.class);
    }

    @Override
    public String toString(){
        return getString("question") + "\n" + getString("answer_a")+ "\n" + getString("answer_b");
    }

}
