package com.convoy.service;


import com.convoy.mapper.*;
import com.convoy.model.*;
import com.convoy.model.Response.CarAndVideoAnomaly;
import com.convoy.model.Response.CarCameraResponse;
import com.convoy.model.Response.CarGpsResponse;
import com.convoy.utils.ImitateCoor;
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
    private final VideoAnomalyMapper videoAnomalyMapper;
    private final CameraMapper cameraMapper;

    @Autowired
    public CarService(CarMapper carMapper, ConvoyMapper convoyMapper, DeviceMapper deviceMapper,
                      DeviceGpsMapper deviceGpsMapper,VideoAnomalyMapper videoAnomalyMapper,
                      CameraMapper cameraMapper) {
        this.carMapper = carMapper;
        this.convoyMapper = convoyMapper;
        this.deviceMapper = deviceMapper;
        this.deviceGpsMapper = deviceGpsMapper;
        this.videoAnomalyMapper = videoAnomalyMapper;
        this.cameraMapper = cameraMapper;
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

    public List<CarAndVideoAnomaly> getCarAndVideoAnomaly(){
        List<CarAndVideoAnomaly> res = new ArrayList<>();
        List<Car> cars = carMapper.getAll();
        for(int i=0; i<cars.size(); i++){
            Car car = cars.get(i);
            if(car.getType().equals("押解车")){
                List<VideoAnomaly> videoAnomalies = videoAnomalyMapper.getSomeByCarNo(car.getCarNo());
                Camera camera = cameraMapper.getCameraByCarNo(car.getCarNo());
                CarAndVideoAnomaly carAndVideoAnomaly = new CarAndVideoAnomaly();
                carAndVideoAnomaly.setCar(car);
                carAndVideoAnomaly.setVideoAnomalies(videoAnomalies);
                carAndVideoAnomaly.setCamera(camera);
                res.add(carAndVideoAnomaly);
            }
        }

        return res;
    }

    public List<VideoAnomaly> getSomeVideoAnomaly(String id,String carNo){
        return videoAnomalyMapper.getSomeByCarNoAndId(id,carNo);
    }

    public List<CarCameraResponse> getCamera(){
        return carMapper.getCarCamera("押解车");
    }

    public List<CarGpsResponse> getCarGps(){
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
            if(cars.get(i).getType().equals("押解车"))
                for(int j=0;j<convoys.size();j++){
                    String user_id = convoys.get(j).getUserId();
                    DeviceGps deviceGps = null;
                    if(deviceMapper.getByUserIdAndDeviceType(user_id,"一体化终端")!=null) {
                        String device_no = deviceMapper.getByUserIdAndDeviceType(user_id,"一体化终端").getDeviceNo();
                        deviceGps = deviceGpsMapper.getByDeviceNo(device_no);
                        if(deviceGps!=null){
                            carGpsResponse.setHeight(deviceGps.getHeight());
                            carGpsResponse.setLongitude(deviceGps.getLongitude());
                            carGpsResponse.setLatitude(deviceGps.getLatitude());
                            break;
                        }
                    }
                }
            else{
                carGpsResponse.setHeight(ImitateCoor.height);
                carGpsResponse.setLongitude(ImitateCoor.lon);
                carGpsResponse.setLatitude(ImitateCoor.lat);
            }


            carGpsResponses.add(carGpsResponse);
        }
        return carGpsResponses;
    }
}
