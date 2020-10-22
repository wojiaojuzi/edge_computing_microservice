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
    private Integer carNo;

    private Integer cover;

    private Integer stand;

    private Integer sit;

    private Integer kick;

    private Integer fight;

    private Integer punch;

    private Integer walk;

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

    public Integer getCarNo() {
        return carNo;
    }

    public void setCarNo(Integer carNo) {
        this.carNo = carNo;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    public Integer getStand() {
        return stand;
    }

    public void setStand(Integer stand) {
        this.stand = stand;
    }

    public Integer getSit() {
        return sit;
    }

    public void setSit(Integer sit) {
        this.sit = sit;
    }

    public Integer getKick() {
        return kick;
    }

    public void setKick(Integer kick) {
        this.kick = kick;
    }

    public Integer getFight() {
        return fight;
    }

    public void setFight(Integer fight) {
        this.fight = fight;
    }

    public Integer getPunch() {
        return punch;
    }

    public void setPunch(Integer punch) {
        this.punch = punch;
    }

    public Integer getWalk() {
        return walk;
    }

    public void setWalk(Integer walk) {
        this.walk = walk;
    }
}
