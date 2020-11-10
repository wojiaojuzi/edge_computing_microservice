package com.convoy.controller;

import com.convoy.model.Response.CarAndVideoAnomaly;
import com.convoy.model.Response.CarCameraResponse;
import com.convoy.model.Response.HttpResponseContent;
import com.convoy.model.Response.ResponseEnum;
import com.convoy.model.VideoAnomaly;
import com.convoy.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/cars")
@EnableAutoConfiguration
@Api(tags = "Car", description = "车辆相关操作")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @ApiOperation(value = "获取车辆摄像头信息")
    @RequestMapping(path="/getCamera",method = RequestMethod.GET)
    public HttpResponseContent getCamera(){
        HttpResponseContent response = new HttpResponseContent();
        List<CarCameraResponse> carCameraResponseList = carService.getCamera();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());

        response.setData(carCameraResponseList);
        return response;
    }

    @ApiOperation(value = "获取车辆摄像头以及其他信息")
    @RequestMapping(path="/getCarInfoAndVideoAnomaly",method = RequestMethod.GET)
    public HttpResponseContent getCarInfoAndVideoAnomaly(){
        HttpResponseContent response = new HttpResponseContent();

        List<CarAndVideoAnomaly> carCameraResponseList = carService.getCarAndVideoAnomaly();

        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());

        response.setData(carCameraResponseList);
        return response;
    }

    @ApiOperation(value = "获取视频异常事件")
    @RequestMapping(path="/getVideoAnomaly",method = RequestMethod.GET)
    public HttpResponseContent getVideoAnomaly(@RequestParam("id") String id,@RequestParam("carNo") String carNo){
        HttpResponseContent response = new HttpResponseContent();

        List<VideoAnomaly> res = carService.getSomeVideoAnomaly(id,carNo);

        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());

        response.setData(res);
        return response;
    }
}
