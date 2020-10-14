package com.prisoner.model;

import io.swagger.annotations.ApiModelProperty;

public class PrisonerRisk {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "犯人ID")
    private String prisonerId;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "风险指数")
    private String riskValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(String riskValue) {
        this.riskValue = riskValue;
    }
}
