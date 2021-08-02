package org.aurd.user.modal.entity;

import java.util.ArrayList;

public class AllQuestionsModal {
    String _id;
    String compoundID;
    String question;
    String userID;
    String userName;
    ArrayList<AnswerModal> answersList;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCompoundID() {
        return compoundID;
    }

    public void setCompoundID(String compoundID) {
        this.compoundID = compoundID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<AnswerModal> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(ArrayList<AnswerModal> answersList) {
        this.answersList = answersList;
    }
}
