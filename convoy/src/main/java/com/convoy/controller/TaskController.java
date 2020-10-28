package com.convoy.controller;

import com.convoy.feign.AdminFeign;
import com.convoy.model.*;
import com.convoy.model.Response.*;
import com.convoy.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhaoone on 2019/10/21
 **/
@RestController
@RequestMapping(path = "/tasks")
@EnableAutoConfiguration
@Api(tags = "Task", description = "任务相关操作")

/*
*
*/
public class TaskController {
    private final TaskService taskService;
    private final AdminFeign adminFeign;
    private final ConvoyService convoyService;
    private final CarService carService;

    @Autowired
    public TaskController(TaskService taskService,
                          AdminFeign adminFeign,  ConvoyService convoyService,
                          CarService carService) {
        this.taskService = taskService;
        this.adminFeign = adminFeign;
        this.convoyService = convoyService;
        this.carService = carService;
    }

    @ApiOperation(value = "获取全部任务")
    @RequestMapping(path = "/getAllEscortData", method = RequestMethod.GET)
    public HttpResponseContent getAllTasks(@RequestHeader(value="token") String token) throws Exception{
        String userId = adminFeign.getUserIdFromToken(token);
        List<EscortDataResponse> escortDataResponses = taskService.getAllTasks();

        HttpResponseContent response = new HttpResponseContent();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(escortDataResponses);
        return response;
    }

    /*@ApiOperation(value = "获取数据库状态")
    @RequestMapping(path = "/getDataBaseStatus", method = RequestMethod.GET)
    public HttpResponseContent getDataBaseStatus(){
        HttpResponseContent response = new HttpResponseContent();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(taskService.getDataBaseStatus());
        return response;
    }*/

    /*
    @ApiOperation(value = "获取警察的任务信息（手持机）")
    @RequestMapping(path = "/deviceGetTasks", method = RequestMethod.GET)
    public Task getByUserId(@RequestParam("userName") String userName, @RequestHeader(value="token") String token) {
        String userId = userService.getByUserName(userName).getUserId();
        String taskNo = convoyService.getConvoyByUserId(userId).getTaskNo();
        Task task = taskService.getTask(taskNo);
        return task;
    }


    @ApiOperation(value = "获取警察绑定犯人信息（一体机）")
    @RequestMapping(path = "/getByUser", method = RequestMethod.GET)
    public Prisoner getPrisonerId(@RequestParam("userId") String userId, @RequestHeader(value="token") String token) throws Exception{
        String user_Id = adminFeign.getUserIdFromToken(token);
        String prisonerId = convoyService.getConvoyByUserId(userId).getPrisonerId();
        Prisoner prisoner = prisonerService.getByPrisonerId(prisonerId);
        return prisoner;
    }
    */


    @ApiOperation(value = "获取视频")
    @RequestMapping(path = "/getVideoUrl", method = RequestMethod.GET)
    public HttpResponseContent getVideoUrl(@RequestHeader(value="token") String token) throws Exception {
        String user_Id = adminFeign.getUserIdFromToken(token);
        HttpResponseContent response = new HttpResponseContent();
        VideoUrlResponse videoUrlResponses = taskService.getVideoUrl();

        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(videoUrlResponses);
        return response;
    }

    @ApiOperation(value = "押解任务导入")
    @RequestMapping(path = "/inputTasks", method = RequestMethod.GET)
    public HttpResponseContent inputTasks() throws Exception {
        taskService.inputTasks();
        return null;
    }

    @ApiOperation(value = "判断有无押解数据")
    @RequestMapping(path = "/hasData", method = RequestMethod.GET)
    public HttpResponseContent hasData(){
        System.out.println("hasData");
        HttpResponseContent response = new HttpResponseContent();

        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());

        response.setData(taskService.getDataBaseStatus());

        return response;
    }

    @ApiOperation(value = "获取路线信息")
    @RequestMapping(path = "/getCarRoute", method = RequestMethod.GET)
    public HttpResponseContent getAllRoute(@RequestHeader(value="token") String token) throws Exception{
        HttpResponseContent response = new HttpResponseContent();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(taskService.getAllRoute());
        return response;
    }

    @ApiOperation(value = "（前端轨迹展示）获取全部GPS数据")
    @RequestMapping(path = "/getCarGps", method = RequestMethod.GET)
    public HttpResponseContent getAllGps2(@RequestHeader(value="token") String token) throws Exception{
        String userId = adminFeign.getUserIdFromToken(token);
        List<CarGpsResponse> carGpsResponses = carService.getCarGps();
        HttpResponseContent response = new HttpResponseContent();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(carGpsResponses);
        return response;
    }

    @ApiOperation(value = "（云中心）获取全部GPS数据")
    @RequestMapping(path = "/getGPS", method = RequestMethod.GET)
    public HttpResponseContent getAllGps(){
        List<CarGpsResponse> carGpsResponses = carService.getCarGps();
        HttpResponseContent response = new HttpResponseContent();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(carGpsResponses);
        return response;
    }

    @ApiOperation(value = "获取起点坐标")
    @RequestMapping(path = "/getStartPoint", method = RequestMethod.GET)
    public HttpResponseContent getStartPoint(@RequestHeader(value="token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        HttpResponseContent response = new HttpResponseContent();
        String[] res = taskService.getStartPoint();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(res);
        return response;
    }

}
