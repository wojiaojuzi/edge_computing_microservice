package com.prisoner.controller;

import com.prisoner.feign.AdminFeign;
import com.prisoner.model.*;
import com.prisoner.model.Response.PrisonerData;
import com.prisoner.service.*;
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
    //private final RiskAssessmentService riskAssessmentService;
    //private final BracketConnectivityService bracketConnectivityService;
    private final PrisonerDataService prisonerDataService;
    private final PrisonerService prisonerService;
    private final AdminFeign adminFeign;
    private final CloudService cloudService;

    @Autowired
    public PrisonerDataController(PrisonerDataService prisonerDataService, PrisonerService prisonerService,
                                  AdminFeign adminFeign, CloudService cloudService) {
        this.prisonerDataService = prisonerDataService;
        this.prisonerService = prisonerService;
        this.adminFeign = adminFeign;
        this.cloudService = cloudService;
    }

    @ApiOperation(value = "手持机上传")
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "prisonerId") String prisonerId,
                         @RequestParam(value = "heartbeat") String heartbeat,
                         @RequestParam("height") String height,
                         @RequestHeader(value = "token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);

        prisonerDataService.uploadHeartbeat(prisonerId, heartbeat);
        cloudService.physiologyData(prisonerId, heartbeat, height);
        return "上传成功";
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
    public String outRange(@RequestParam("prisonerId") String prisonerId,@RequestHeader(value="token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        prisonerDataService.uploadOutRange(prisonerId);
        return "上传成功";
    }

}
