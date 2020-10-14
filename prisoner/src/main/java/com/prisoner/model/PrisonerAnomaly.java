package com.prisoner.model;

public class PrisonerAnomaly {
    private String id;

    private String riskId;

    private String createAt;

    private String level;

    private boolean dealState;

    private boolean misdeclaration;

    private String description;

    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isDealState() {
        return dealState;
    }

    public void setDealState(boolean dealState) {
        this.dealState = dealState;
    }

    public boolean isMisdeclaration() {
        return misdeclaration;
    }

    public void setMisdeclaration(boolean misdeclaration) {
        this.misdeclaration = misdeclaration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
