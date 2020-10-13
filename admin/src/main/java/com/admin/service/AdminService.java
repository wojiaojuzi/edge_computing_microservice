package com.admin.service;

import com.admin.model.Admin;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.admin.mapper.AdminMapper;
import com.admin.mapper.UserMapper;
import com.admin.model.Exception.EdgeComputingServiceException;
import com.admin.model.Response.ResponseEnum;
import com.admin.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Zhaoone on 2019/10/22
 **/
@Service
public class AdminService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";

    private final AdminMapper adminMapper;
    private final UserMapper userMapper;

    @Autowired
    public AdminService(AdminMapper adminMapper, UserMapper userMapper) {
        this.adminMapper = adminMapper;
        this.userMapper = userMapper;
    }

    public Admin userLogin(String adminId, String password) throws Exception {
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Admin admin = adminMapper.getByAdminIdAndPassword(adminId, password);
        if(admin == null) {
            throw new EdgeComputingServiceException(ResponseEnum.LOGIN_FAILED.getCode(), ResponseEnum.LOGIN_FAILED.getMessage());
        } else {
            //采用jwt获得token
            Date createTime = new Date();
            String createAt = adminMapper.getTokenCreatedTime(adminId);
            /*if(createAt !="") {
                Date tokenCreateAt = mysqlSdf.parse(createAt);
                if ((createTime.getTime() < tokenCreateAt.getTime() + 1000 * 60 * 60 * 24 * 7))
                    throw new EdgeComputingServiceException(ResponseEnum.LOGIN_FAILED.getCode(), ResponseEnum.LOGIN_FAILED.getMessage());
            }*/

            //每七天需要重新登录(可以直接将过期时间写入token，解析token时即可判断是否过期，而无需在代码中判断)
            Date expireTime = new Date(createTime.getTime() + 1000 * 60 * 60 * 24 * 7);
            Map<String, String> content = new HashMap<>();
            content.put("uid", admin.getAdminId());
            String token = CommonUtil.createJWT(content, "EdgeComputingService", createTime, expireTime);
            //更新数据库token_create_at，之后每次鉴权需要查看数据库查看自己的token是否是最新的
            adminMapper.updateTokenCreateTimeByadminId(mysqlSdf.format(createTime), admin.getAdminId());
            //将token返回作为登录凭证
            admin.setLoginToken(token);
            admin.setPassword(null);
            //admin.setTokenCreatedAt(null);
        }
        return admin;
    }

    public void userLogout(String token) throws Exception {
        String adminId = getUserIdFromToken(token);
        adminMapper.updateTokenCreateTimeByadminId(null, adminId);
    }

    public Admin aboutInformation(String adminId){
        return adminMapper.getByadminId(adminId);
    }

    public String getUserIdFromToken(String token) throws Exception {
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        if (Objects.equals(token, "noToken")) {
            throw new EdgeComputingServiceException(ResponseEnum.DO_NOT_LOGIN.getCode(), ResponseEnum.DO_NOT_LOGIN.getMessage());
        }
        Date now = new Date();
        DecodedJWT jwt = CommonUtil.phraseJWT(token, "EdgeComputingService", ResponseEnum.INVALID_USER_TOKEN.getMessage());
        String adminId = JSONObject.parseObject(jwt.getSubject()).getString("uid");
        String tokenCreateTime = adminMapper.getTokenCreatedTime(adminId);
        if(tokenCreateTime == null) {
            System.out.println("这是user");
            tokenCreateTime = userMapper.getTokenCreateTime(adminId);
        }

        //数据库中token创建时间字段为空，说明用户已经注销登陆
        if (tokenCreateTime == null)
            throw new EdgeComputingServiceException(ResponseEnum.DO_NOT_LOGIN.getCode(), ResponseEnum.DO_NOT_LOGIN.getMessage());
        else if (jwt.getIssuedAt().getTime() != mysqlSdf.parse(tokenCreateTime).getTime())
            throw new EdgeComputingServiceException(ResponseEnum.ALREADY_LOGIN.getCode(), ResponseEnum.ALREADY_LOGIN.getMessage());
        else if (jwt.getExpiresAt().getTime() < now.getTime())
            throw new EdgeComputingServiceException(ResponseEnum.EXPIRED_USER_TOKEN.getCode(), ResponseEnum.EXPIRED_USER_TOKEN.getMessage());
        else return adminId;
    }
}
