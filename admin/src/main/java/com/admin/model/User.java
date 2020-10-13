package com.admin.model;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

public class User {
    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户真实姓名")
    private String userName;

    @ApiModelProperty(value = "用户登录密码")
    private String password;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "账号创建时间")
    private Timestamp createAt;

    @ApiModelProperty(value = "token生成时间")
    private String tokenCreateAt;

    @ApiModelProperty(value = "登录token")
    private String loginToken;

    @ApiModelProperty(value = "职务类别")
    private String policeType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getTokenCreateAt() {
        return tokenCreateAt;
    }

    public void setTokenCreateAt(String tokenCreateAt) {
        this.tokenCreateAt = tokenCreateAt;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }
}
