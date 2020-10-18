package com.device.controller;

import com.device.feign.AdminFeign;
import com.device.model.*;
import com.device.model.Exception.EdgeComputingServiceException;
import com.device.model.Request.DeviceRegisterRequest;
import com.device.model.Response.DeviceCloudGraphResponse;
import com.device.model.Response.DeviceResponse;
import com.device.model.Response.HttpResponseContent;
import com.device.model.Response.ResponseEnum;
import com.device.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/devices")
@EnableAutoConfiguration
@Api(tags = "Device", description = "设备相关的操作")
public class DeviceController {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final DeviceService deviceService;
    private final BraceletService braceletService;
    private final AdminFeign adminFeign;
    private final CloudService cloudService;
    private final VervelService vervelService;


    @Autowired
    public DeviceController(DeviceService deviceService, BraceletService braceletService,
                            AdminFeign adminFeign, CloudService cloudService, VervelService vervelService) {
        this.deviceService = deviceService;
        this.braceletService = braceletService;
        this.adminFeign = adminFeign;
        this.cloudService = cloudService;
        this.vervelService = vervelService;
    }

    @ApiOperation(value = "注册新设备")    //未修改关于手环、脚环的部分
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public HttpResponseContent createDevice(@RequestParam("deviceType") String deviceType,
                                            @RequestParam("deviceNo") String deviceNo,
                                            @RequestParam("userId") String userId) {
        HttpResponseContent response = new HttpResponseContent();
        DeviceRegisterRequest deviceRegisterRequest = new DeviceRegisterRequest(deviceType,
                userId, deviceNo);
        Device device = deviceService.createDevice(deviceRegisterRequest);
        if(device == null) {
            response.setCode(ResponseEnum.DEVICE_REGISTER_FAIL.getCode());
            response.setMessage(ResponseEnum.DEVICE_REGISTER_FAIL.getMessage());
        } else {
            response.setCode(ResponseEnum.SUCCESS.getCode());
            response.setMessage(ResponseEnum.SUCCESS.getMessage());
            response.setData(device);
        }
        return response;
    }

    @ApiOperation(value = "设备登入")
    @RequestMapping(path = "/login", method = RequestMethod.PUT)
    public HttpResponseContent deviceLogin(@RequestParam("deviceNo") String deviceNo) {
        HttpResponseContent response = new HttpResponseContent();
        deviceService.deviceLogin(deviceNo);
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        Device device = deviceService.getByDeviceNo(deviceNo);
        response.setData(device);

        return response;
    }

    @ApiOperation(value = "设备登出")
    @RequestMapping(path = "/logout", method = RequestMethod.PUT)
    public HttpResponseContent deviceLogout(@RequestParam("deviceNo") String deviceNo) {
        HttpResponseContent response = new HttpResponseContent();
        deviceService.deviceLogout(deviceNo);
        response.setCode(ResponseEnum.SUCCESS.getCode());
        Device device = deviceService.getByDeviceNo(deviceNo);
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(device);

        /*
         *   日志修改
         */
        //deviceLogsService.insertRecord(device,device.getDeviceType(), "设备登出");

        return response;
    }

    @ApiOperation(value = "设备注销")
    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public HttpResponseContent deleteDevice(@RequestParam("deviceNo") String deviceNo) {
        HttpResponseContent response = new HttpResponseContent();

        deviceService.deleteDevice(deviceNo);
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());

        return response;
    }

    /*
    *添加于2019/10/16
    */
    @ApiOperation(value = "判断设备是否存在")
    @RequestMapping(path = "/judgeDeviceNo", method = RequestMethod.GET)
    public boolean judgeDeviceNo(@RequestParam("deviceNo") String deviceNo) {
        Device device = deviceService.getByDeviceNo(deviceNo);
        if(device == null)
            return false;
        return true;
    }

    @ApiOperation(value = "修改手持机状态")
    @RequestMapping(path = "/updateDeviceStatus", method = RequestMethod.GET)
    public void getByDeviceNo(@RequestParam("deviceStatus") Boolean deviceStatus,
                              @RequestParam("DeviceNo") String DeviceNo,
                              @RequestParam("record") String record,
                              @RequestHeader("token") String token) throws Exception{
        String userId = adminFeign.getUserIdFromToken(token);
        deviceService.updateDeviceConnectivityStatusByDeviceNo(deviceStatus, record, DeviceNo);
    }


    @ApiOperation(value = "修改手环连接设备")
    @RequestMapping(path = "/updateBraceletConnectivityStatus", method = RequestMethod.POST)
    public String updateBraceletConnectivityStatus(@RequestHeader(value="token")String token, @RequestParam("deviceNo") String deviceNo,
                                                   @RequestParam("braceletNo") String braceletNo) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        if(braceletService.updateBraceletConnectivityStatus(deviceNo,braceletNo))
            return "修改成功";
        return "修改失败";
    }
    @ApiOperation(value = "修改脚环连接设备")
    @RequestMapping(path = "/updateVervelConnectivityStatus", method = RequestMethod.POST)
    public String updateVervelConnectivityStatus(@RequestHeader(value="token")String token, @RequestParam("deviceNo") String deviceNo,
                                                 @RequestParam("vervelNo") String vervelNo) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        if(vervelService.updateVervelConnectivityStatus(deviceNo,vervelNo))
            return "修改成功";
        return "修改失败";
    }

    @ApiOperation(value = "一体机位置上传")
    @RequestMapping(path = "/upload2", method = RequestMethod.POST)
    public String uploadByPad(@RequestParam(value = "deviceNo") String deviceNo,
                              @RequestParam(value = "longitude") String longitude, @RequestParam(value = "latitude") String latitude,
                              @RequestParam("height") String height, @RequestHeader("token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        //String carNo = taskService.getTaskByUserName((userService.getByUserId(userId).getUserName())).getCarNo();
        deviceService.uploadDeviceGps(deviceNo, longitude, latitude, height);
        cloudService.GpsData(userId,deviceNo,longitude, latitude, height);
        return "上传成功";
    }

    @ApiOperation(value = "上传设备信息")
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadByDeviceNo(@RequestParam("deviceNo") String deviceNo,
                                   @RequestParam("cpuUsageRate") String cpuUsageRate,
                                   @RequestParam("memoryUsageRate") String memoryUsageRate,
                                   @RequestParam("dumpEnergyRate") String dumpEnergyRate,
                                   @RequestHeader("token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date time = new Date();
        String createAt = mysqlSdf.format(time);
        DeviceState deviceState = new DeviceState();
        deviceState.setDeviceNo(deviceNo);
        deviceState.setCpuUsageRate(cpuUsageRate);
        deviceState.setDumpEnergyRate(dumpEnergyRate);
        deviceState.setMemoryUsageRate(memoryUsageRate);
        deviceState.setCreateAt(createAt);
        deviceService.createDeviceState(deviceState);
        return "上传成功";
    }

    @ApiOperation(value = "获取最新的设备信息")
    @RequestMapping(path = "/latestRunInfo", method = RequestMethod.GET)
    public DeviceState getDeviceStateByDeviceNo(@RequestParam("deviceNo") String deviceNo, @RequestHeader(value="token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        return deviceService.getDeviceStateBydeviceNo(deviceNo);
    }

    @ApiOperation(value = "获取全部设备信息")
    @RequestMapping(path = "/getAllRunInfo", method = RequestMethod.GET)
    public List<DeviceState> getAllDeviceState(@RequestHeader(value="token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        List<Device> devices = deviceService.getAll();
        List<DeviceState> deviceStateList = new ArrayList<>();
        for(int i = 0; i < devices.size(); i++){
            deviceStateList.add(deviceService.getDeviceStateBydeviceNo(devices.get(i).getDeviceNo()));
        }
        return deviceStateList;
    }

    /*@ApiOperation(value = "判断手环是否绑定犯人")
    @RequestMapping(path = "/braceletBind", method = RequestMethod.GET)
    public Boolean braceletBind(@RequestParam("braceletNo") String braceletNo){
        if(braceletService.getPrisonerIdByBraceletNo(braceletNo) == null)
            return false;
        else return true;
    }

     */

    @ApiOperation(value = "获取设备云图")
    @RequestMapping(path = "/getDeviceCloudGraphData", method = RequestMethod.GET)
    public HttpResponseContent getDeviceCloudGraph(@RequestHeader(value="token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        HttpResponseContent response = new HttpResponseContent();
        DeviceCloudGraphResponse deviceCloudGraphResponse = deviceService.getDeviceCloudGraph();

        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(deviceCloudGraphResponse);

        return response;
    }

    @ApiOperation(value = "检测服务器是否可连")
    @RequestMapping(path = "/isConnectivity", method = RequestMethod.GET)
    public String isConnectivity(){
        return "true";
    }


}