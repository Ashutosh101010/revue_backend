package org.aurd.Admin.adminModal.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.response.LoginResponse;
import org.aurd.user.constant.Constants;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import static org.aurd.MongoService.adminCollection;

public class AdminUserModal {
    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String email;
    String password;



    public String get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public LoginResponse loginResponse(){
        Document document = new Document();
        document.append("email",email);
        System.out.println(document);
        MongoCursor mongoCursor = adminCollection.find(document).cursor();
        LoginResponse loginResponse = new LoginResponse();

        if(mongoCursor.hasNext()){
            Document doc = (Document) mongoCursor.next();
            if(doc.containsKey("password")&&doc.getString("password").equals(password)){
                loginResponse.setMessage(Constants.MESSAGE_LOGIN_SUCCESS);
                loginResponse.setStatus(Constants.STATUS_SUCCESS);
                loginResponse.setErrorCode(Constants.SUCCESS_LOGIN_CODE);
                AdminUserModal userModal = new Gson().fromJson(doc.toJson(), AdminUserModal.class);
                loginResponse.setUser(userModal);
                return loginResponse;
            }
            else {
                loginResponse.setMessage(Constants.LOGIN_FAILED_DESCRIPTION_PASSWORD_UNMATCHED);
                loginResponse.setStatus(Constants.STATUS_FAIL);
                loginResponse.setErrorCode(Constants.ERROR_CODE);
                return loginResponse;
            }

        } else{
            loginResponse.setMessage(Constants.LOGIN_FAILED_DESCRIPTION_USER_NOT_FOUND);
            loginResponse.setStatus(Constants.STATUS_FAIL);
            loginResponse.setErrorCode(Constants.ERROR_CODE);
            return loginResponse;
        }
    }
}
