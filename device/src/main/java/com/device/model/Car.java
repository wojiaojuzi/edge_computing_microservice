package com.device.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Zhaoone on 2019/10/21
 **/
public class Car {
    @ApiModelProperty(value = "押解车编号")
    private String carNo;

    @ApiModelProperty(value = "押解车型号")
    private String type;

    @ApiModelProperty(value = "押解车颜色")
    private String color;

    @ApiModelProperty(value = "押解车等级")
    private String level;

    @ApiModelProperty(value = "相片地址")
    private String CarPhotoUrl;

    @ApiModelProperty(value = "车内视频地址")
    private String CarInnerVideoUrl;

    @ApiModelProperty(value = "车外视频地址")
    private String CarOuterVideoUrl;

    @ApiModelProperty(value = "车内视频地址")
    private String CameraInnerIp;

    @ApiModelProperty(value = "车外视频地址")
    private String CameraOuterIp;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCarPhotoUrl() {
        return CarPhotoUrl;
    }

    public void setCarPhotoUrl(String carPhotoUrl) {
        CarPhotoUrl = carPhotoUrl;
    }

    public String getCarInnerVideoUrl() {
        return CarInnerVideoUrl;
    }

    public void setCarInnerVideoUrl(String carInnerVideoUrl) {
        CarInnerVideoUrl = carInnerVideoUrl;
    }

    public String getCarOuterVideoUrl() {
        return CarOuterVideoUrl;
    }

    public void setCarOuterVideoUrl(String carOuterVideoUrl) {
        CarOuterVideoUrl = carOuterVideoUrl;
    }

    public String getCameraInnerIp() {
        return CameraInnerIp;
    }

    public void setCameraInnerIp(String cameraInnerIp) {
        CameraInnerIp = cameraInnerIp;
    }

    public String getCameraOuterIp() {
        return CameraOuterIp;
    }

    public void setCameraOuterIp(String cameraOuterIp) {
        CameraOuterIp = cameraOuterIp;
    }
}
