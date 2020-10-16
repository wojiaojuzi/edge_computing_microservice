package com.device.service;

import com.device.mapper.CarMapper;
import com.device.mapper.ConvoyMapper;
import com.device.mapper.DeviceGpsMapper;
import com.device.mapper.DeviceMapper;
import com.device.model.Car;
import com.device.model.Convoy;
import com.device.model.DeviceGps;
import com.device.model.Response.CarGpsResponse;
import com.device.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.device.constant.Networks.*;

/**
 * Created by Zhaoone on 2019/12/19
 **/
@Service
public class CloudService {

    private final RestTemplate restTemplate;
    private final ConvoyMapper convoyMapper;
    private final CarMapper carMapper;
    private final DeviceMapper deviceMapper;
    private final DeviceGpsMapper deviceGpsMapper;

    @Autowired
    public CloudService(RestTemplate restTemplate,CarMapper carMapper,
                        DeviceMapper deviceMapper, ConvoyMapper convoyMapper, DeviceGpsMapper deviceGpsMapper) {
        this.restTemplate = restTemplate;
        this.convoyMapper = convoyMapper;
        this.carMapper = carMapper;
        this.deviceMapper = deviceMapper;
        this.deviceGpsMapper = deviceGpsMapper;
    }

    @Async
    public void GpsData(String userId,String deviceNo,String longitude,String latitude, String height){
        List<CarGpsResponse> carGpsResponses = new ArrayList<>();
        List<Car> cars = carMapper.getAll();
        for(int i = 0; i < cars.size(); i++){
            List<Convoy> convoys = convoyMapper.getByCarNo(cars.get(i).getCarNo());
            CarGpsResponse carGpsResponse = new CarGpsResponse();
            //List<DeviceGps> deviceGpsList = new ArrayList<>();
            carGpsResponse.setCarNo(cars.get(i).getCarNo());
            carGpsResponse.setCarType(cars.get(i).getType());
            carGpsResponse.setColor(cars.get(i).getColor());
            carGpsResponse.setTaskNo(convoys.get(0).getTaskNo());

            for(int j=0;j<convoys.size();j++){
                String user_id = convoys.get(j).getUserId();
                DeviceGps deviceGps = null;
                if(deviceMapper.getByUserId(user_id)!=null) {
                    String device_no = deviceMapper.getByUserId(user_id).getDeviceNo();
                    deviceGps = deviceGpsMapper.getByDeviceNo(device_no);
                    if(deviceGps!=null){
                        carGpsResponse.setHeight(deviceGps.getHeight());
                        carGpsResponse.setLongitude(deviceGps.getLongitude());
                        carGpsResponse.setLatitude(deviceGps.getLatitude());
                        break;
                    }
                }
            }


            carGpsResponses.add(carGpsResponse);
        }
        String data = JsonUtil.listToJson(carGpsResponses);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("data",data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String ,String>> request = new HttpEntity<MultiValueMap<String, String>>(map,headers);


        String msg= restTemplate.postForEntity("http://"+Cloud_IPADDRESS+"/api/v1/deploy",request,String.class).getBody();
        System.err.println("向云传输位置数据"+msg);
    }

//    public JSONObject escapeGPS(){
////        JSONObject msg = this.restTemplate.getForEntity("http://"+Cloud_IPADDRESS+"/api/v1/escape", JSONObject.class).getBody();
//        JSONObject msg;
//        return msg;
//    }

}
