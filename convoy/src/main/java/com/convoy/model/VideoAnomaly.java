package com.convoy.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

public class VideoAnomaly {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "脚环风险等级")
    private String videoRiskLevel;

    @ApiModelProperty(value = "异常类型")
    private String abnomalType;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "车辆编号")
    private String carNo;

    private Integer videoRiskValue;

    private String storagePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoRiskLevel() {
        return videoRiskLevel;
    }

    public void setVideoRiskLevel(String videoRiskLevel) {
        this.videoRiskLevel = videoRiskLevel;
    }

    public String getAbnomalType() {
        return abnomalType;
    }

    public void setAbnomalType(String abnomalType) {
        this.abnomalType = abnomalType;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getVideoRiskValue() {
        return videoRiskValue;
    }

    public void setVideoRiskValue(Integer videoRiskValue) {
        this.videoRiskValue = videoRiskValue;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }
}
