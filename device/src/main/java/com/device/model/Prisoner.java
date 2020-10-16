package com.device.model;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * @Author: jojo
 * @Description:
 * @Date: Created on 2019/5/6 21:06
 */
public class Prisoner {
    @ApiModelProperty(value = "犯人编号")
    private String prisonerId;

    @ApiModelProperty(value = "犯人真实姓名")
    private String prisonerName;

    @ApiModelProperty(value = "犯人头像")
    private String prisonerPhotoUrl;

    @ApiModelProperty(value = "犯人年龄")
    private String age;

    @ApiModelProperty(value = "犯人性别")
    private String gender;

    @ApiModelProperty(value = "犯人教育程度")
    private String educationBackground;

    @ApiModelProperty(value = "犯人身高")
    private String height;

    @ApiModelProperty(value = "犯人体重")
    private String weight;

    @ApiModelProperty(value = "犯人体脂率")
    private String bodyFatRate;

    @ApiModelProperty(value = "犯人罪行")
    private String crime;

    @ApiModelProperty(value = "犯人前科")
    private String criminalRecord;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "犯人特征")
    private String feature;

    @ApiModelProperty(value = "记录创建时间")
    private Timestamp createAt;

    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    public String getPrisonerPhotoUrl() {
        return prisonerPhotoUrl;
    }

    public void setPrisonerPhotoUrl(String prisonerPhotoUrl) {
        this.prisonerPhotoUrl = prisonerPhotoUrl;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBodyFatRate() {
        return bodyFatRate;
    }

    public void setBodyFatRate(String bodyFatRate) {
        this.bodyFatRate = bodyFatRate;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(String criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
