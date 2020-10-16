package com.admin.controller;

import com.admin.model.Device;
import com.admin.model.Request.DeviceRegisterRequest;
import com.admin.model.Request.LoginRequest;
import com.admin.model.Request.UserRegisterRequest;
import com.admin.model.Response.HttpResponseContent;
import com.admin.model.Response.ResponseEnum;
import com.admin.model.User;
import com.admin.service.DeviceService;
import com.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zhaoone
 * @Description:添加功能
 * @Date: Created on 2019/10/14
 */
@RestController
@RequestMapping(path = "/users")
@EnableAutoConfiguration
@Api(tags = "User", description = "用户相关的操作")
public class UserController {

    private final UserService userService;
    private final DeviceService deviceService;

    @Autowired
    public UserController(UserService userService, DeviceService deviceService) {
        this.userService = userService;
        this.deviceService = deviceService;
    }



    @ApiOperation(value = "用户登陆")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public HttpResponseContent userLogin(@RequestParam("userId") String userId,
                                         @RequestParam("password") String password,
                                         @RequestParam("deviceNo") String deviceNo,
                                         @RequestParam("deviceType") String deviceType) throws Exception {
        HttpResponseContent response = new HttpResponseContent();
        LoginRequest loginRequest = new LoginRequest(userId, password);
        User user = userService.userLogin(loginRequest);
        if(user == null) {
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        } else {
            //将设备表里的该设备修改为该用户
            DeviceRegisterRequest deviceRegisterRequest = new DeviceRegisterRequest(deviceType,
                    userId, deviceNo);
            Device device = deviceService.createDevice(deviceRegisterRequest);
            if(device == null) {
                response.setCode(ResponseEnum.REQUEST_ERROR.getCode());
                response.setMessage(ResponseEnum.REQUEST_ERROR.getMessage());
            } else {
                response.setCode(ResponseEnum.SUCCESS.getCode());
                response.setMessage(ResponseEnum.SUCCESS.getMessage());
                response.setData(user);
            }
        }
        return response;
    }

    @ApiOperation(value = "用户注销登录")
    @RequestMapping(path = "/logout", method = RequestMethod.DELETE)
    public HttpResponseContent userLogout(@RequestParam(value = "token", defaultValue = "noToken") String token) throws Exception {
        String userId = userService.getUserIdFromToken(token);
        HttpResponseContent response = new HttpResponseContent();
        userService.userLogout(token);
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        return response;
    }

    @ApiOperation(value = "新用户注册")
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public HttpResponseContent createUser(@RequestParam("userId") String userId,
                                          @RequestParam("password") String password,
                                          @RequestParam("confirmPwd") String confirmPwd,
                                          @RequestParam("userName") String userName,
                                          @RequestParam("idCard") String idCard) {
        HttpResponseContent response = new HttpResponseContent();
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(userName, password, confirmPwd, userId, idCard);
        if(userService.getByUserName(userRegisterRequest.getUserName()) != null) {
            response.setCode(ResponseEnum.USERNAME_EXIST.getCode());
            response.setMessage(ResponseEnum.USERNAME_EXIST.getMessage());
        } else if(userRegisterRequest.getPassword().length() < 6) {
            response.setCode(ResponseEnum.PASSWORD_TOO_SHORT.getCode());
            response.setMessage(ResponseEnum.PASSWORD_TOO_SHORT.getMessage());
        } else if(!userRegisterRequest.getPassword().equals(userRegisterRequest.getConfirmPassword())) {
            response.setCode(ResponseEnum.PASSWORD_CONFIRM_ERROR.getCode());
            response.setMessage(ResponseEnum.PASSWORD_CONFIRM_ERROR.getMessage());
        } else {
            User user = new User();
            user.setUserName(userRegisterRequest.getUserName());
            user.setPassword(userRegisterRequest.getPassword());
            user.setUserId(userRegisterRequest.getUserId());
            user.setIdCard(userRegisterRequest.getIdCard());
            userService.createUser(user);
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
            response.setData(user);
        }
        return response;
    }

    @RequestMapping(value = "/getUserNameByUserId")
    public String getUserNameByUserId(@RequestParam("userId")String userId){
        return userService.getUserNameByUserId(userId);
    }

}
