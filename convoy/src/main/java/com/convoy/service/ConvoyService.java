package com.convoy.service;


import com.convoy.mapper.ConvoyMapper;
import com.convoy.model.Convoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvoyService {

    private final ConvoyMapper convoyMapper;

    @Autowired
    public ConvoyService(ConvoyMapper convoyMapper) {
        this.convoyMapper = convoyMapper;
    }

    public Convoy getConvoyByUserId(String userId){
        return convoyMapper.getConvoyByUserId(userId);
    }

    public List<Convoy> getNoRepeat(String carNo){
        return convoyMapper.getNoRepeat(carNo);
    }

    public List<Convoy> getByCarNo(String carNo){
        return convoyMapper.getByCarNo(carNo);
    }

    public List<String> getCarNoByTaskNo(String taskNo){
        return convoyMapper.getCarNoByTaskNo(taskNo);
    }

    public String getTaskNoByCarNo(String carNo){
        return convoyMapper.getTaskNoByCarNo(carNo);
    }

    public List<String> getUserIdByCarNo(String carNo){
        return convoyMapper.getUserIdByCarNo(carNo);
    }

    public List<String> getPrisonerIdByCarNo(String carNo){
        return convoyMapper.getPrisonerIdByCarNo(carNo);
    }
}
