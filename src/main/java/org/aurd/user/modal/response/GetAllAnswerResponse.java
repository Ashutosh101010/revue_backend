package org.aurd.user.modal.response;


import org.aurd.user.modal.entity.AnswerModal;

import java.util.ArrayList;

public class GetAllAnswerResponse {
    ArrayList<AnswerModal> answerList;
    String message;
    boolean status;
    int errorCode;

    public ArrayList<AnswerModal> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<AnswerModal> answerList) {
        this.answerList = answerList;
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
