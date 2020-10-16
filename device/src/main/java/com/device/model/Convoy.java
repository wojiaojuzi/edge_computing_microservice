package com.device.model;

import io.swagger.annotations.ApiModelProperty;

public class Convoy {
    @ApiModelProperty(value = "车队ID")
    private String id;

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "车牌号")
    private String carNo;

    @ApiModelProperty(value = "犯人ID")
    private String prisonerId;

    @ApiModelProperty(value = "管理员ID")
    private String adminId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Convoy{" +
                "id='" + id + '\'' +
                ", taskNo='" + taskNo + '\'' +
                ", userId='" + userId + '\'' +
                ", carNo='" + carNo + '\'' +
                ", prisonerId='" + prisonerId + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
