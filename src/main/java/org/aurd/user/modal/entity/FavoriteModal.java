package org.aurd.user.modal.entity;


import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.client.MongoCursor;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.response.FavoriteResponse;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import java.util.ArrayList;

import static org.aurd.MongoService.favCollection;


public class FavoriteModal {
    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String compoundID;
    String userID;
    ArrayList favoriteList ;

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


    public FavoriteResponse addFavoriteCompoundRequest(){
        Document doc= new Document();
        doc.append("userID",userID);

        FavoriteResponse addFavoriteResponse = new FavoriteResponse();
        MongoCursor cursor =  favCollection.find(doc).cursor();
       if(cursor.hasNext()){
          Document document = (Document) cursor.next();
          System.out.println(document);
          System.out.println(document.get("favoriteList"));
          ArrayList arrayList  = (ArrayList) document.get("favoriteList");
          for(int i =0;i<favoriteList.size();i++){
              arrayList.add(favoriteList.get(i));
          }
           Document document1 = new Document();

         //          Document document1 = Document.parse(new Gson().toJson(this));
          favCollection.findOneAndUpdate(doc,new Document("$set",document1));
           addFavoriteResponse.setErrorcode(Constants.ERROR_CODE);
           addFavoriteResponse.setStatus(Constants.STATUS_SUCCESS);
           addFavoriteResponse.setMessage(Constants.ADD_TO_FAVORITE);
           return  addFavoriteResponse;
       }else{
           Document document  = Document.parse(new Gson().toJson(this));
           favCollection.insertOne(document);

           addFavoriteResponse.setErrorcode(Constants.ERROR_CODE);
           addFavoriteResponse.setStatus(Constants.STATUS_SUCCESS);
           addFavoriteResponse.setMessage(Constants.ADD_TO_FAVORITE);
           return  addFavoriteResponse;
       }

    }


}
