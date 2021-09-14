package org.aurd.user.modal.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.client.MongoCursor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.response.LoginResponse;
import org.aurd.user.modal.response.RegisterResponse;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;

import static org.aurd.MongoService.users;

public class UserModal implements Serializable {


    @JsonAdapter(ObjectAdapter.class)

    String _id;
    String firstname;
    String lastname;
    String email;
    String password;
    String mobilenumber;
    String token;
    boolean isLoggedIn;
    int reviewCount;
    boolean status;

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModal(String userId) {
        this._id=userId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

//    public RegisterResponse saveUserToDB(){
//        Document document = new Document();
//        document.append("email",email);
//       MongoCursor cursor =  users.find(document).cursor();
//
//       if(cursor.hasNext()){
//
//
//           RegisterResponse registerResponse=new RegisterResponse();
//           registerResponse.setStatus(Constants.STATUS_FAIL);
//           registerResponse.setMessage(Constants.REGISTER_FAILED_ALREADY_EXIST);
//           registerResponse.setErrorCode(Constants.ERROR_CODE);
//           return registerResponse;
//       }else{
//           Document doc = Document.parse(new Gson().toJson(UserModal.class));
//           System.out.println(doc.toJson());
//           users.insertOne(doc);
//           RegisterResponse registerResponse = new RegisterResponse();
//           registerResponse.setStatus(Constants.STATUS_SUCCESS);
//           registerResponse.setMessage(Constants.MESSAGE_REGISTRATION_SUCCESS);
//           registerResponse.setErrorCode(Constants.SUCCESS_REGISTER_CODE);
//           return registerResponse;
//       }
//    }


    public LoginResponse login(){

        Document document =new Document();
        document.append("email",email);
        MongoCursor mongoCursor = users.find(document).cursor();
        LoginResponse loginResponse = new LoginResponse();

       if(mongoCursor.hasNext()){
           Document doc = (Document) mongoCursor.next();
           if(doc.containsKey("verified")&&!doc.getBoolean("verified"))
           {
               loginResponse.setMessage("Email not verified");
               loginResponse.setStatus(Constants.STATUS_FAIL);
               loginResponse.setErrorCode(5);
               return loginResponse;
           }


           if(doc.containsKey("status") && !doc.getBoolean("status")){
               loginResponse.setMessage("Account is inactive");
               loginResponse.setStatus(Constants.STATUS_FAIL);
               loginResponse.setErrorCode(6);
               return loginResponse;
           }
           if(doc.containsKey("password")&&doc.getString("password").equals(password)){
               loginResponse.setMessage(Constants.MESSAGE_LOGIN_SUCCESS);
               loginResponse.setStatus(Constants.STATUS_SUCCESS);
               loginResponse.setErrorCode(Constants.SUCCESS_LOGIN_CODE);
               Document update=new Document();
               update.append("token",DigestUtils.sha3_256Hex((doc.get("_id").toString())));
               update.append("isLoggedIn",true);

               users.findOneAndUpdate(new Document("_id",doc.get("_id")),new Document("$set",update));
               UserModal userModal=new Gson().fromJson(doc.toJson(),UserModal.class);
               loginResponse.setUser(userModal);
               return loginResponse;
           }
           else{
               loginResponse.setMessage(Constants.LOGIN_FAILED_DESCRIPTION_PASSWORD_UNMATCHED);
               loginResponse.setStatus(Constants.STATUS_FAIL);
               loginResponse.setErrorCode(Constants.ERROR_CODE);
               return loginResponse;
           }

       }else{
           loginResponse.setMessage(Constants.LOGIN_FAILED_DESCRIPTION_USER_NOT_FOUND);
           loginResponse.setStatus(Constants.STATUS_FAIL);
           loginResponse.setErrorCode(Constants.ERROR_CODE);
           return  loginResponse;
       }
    }

}
