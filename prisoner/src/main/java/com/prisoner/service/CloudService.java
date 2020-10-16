package com.prisoner.service;

import com.prisoner.mapper.ConvoyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import static com.prisoner.constant.Networks.*;

/**
 * Created by Zhaoone on 2019/12/19
 **/
@Service
public class CloudService {

    private final PrisonerService prisonerService;
    private final RestTemplate restTemplate;
    private final ConvoyMapper convoyMapper;

    @Autowired
    public CloudService(PrisonerService prisonerService, RestTemplate restTemplate,
                        ConvoyMapper convoyMapper) {
        this.prisonerService = prisonerService;
        this.restTemplate = restTemplate;
        this.convoyMapper = convoyMapper;
    }



    @Async
    public void physiologyData(String prisonerId, String heartbeat, String height){

        String prisonerName = prisonerService.getPrisonerNameByPrisonerId(prisonerId);
        String taskNo = convoyMapper.getTaskNoByPrisonerId(prisonerName);
        //String taskNo = taskService.getPrisonerCar(prisonerName).getTaskNo();                 //taskId = taskNo
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("prisonerId", prisonerId);
        map.add("taskId", taskNo);
        map.add("prisonerName", prisonerName);
        map.add("heartbeat", heartbeat);
        map.add("height", height);
        map.add("posture", "");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String ,String>> request = new HttpEntity<MultiValueMap<String, String>>(map,headers);

        String msg = this.restTemplate.postForEntity("http://"+Cloud_IPADDRESS+"/api/v1/prisoner", request, String.class).getBody();
        System.err.println("向云传输生理数据"+msg);
    }


}
