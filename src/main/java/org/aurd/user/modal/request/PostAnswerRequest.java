package org.aurd.user.modal.request;

public class PostAnswerRequest {

    String compoundID;
    String userID;
    String userName;
    String answer;
    String questionID;
//    @JsonAdapter(LongAdapter.class)
    Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }



    public String getCompoundID() {
        return compoundID;
    }

    public void setCompoundID(String compoundID) {
        this.compoundID = compoundID;
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
}
