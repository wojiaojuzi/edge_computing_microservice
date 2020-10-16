package com.prisoner.controller;

import com.prisoner.feign.AdminFeign;
import com.prisoner.model.PrisonerAnomaly;
import com.prisoner.model.Request.UpdateCommentRequest;
import com.prisoner.model.Response.AbnormalResponse;
import com.prisoner.model.Response.HttpResponseContent;
import com.prisoner.model.Response.ResponseEnum;
import com.prisoner.service.AbnormalConditionService;
import com.prisoner.service.PrisonerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Zhaoone on 2019/10/31
 **/

@RestController
@RequestMapping(path = "/exceptions")
@EnableAutoConfiguration
@Api(tags = "AbnormalCondition", description = "异常信息")
public class AbnormalConditionController {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final AbnormalConditionService abnormalConditionService;
    private final AdminFeign adminFeign;
    private final PrisonerService prisonerService;


    @Autowired
    public AbnormalConditionController(AbnormalConditionService abnormalConditionService, AdminFeign adminFeign,
                                       PrisonerService prisonerService) {
        this.abnormalConditionService = abnormalConditionService;
        this.adminFeign = adminFeign;
        this.prisonerService = prisonerService;
    }

    @ApiOperation(value = "全部异常")
    @RequestMapping(path = "/getAllExceptionData", method = RequestMethod.GET)
    public HttpResponseContent getAllExceptions(@RequestHeader(value="token") String token) throws Exception{
            String userId = adminFeign.getUserIdFromToken(token);
            HttpResponseContent response = new HttpResponseContent();
            List<AbnormalResponse> abnormalResponses = abnormalConditionService.getAllExceptions();

        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(abnormalResponses);
        return response;
    }

    @ApiOperation(value = "处理情况修改")
    @RequestMapping(path = "/changeState", method = RequestMethod.POST)
    public String updateDealState(@RequestParam("riskId") String riskId, @RequestHeader(value="token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        abnormalConditionService.updateDealState(riskId);
        return "处理完毕";
    }

    @ApiOperation(value = "误报处理")
    @RequestMapping(path = "/misdeclaration", method = RequestMethod.POST)
    public String updateMisdeclaration(@RequestParam("riskId") String riskId,@RequestHeader(value="token") String token) throws Exception {
        String userId = adminFeign.getUserIdFromToken(token);
        abnormalConditionService.updateMisdeclaration(riskId);
        return "处理完毕";
    }

    @ApiOperation(value = "详情")
    @RequestMapping(path = "/comment", method = RequestMethod.POST)
    public String updateComment(@RequestBody UpdateCommentRequest updateCommentRequest,@RequestHeader(value="token") String token){
        abnormalConditionService.updateComment(updateCommentRequest.getId(), updateCommentRequest.getComment());
        return "处理完毕";
    }

    @ApiOperation(value = "单个犯人异常（一体机）")
    @RequestMapping(path = "/getException", method = RequestMethod.GET)
    public List<AbnormalResponse> getExceptionByPrisoner(@RequestParam(value = "prisonerId") String prisonerId, @RequestHeader(value="token") String token) throws Exception{
        String userId = adminFeign.getUserIdFromToken(token);

        List<AbnormalResponse> abnormalResponses = new ArrayList<>();
        List<PrisonerAnomaly> prisonerAnomalies = abnormalConditionService.getPrisonerAnomalyByPrisonerId(prisonerId);
        String prisonerName = prisonerService.getPrisonerNameByPrisonerId(prisonerId);
        for(int i = 0;i < prisonerAnomalies.size(); i++){
            AbnormalResponse abnormalResponse = new AbnormalResponse();
            abnormalResponse.setCreateAt(prisonerAnomalies.get(i).getCreateAt());
            abnormalResponse.setDealState(prisonerAnomalies.get(i).isDealState());
            abnormalResponse.setExceptionLevel(prisonerAnomalies.get(i).getLevel());
            abnormalResponse.setExceptionType(prisonerAnomalies.get(i).getDescription());
            abnormalResponse.setComment(prisonerAnomalies.get(i).getComment());
            abnormalResponse.setMisdeclaration(prisonerAnomalies.get(i).isMisdeclaration());
            abnormalResponse.setPrisonerName(prisonerName);

            abnormalResponses.add(abnormalResponse);
        }

        return search(abnormalResponses);
    }
    public List<AbnormalResponse> search(List<AbnormalResponse> logsList) {
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Collections.sort(logsList, new Comparator<AbnormalResponse>() {
            @Override
            public int compare(AbnormalResponse o1, AbnormalResponse o2) {
                try {
                    if (mysqlSdf.parse(o1.getCreateAt()).getTime() > mysqlSdf.parse(o2.getCreateAt()).getTime()){
                        return -1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                try {
                    if (mysqlSdf.parse(o1.getCreateAt()).getTime() == mysqlSdf.parse(o2.getCreateAt()).getTime()){
                        return 0;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 1;
            }
        });
        return logsList;
    }
}
