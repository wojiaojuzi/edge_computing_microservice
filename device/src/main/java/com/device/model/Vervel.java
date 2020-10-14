package com.device.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Zhaoone on 2019/11/4
 **/
public class Vervel {

    @ApiModelProperty(value = "手环编号")
    private String vervelNo;

    @ApiModelProperty(value = "绑定手持机编号")
    private String deviceNo;

    @ApiModelProperty(value = "设备创建时间")
    private String createAt;

    public String getVervelNo() {
        return vervelNo;
    }

    public void setVervelNo(String vervelNo) {
        this.vervelNo = vervelNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
