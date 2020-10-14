package com.device.model;

import io.swagger.annotations.ApiModelProperty;

public class VervelGps {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "手环编号")
    private String vervelNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getVervelNo() {
        return vervelNo;
    }

    public void setVervelNo(String vervelNo) {
        this.vervelNo = vervelNo;
    }
}
