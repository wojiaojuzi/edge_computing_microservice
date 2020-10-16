package com.prisoner.service;

import com.prisoner.mapper.*;
import com.prisoner.model.Prisoner;
import com.prisoner.model.PrisonerHeartBeat;
import com.prisoner.model.PrisonerRisk;
import com.prisoner.model.Response.PrisonerDataResponse;
import com.prisoner.model.Response.PrisonerRiskDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author: jojo
 * @Description:
 * @Date: Created on 2019/5/6 21:23
 */
@Service
public class PrisonerService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final PrisonerMapper prisonerMapper;
    private final PrisonerHeartBeatMapper prisonerHeartBeatMapper;
    private final PrisonerRiskMapper prisonerRiskMapper;

    @Autowired
    public PrisonerService(PrisonerMapper prisonerMapper,PrisonerHeartBeatMapper prisonerHeartBeatMapper, PrisonerRiskMapper prisonerRiskMapper) {
        this.prisonerMapper = prisonerMapper;
        this.prisonerHeartBeatMapper = prisonerHeartBeatMapper;
        this.prisonerRiskMapper = prisonerRiskMapper;
    }

    public List<PrisonerDataResponse> getAllPrisonerData(){
        List<PrisonerDataResponse> prisonerDataResponses = new ArrayList<>();
        List<Prisoner> prisoners = prisonerMapper.getAll();
        for(int i=0; i<prisoners.size();i++){
            String prisonerId = prisoners.get(i).getPrisonerId();
            String prisonerName = prisoners.get(i).getPrisonerName();
            String photoUrl = prisoners.get(i).getPrisonerPhotoUrl();
            PrisonerHeartBeat prisonerHeartBeat = prisonerHeartBeatMapper.getLastestHeartbeatByPrisonerId(prisonerId);
            PrisonerRisk prisonerRisk = prisonerRiskMapper.getByPrisonerId(prisonerId);
            PrisonerDataResponse prisonerDataResponse = new PrisonerDataResponse();

            prisonerDataResponse.setPrisonerId(prisonerId);
            prisonerDataResponse.setPrisonerName(prisonerName);
            prisonerDataResponse.setPhoteUrl(photoUrl);
            prisonerDataResponse.setHeartBeat(prisonerHeartBeat.getHeartBeat());
            prisonerDataResponse.setRiskValue(prisonerRisk.getRiskValue());
            prisonerDataResponses.add(prisonerDataResponse);
        }
        return prisonerDataResponses;
    }

    public List<PrisonerRiskDataResponse> getPrisonerRiskValue(String prisonerId){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date now = new Date();
        String createAt = mysqlSdf.format(now);
        List<PrisonerRiskDataResponse> prisonerRiskDataResponses = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<10;i++) {
            PrisonerRiskDataResponse prisonerRiskDataResponse = new PrisonerRiskDataResponse();
            PrisonerRisk prisonerRisk = prisonerRiskMapper.getByPrisonerId(prisonerId);
            String riskValue = null;
            if (prisonerRisk != null)
                riskValue = prisonerRisk.getRiskValue();
            prisonerRiskDataResponse.setPrisonerId(prisonerId);

            int riskValueOfEnvironment = random.nextInt(100);
            int riskValueOfVideo = random.nextInt(100);
            int TotalRiskValue = random.nextInt(100);
            prisonerRiskDataResponse.setRiskValueOfEnvironment(String.valueOf(riskValueOfEnvironment));
            prisonerRiskDataResponse.setRiskValueOfVideo(String.valueOf(riskValueOfVideo));
            prisonerRiskDataResponse.setTotalRiskValue(String.valueOf(TotalRiskValue));

            Date date = new Date(now.getTime()-9000+i*1000);
            prisonerRiskDataResponse.setCreateAt(new Timestamp(date.getTime()));

            prisonerRiskDataResponses.add(prisonerRiskDataResponse);
        }

        return prisonerRiskDataResponses;
    }


    public List<Prisoner> getAll() {
        return prisonerMapper.getAll();
    }

    public double[] getFeatureByPrisonerId(String prisonerId){
        String feature = prisonerMapper.getFeatureByPrisonerId(prisonerId);
        String[] features = feature.split(",");
        double[] array = new double[14];
        for(int i = 0; i < features.length; i++){
            array[i] = Double.valueOf(features[i]);
        }
        return array;
    }

    public String getPrisonerNameByPrisonerId(String priosnerId){
        return prisonerMapper.getByPrisonerId(priosnerId).getPrisonerName();
    }

    public String getPrisonerFeatureByPrisonerId(String prisonerId){
        return prisonerMapper.getFeatureByPrisonerId(prisonerId);
    }

    public Prisoner getByPrisonerId(String prisonerId){
        return prisonerMapper.getByPrisonerId(prisonerId);
    }

    public Prisoner getByPrisonerName(String prisonerName){
        return prisonerMapper.getByPrisonerName(prisonerName);
    }

    public List<Prisoner> getAllPrisoners(){
        return prisonerMapper.getAll();
    }


}
