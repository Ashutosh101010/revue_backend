package org.aurd.user.modal.entity;


import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.client.MongoCursor;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import java.util.ArrayList;

import static org.aurd.MongoService.answerCollection;
import static org.aurd.MongoService.questionCollection;


public class QuestionModal {
    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String compoundID;
    String userID;
    String userName;
    String question;
    String compoundName;
//    @JsonAdapter(LongAdapter.class)
    long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCompoundName() {
        return compoundName;
    }

    public void setCompoundName(String compoundName) {
        this.compoundName = compoundName;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }





    public static ArrayList fetchQuestions(String compoundID){
        Document document = new Document();
        document.append("compoundID",compoundID);
        MongoCursor cursor =  questionCollection.find(document).iterator();
       ArrayList<AllQuestionsModal> questionList = new ArrayList();

        while(cursor.hasNext()){
            AllQuestionsModal allQuestionsModal = new AllQuestionsModal();
            Document questDoc = (Document) cursor.next();
            allQuestionsModal.set_id(questDoc.get("_id").toString());
            allQuestionsModal.setCompoundID(questDoc.getString("compoundID"));
            allQuestionsModal.setUserName(questDoc.getString("userName"));
            allQuestionsModal.setUserID(questDoc.getString("userID"));
            allQuestionsModal.setQuestion(questDoc.getString("question"));

//            Document ansDocument  = (Document) answerCollection.find(new Document("questionID",
//                    questDoc.get("_id").toString())).iterator().next(
//
//            );
//            ArrayList<AnswerModal> arrayList = new ArrayList();
//            System.out.println(ansDocument.toJson());
//            AnswerModal answerModal = new Gson().fromJson(ansDocument.toJson(),AnswerModal.class);
//            arrayList.add(answerModal);


            MongoCursor ansCursor = answerCollection.find(new Document("questionID",
                    questDoc.get("_id").toString())).iterator();
            ArrayList<AnswerModal> arrayList = new ArrayList();
            while(ansCursor.hasNext()){
                Document ansDocument = (Document) ansCursor.next();
                System.out.println(ansDocument.toJson());
                AnswerModal answerModal = new Gson().fromJson(ansDocument.toJson(),AnswerModal.class);
                arrayList.add(answerModal);
            }

            allQuestionsModal.setAnswersList(arrayList);

            questionList.add(allQuestionsModal);
        }
        return questionList;
    }




}
