package com.device.model;

import io.swagger.annotations.ApiModelProperty;

public class DeviceConnection {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "设备连接状态")
    private boolean deviceConnectivityStatus;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "备注")
    private String record;

    @ApiModelProperty(value = "设备编号")
    private String deviceNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeviceConnectivityStatus() {
        return deviceConnectivityStatus;
    }

    public void setDeviceConnectivityStatus(boolean deviceConnectivityStatus) {
        this.deviceConnectivityStatus = deviceConnectivityStatus;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
