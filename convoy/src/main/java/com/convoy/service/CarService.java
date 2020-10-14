package com.convoy.service;


import com.convoy.mapper.CarMapper;
import com.convoy.mapper.ConvoyMapper;
import com.convoy.mapper.DeviceGpsMapper;
import com.convoy.mapper.DeviceMapper;
import com.convoy.model.Car;
import com.convoy.model.Convoy;
import com.convoy.model.DeviceGps;
import com.convoy.model.Response.CarGpsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarMapper carMapper;
    private final ConvoyMapper convoyMapper;
    private final DeviceMapper deviceMapper;
    private final DeviceGpsMapper deviceGpsMapper;

    @Autowired
    public CarService(CarMapper carMapper, ConvoyMapper convoyMapper, DeviceMapper deviceMapper, DeviceGpsMapper deviceGpsMapper) {
        this.carMapper = carMapper;
        this.convoyMapper = convoyMapper;
        this.deviceMapper = deviceMapper;
        this.deviceGpsMapper = deviceGpsMapper;
    }

    public List<Car> getAllCars(){
        return carMapper.getAll();
    }

    public Car getCarByCarNo(String carNo){
        return carMapper.getByCarNo(carNo);
    }

    public String getCommandCarNo(){
        return carMapper.getCommandCarNo();
    }

    public List<CarGpsResponse>getAllCarGps(){
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
        return carGpsResponses;
    }
}
