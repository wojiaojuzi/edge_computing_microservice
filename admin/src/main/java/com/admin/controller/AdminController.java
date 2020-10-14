package com.admin.controller;

import com.admin.model.Admin;
import com.admin.model.Request.AdminLoginRequest;
import com.admin.model.Response.HttpResponseContent;
import com.admin.model.Response.ResponseEnum;
import com.admin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zhaoone on 2019/10/22
 **/

@RestController
@RequestMapping(path = "/admins")
@EnableAutoConfiguration
@Api(tags = "Admin", description = "管理员相关操作")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation(value = "管理员登陆")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public HttpResponseContent adminLogin(@RequestBody AdminLoginRequest adminLoginRequest) throws Exception {
        System.out.println(adminLoginRequest.getAdminId());
        HttpResponseContent response = new HttpResponseContent();
        Admin admin = adminService.userLogin(adminLoginRequest.getAdminId(), adminLoginRequest.getPassword());
        if (admin == null) {
            response.setCode(ResponseEnum.LOGIN_FAILED.getCode());
            response.setMessage(ResponseEnum.LOGIN_FAILED.getMessage());
        } else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
            response.setData(admin);
        }
        return response;
    }

    @ApiOperation(value = "管理员登出")
    @RequestMapping(path = "/logout", method = RequestMethod.DELETE)
    public HttpResponseContent userLogout(@RequestParam(value = "token", defaultValue = "noToken") String token) throws Exception {
        String userId = adminService.getUserIdFromToken(token);
        HttpResponseContent response = new HttpResponseContent();
        adminService.userLogout(token);
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        return response;
    }

    @ApiOperation(value = "管理员信息")
    @RequestMapping(path = "/getAboutInformation", method = RequestMethod.GET)
    public Admin aboutInformation(@RequestParam(value = "adminId") String adminId,@RequestHeader(value="token") String token){
        return adminService.aboutInformation(adminId);
    }

    @ApiOperation(value="鉴权")
    @RequestMapping(path = "/getUserIdFromToken", method = RequestMethod.GET)
    public String getUserIdFromToken(@RequestParam("token")String token) throws Exception {
        return adminService.getUserIdFromToken(token);
    }

}
