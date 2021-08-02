package org.aurd.user.modal.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.response.ReportAnswerResponse;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import static org.aurd.MongoService.reportCollection;


public class ReportModal {

    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String userID;
    String userName;
    String answerID;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getAnswerID() {
        return answerID;
    }

    public void setAnswerID(String answerID) {
        this.answerID = answerID;
    }


    public ReportAnswerResponse reportAnswerAndAddToDB(){
        Document document = Document.parse( new Gson().toJson(this));
        reportCollection.insertOne(document);

//        int count = 0;
//      MongoCursor cursor =  reportCollection.find(new Document("answerID",answerID)).iterator();
//      while(cursor.hasNext()){
//          count = count+1;
//      }
//      Document countDocument = new Document();
//      document.put("report",count);
//      answerCollection.findOneAndUpdate(new Document("_id",new ObjectId(answerID)),
//              new Document("$set",countDocument));

      ReportAnswerResponse reportAnswerResponse = new ReportAnswerResponse();
      reportAnswerResponse.setMessage(Constants.REPORT_ANSWER_SUCCESS);
      reportAnswerResponse.setErrorcode(Constants.ERROR_CODE);
      reportAnswerResponse.setStatus(Constants.STATUS_SUCCESS);

      return  reportAnswerResponse;
    }
}
