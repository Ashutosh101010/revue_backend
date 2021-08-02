package org.aurd.user.modal.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.response.PostAnswerResponse;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import static org.aurd.MongoService.answerCollection;


public class AnswerModal {
    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String compoundID;
    String userID;
    String userName;
    int like;
    int dislike;
    String answer;
    String questionID;
    boolean liked;
    boolean disliked;

//    @JsonAdapter(LongAdapter.class)
    Long timestamp;

    public boolean isDisliked() {
        return disliked;
    }

    public void setDisliked(boolean disliked) {
        this.disliked = disliked;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public PostAnswerResponse addAnswerToDB(){

        Document document = Document.parse(new Gson().toJson(this));
        answerCollection.insertOne(document);
        PostAnswerResponse addAnswerResponse = new PostAnswerResponse();
        addAnswerResponse.setErrorCode(Constants.ERROR_CODE);
        addAnswerResponse.setStatus(Constants.STATUS_SUCCESS);
        addAnswerResponse.setMessage(Constants.ANSWER_QUESTION_SUCCESS);
        return addAnswerResponse;
    }
}
