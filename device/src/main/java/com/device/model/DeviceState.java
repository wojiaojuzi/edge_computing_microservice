package com.device.model;

import io.swagger.annotations.ApiModelProperty;

public class DeviceState {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "剩余电量")
    private String dumpEnergyRate;

    @ApiModelProperty(value = "CPU使用率")
    private String cpuUsageRate;

    @ApiModelProperty(value = "内存使用率")
    private String memoryUsageRate;

    @ApiModelProperty(value = "设备编号")
    private String deviceNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getDumpEnergyRate() {
        return dumpEnergyRate;
    }

    public void setDumpEnergyRate(String dumpEnergyRate) {
        this.dumpEnergyRate = dumpEnergyRate;
    }

    public String getCpuUsageRate() {
        return cpuUsageRate;
    }

    public void setCpuUsageRate(String cpuUsageRate) {
        this.cpuUsageRate = cpuUsageRate;
    }

    public String getMemoryUsageRate() {
        return memoryUsageRate;
    }

    public void setMemoryUsageRate(String memoryUsageRate) {
        this.memoryUsageRate = memoryUsageRate;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
