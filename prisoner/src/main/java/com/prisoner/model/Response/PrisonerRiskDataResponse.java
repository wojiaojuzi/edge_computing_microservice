package com.prisoner.model.Response;

import java.sql.Timestamp;

public class PrisonerRiskDataResponse {
    private String prisonerId;

    private String riskValueOfEnvironment;

    private String riskValueOfVideo;

    private String totalRiskValue;

    private Timestamp createAt;

    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getRiskValueOfEnvironment() {
        return riskValueOfEnvironment;
    }

    public void setRiskValueOfEnvironment(String riskValueOfEnvironment) {
        this.riskValueOfEnvironment = riskValueOfEnvironment;
    }

    public String getRiskValueOfVideo() {
        return riskValueOfVideo;
    }

    public void setRiskValueOfVideo(String riskValueOfVideo) {
        this.riskValueOfVideo = riskValueOfVideo;
    }

    public String getTotalRiskValue() {
        return totalRiskValue;
    }

    public void setTotalRiskValue(String totalRiskValue) {
        this.totalRiskValue = totalRiskValue;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
