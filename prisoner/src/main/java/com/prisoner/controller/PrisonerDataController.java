package com.prisoner.controller;

import com.ecs.model.*;
import com.ecs.model.Response.PrisonerData;
import com.ecs.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhaoone on 2019/10/14
 **/
@RestController
@RequestMapping(path = "/prisonerData")
@EnableAutoConfiguration
@Api(tags = "PrisonerData", description = "查询犯人生理数据以及风险值")
public class PrisonerDataController {
    private final RiskAssessmentService riskAssessmentService;
    //private final BracketConnectivityService bracketConnectivityService;
    private final PrisonerDataService prisonerDataService;
    private final PrisonerService prisonerService;
    private final UserService userService;
    private final TaskService taskService;
    private final CarService carService;
    private final CloudService cloudService;
    private final AdminService adminService;
    private final VervelService vervelService;
    private final ConvoyService convoyService;
    private final DeviceService deviceService;

    @Autowired
    public PrisonerDataController(RiskAssessmentService riskAssessmentService,
                                  PrisonerDataService prisonerDataService, PrisonerService prisonerService,
                                  UserService userService, TaskService taskService, CarService carService, CloudService cloudService,
                                  AdminService adminService, VervelService vervelService, ConvoyService convoyService,
                                  DeviceService deviceService) {
        this.riskAssessmentService = riskAssessmentService;
        this.prisonerDataService = prisonerDataService;
        this.prisonerService = prisonerService;
        this.userService = userService;
        this.taskService = taskService;
        this.carService = carService;
        this.cloudService = cloudService;
        this.adminService = adminService;
        this.vervelService = vervelService;
        this.convoyService = convoyService;
        this.deviceService = deviceService;
    }

    @ApiOperation(value = "手持机上传")
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "prisonerId") String prisonerId,
                         @RequestParam(value = "heartbeat") String heartbeat,
                         @RequestParam("height") String height,
                         @RequestHeader(value = "token") String token) throws Exception {
        String userId = adminService.getUserIdFromToken(token);

        prisonerDataService.uploadHeartbeat(prisonerId, heartbeat);
        cloudService.physiologyData(prisonerId, heartbeat, height);
        return "上传成功";
    }


    /*@ApiOperation(value = "获取所有犯人心率")
    @RequestMapping(path = "/getAllHeartBeat", method = RequestMethod.GET)
    public List<PrisonerHeartBeat> prisonerAllHeartBeat(@RequestHeader(value="token") String token) throws Exception {
        String userId = adminService.getUserIdFromToken(token);
        List<Prisoner> prisoners = prisonerService.getAllPrisoners();
        List<PrisonerHeartBeat> prisonerHeartBeats = new ArrayList<>();
        for(int i = 0; i < prisoners.size(); i++){
                PrisonerHeartBeat prisonerHeartBeat = prisonerDataService.getLastestHeartbeat(prisoners.get(i).getPrisonerId());
                //String risk_level = riskAssessmentService.prisonerRisk(prisoners.get(i).getPrisonerId());
                prisonerHeartBeats.add(prisonerHeartBeat);
        }
        return prisonerHeartBeats;
    }*/

    /*@ApiOperation(value = "获取所有犯人风险预警值")
    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public List<PrisonerRisk> prisonerAllRisk(@RequestHeader(value="token") String token) throws Exception {
        String userId = adminService.getUserIdFromToken(token);

        List<Prisoner> prisoners = prisonerService.getAllPrisoners();
        List<PrisonerRisk> prisonerRisks = new ArrayList<>();
        for(int i = 0; i < prisoners.size(); i++){
            PrisonerRisk prisonerRisk = riskAssessmentService.prisonerRisk(prisoners.get(i).getPrisonerId());
            //PrisonerRisk prisonerRisk = prisonerDataService.getLatestRisk(prisoners.get(i).getPrisonerId());
            prisonerRisks.add(prisonerRisk);
        }
        return prisonerRisks;
    }*/

    /*@ApiOperation(value = "获取单个犯人风险预警值")
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public PrisonerRisk prisonerData(@RequestParam(value = "PrisonerId") String PrisonerId, @RequestHeader(value="token") String token) throws Exception {
        String userId = adminService.getUserIdFromToken(token);
        return riskAssessmentService.prisonerRisk(PrisonerId);
        //return riskAssessmentService.getByPrisonerId(PrisonerId);
    }*/

    /*@ApiOperation(value = "获取单个视频的识别结果")
    @RequestMapping(path = "/getVideoType", method = RequestMethod.GET)
    public VideoAnomaly videoDetectionData(@RequestParam(value = "CarNo") String CarNo, @RequestHeader(value="token") String token) throws Exception {
        String userId = adminService.getUserIdFromToken(token);
        return riskAssessmentService.getByCarNo(CarNo);
    }*/

//    @ApiOperation(value = "获取单个视频的识别结果")
//    @RequestMapping(path = "/getVideoType", method = RequestMethod.GET)
//    public VideoDetection videoDetectionData(@RequestParam(value = "CarNo") String CarNo) throws IOException {
//        return riskAssessmentService.getByCarNo(CarNo);
//    }


    @ApiOperation(value = "获取单个犯人心率")
    @RequestMapping(path = "/getSinglePrisonerHeartbeat", method = RequestMethod.GET)
    public PrisonerHeartBeat singlePrisonerHeartbeat(@RequestParam(value = "PrisonerId") String PrisonerId, @RequestHeader(value="token") String token) throws Exception{
        String userId = adminService.getUserIdFromToken(token);
        return prisonerDataService.getLastestHeartbeat(PrisonerId);
    }

    @ApiOperation(value = "（云中心）获取全部犯人生理数据")
    @RequestMapping(path = "/getPrisonerData", method = RequestMethod.GET)
    public List<PrisonerData> getAll(){
        List<PrisonerData> prisonerDatas = new ArrayList<>();
        List<Prisoner> prisoners = prisonerService.getAll();
        for(int i = 0; i < prisoners.size(); i++){
            PrisonerHeartBeat prisonerHeartBeat = prisonerDataService.getLastestHeartbeat(prisoners.get(i).getPrisonerId());
            PrisonerRisk prisonerRisk = prisonerDataService.getLatestRisk(prisoners.get(i).getPrisonerId());

            PrisonerData prisonerData = new PrisonerData();
            prisonerData.setPrisonerId(prisoners.get(i).getPrisonerId());
            prisonerData.setAge(prisoners.get(i).getAge());
            prisonerData.setBodyFatRate(prisoners.get(i).getBodyFatRate());
            prisonerData.setGender(prisoners.get(i).getGender());
            prisonerData.setHeight(prisoners.get(i).getHeight());
            prisonerData.setWeight(prisoners.get(i).getWeight());
            prisonerData.setHeartBeat(prisonerHeartBeat.getHeartBeat());
            prisonerData.setCreateAt(prisonerHeartBeat.getCreateAt());

            prisonerDatas.add(prisonerData);
        }
        return prisonerDatas;
    }





    @ApiOperation(value = "犯人超出距离上报")
    @RequestMapping(path = "/outrange", method = RequestMethod.POST)
    public String outRange(@RequestParam("prisonerId") String prisonerId){
        prisonerDataService.uploadOutRange(prisonerId);
        return "上传成功";
    }

    /*@ApiOperation(value = "逃逸犯人GPS")
    @RequestMapping(path = "/escapedPrisonerGPS", method = RequestMethod.GET)
    public List<VervelGps> getEscapePrisonerGPS(@RequestHeader(value="token") String token){
        List<VervelGps>  vervelGps = vervelService.getLastest();
        return vervelGps;
    }*/
}
