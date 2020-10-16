package com.prisoner.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Zhaoone on 2019/10/22
 **/
public class Task {

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "任务等级")
    private String level;

    @ApiModelProperty(value = "车牌号")
    private String carNo;

    @ApiModelProperty(value = "任务详情")
    private String detail;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "任务起始时间")
    private String startEscortTime;

    @ApiModelProperty(value = "任务结束时间时间")
    private String endEscortTime;

    @ApiModelProperty(value = "起点")
    private String startingPoint;

    @ApiModelProperty(value = "终点")
    private String endingPoint;

    @ApiModelProperty(value = "路线编号")
    private String escortRoute;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getStartEscortTime() {
        return startEscortTime;
    }

    public void setStartEscortTime(String startEscortTime) {
        this.startEscortTime = startEscortTime;
    }

    public String getEndEscortTime() {
        return endEscortTime;
    }

    public void setEndEscortTime(String endEscortTime) {
        this.endEscortTime = endEscortTime;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public String getEscortRoute() {
        return escortRoute;
    }

    public void setEscortRoute(String escortRoute) {
        this.escortRoute = escortRoute;
    }
}
