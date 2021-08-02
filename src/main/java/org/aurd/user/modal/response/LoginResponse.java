package org.aurd.user.modal.response;

import org.aurd.user.modal.entity.UserModal;

public class LoginResponse {
    boolean status;
    int errorCode;
    String errorDescription;
    String message;
    UserModal user;

    public UserModal getUser() {
        return user;
    }

    public void setUser(UserModal user) {
        this.user = user;
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

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
