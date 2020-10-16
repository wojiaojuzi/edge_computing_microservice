package com.prisoner.model.Response;

public class AbnormalResponse {
    private String createAt;

    private String prisonerName;

    private String exceptionType;

    private String exceptionLevel;

    private boolean dealState;

    private boolean misdeclaration;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMisdeclaration() {
        return misdeclaration;
    }

    public void setMisdeclaration(boolean misdeclaration) {
        this.misdeclaration = misdeclaration;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionLevel() {
        return exceptionLevel;
    }

    public void setExceptionLevel(String exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }

    public boolean isDealState() {
        return dealState;
    }

    public void setDealState(boolean dealState) {
        this.dealState = dealState;
    }
}
