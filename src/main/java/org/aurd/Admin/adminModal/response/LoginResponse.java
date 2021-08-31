package org.aurd.Admin.adminModal.response;

import org.aurd.Admin.adminModal.entity.AdminUserModal;

public class LoginResponse {
    boolean status;
    int errorCode;
    String message;
    String errorDescription;
    AdminUserModal user;

    public AdminUserModal getUser() {
        return user;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUser(AdminUserModal user) {
        this.user = user;
    }
}
