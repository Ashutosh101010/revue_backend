package org.aurd.Admin.adminModal.response;

import org.aurd.user.modal.entity.UserModal;

import java.util.ArrayList;

public class GetAllUsersResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<UserModal> users  =new ArrayList<UserModal>();

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUsers(ArrayList<UserModal> users) {
        this.users = users;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public ArrayList<UserModal> getUsers() {
        return users;
    }

    public String getMessage() {
        return message;
    }
}
