package com.prisoner.model;

import io.swagger.annotations.ApiModelProperty;

public class PrisonerHeartBeat {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "心率")
    private String heartBeat;

    @ApiModelProperty(value = "犯人ID")
    private String prisonerId;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(String heartBeat) {
        this.heartBeat = heartBeat;
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
}
