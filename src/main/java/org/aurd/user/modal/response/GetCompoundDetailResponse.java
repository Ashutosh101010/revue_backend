package org.aurd.user.modal.response;

import org.aurd.user.modal.entity.CompoundModal;

public class GetCompoundDetailResponse {
    boolean status;
    String message;
    CompoundModal compoundModal;
    int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public CompoundModal getCompoundModal() {
        return compoundModal;
    }

    public void setCompoundModal(CompoundModal compoundModal) {
        this.compoundModal = compoundModal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
