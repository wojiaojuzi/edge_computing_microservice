package com.prisoner.service;

import com.prisoner.mapper.PrisonerAnomalyMapper;
import com.prisoner.mapper.PrisonerHeartBeatMapper;
import com.prisoner.mapper.PrisonerRiskMapper;
import com.prisoner.model.PrisonerAnomaly;
import com.prisoner.model.PrisonerHeartBeat;
import com.prisoner.model.PrisonerRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhaoone on 2019/11/13
 **/
@Service
public class PrisonerDataService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final PrisonerHeartBeatMapper prisonerHeartBeatMapper;
    private final PrisonerRiskMapper prisonerRiskMapper;
    private final PrisonerAnomalyMapper prisonerAnomalyMapper;
    //private final RiskLevelRecordingMapper riskLevelRecordingMapper;

    @Autowired
    public PrisonerDataService(PrisonerHeartBeatMapper prisonerHeartBeatMapper,
                               PrisonerRiskMapper prisonerRiskMapper, PrisonerAnomalyMapper prisonerAnomalyMapper) {
        this.prisonerHeartBeatMapper = prisonerHeartBeatMapper;
        this.prisonerRiskMapper = prisonerRiskMapper;
        this.prisonerAnomalyMapper = prisonerAnomalyMapper;
    }

    public void uploadHeartbeat(String prisonerId, String heartbeat){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        String now = mysqlSdf.format(new Date());
        prisonerHeartBeatMapper.createHeartbeat(prisonerId, heartbeat, now);
    }

    public PrisonerHeartBeat getLastestHeartbeat(String prisonerId){
        return prisonerHeartBeatMapper.getLastestHeartbeatByPrisonerId(prisonerId);
    }

    public PrisonerRisk getLatestRisk(String prisonerId){
        return prisonerRiskMapper.getByPrisonerId(prisonerId);
    }

    public List<PrisonerAnomaly> getAllPrisonerAnomaly(){
        return prisonerAnomalyMapper.getAll();
    }



    public void uploadOutRange(String prisonerId){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        //Timestamp now = new Timestamp(new Date().getTime());
        String now = mysqlSdf.format(new Date());
        String riskId = prisonerRiskMapper.getByPrisonerId(prisonerId).getId();
        prisonerAnomalyMapper.createPrisonerAnomaly(riskId,now,"0",false,false,"犯人位置超距","");
    }
}
