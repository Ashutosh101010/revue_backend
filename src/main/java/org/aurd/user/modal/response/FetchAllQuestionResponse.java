package org.aurd.user.modal.response;

import org.aurd.user.modal.entity.AllQuestionsModal;

import java.util.ArrayList;

public class FetchAllQuestionResponse {
    String message;
    boolean status;
    int errorCode;
    ArrayList<AllQuestionsModal> questionsList;

    public ArrayList<AllQuestionsModal> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ArrayList<AllQuestionsModal> questionsList) {
        this.questionsList = questionsList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
