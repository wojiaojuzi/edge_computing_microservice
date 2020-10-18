package com.convoy.service;

import com.convoy.mapper.*;
import com.convoy.model.*;
import com.convoy.model.Response.EscortDataResponse;
import com.convoy.model.Response.VideoUrlResponse;
import com.convoy.utils.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhaoone on 2019/10/21
 **/
@Service
public class TaskService {
    private final CarMapper carMapper;
    private final TaskMapper taskMapper;
    private final DeviceMapper deviceMapper;
    private final PrisonerMapper prisonerMapper;
    private final RouteMapper routeMapper;
    private final ConvoyMapper convoyMapper;
    private final UserMapper userMapper;

    @Autowired
    public TaskService(CarMapper carMapper, TaskMapper taskMapper, DeviceMapper deviceMapper,
                       PrisonerMapper prisonerMapper, RouteMapper routeMapper, ConvoyMapper convoyMapper,UserMapper userMapper) {
        this.carMapper = carMapper;
        this.taskMapper = taskMapper;
        this.deviceMapper = deviceMapper;
        this.prisonerMapper = prisonerMapper;
        this.routeMapper = routeMapper;
        this.convoyMapper = convoyMapper;
        this.userMapper = userMapper;
    }


    public VideoUrlResponse getVideoUrl(){
        List<Car> cars = carMapper.getAll();
        VideoUrlResponse videoUrlResponse = new VideoUrlResponse();
        for(int i=0;i<cars.size();i++){
            Car car = cars.get(i);
            if(!car.getType().equals("指挥车")) {
                CarInner carInner = new CarInner();
                CarOuter carOuter = new CarOuter();
                carInner.setCarNo(car.getCarNo());
                carInner.setCarInnerVideoUrl(car.getCarInnerVideoUrl());
                carOuter.setCarNo(car.getCarNo());
                carOuter.setCarOuterVideoUrl(car.getCarOuterVideoUrl());

                videoUrlResponse.getCarInner().add(carInner);
                videoUrlResponse.getCarOuter().add(carOuter);

                //videoUrlResponses.add(videoUrlResponse);
            }
        }
        return videoUrlResponse;
    }

    public List<EscortDataResponse> getAllTasks(){
        List<EscortDataResponse> escortDataResponses = new ArrayList<>();

        List<Car> cars = carMapper.getAll();
        for(int i =0;i<cars.size();i++) {
            EscortDataResponse escortDataResponse = new EscortDataResponse();
            String taskNo = convoyMapper.getTaskNoByCarNo(cars.get(i).getCarNo());
            List<String> userIds = convoyMapper.getUserIdByCarNo(cars.get(i).getCarNo());
            List<String> userNames = new ArrayList<>();
            for (int j = 0; j < userIds.size(); j++)
                userNames.add(userMapper.getByUserId(userIds.get(j)).getUserName());

            List<String> prisonerIds = convoyMapper.getPrisonerIdByCarNo(cars.get(i).getCarNo());
            System.out.println(prisonerIds);
            List<String> prisonerNames = new ArrayList<>();
            for (int j = 0; j < prisonerIds.size(); j++) {
                if(prisonerIds.get(i)!=null)
                    prisonerNames.add(prisonerMapper.getByPrisonerId(prisonerIds.get(j)).getPrisonerName());
            }
            escortDataResponse.setCarNo(cars.get(i).getCarNo());
            escortDataResponse.setCarType(cars.get(i).getType());
            escortDataResponse.setTaskNo(taskNo);
            escortDataResponse.setPoliceNames(userNames);
            escortDataResponse.setPrisonerNames(prisonerNames);

            escortDataResponses.add(escortDataResponse);
        }
        return escortDataResponses;
    }



    public List<Route> getAllRoute(){
        return routeMapper.getAllRoute();
    }

    public boolean getDataBaseStatus(){
        System.out.println(taskMapper.getSchemaStatus("edge_computing_service"));
        System.out.println(taskMapper.getTableStatus("task","edge_computing_service"));
        System.out.println(taskMapper.getTaskNumber());
        if(taskMapper.getSchemaStatus("edge_computing_service")==0)
            return false;
        else{
            if(taskMapper.getTableStatus("task","edge_computing_service")==0)
                return false;
            else{
                if(taskMapper.getTaskNumber()==0)
                    return false;
            }
        }


        return true;
    }

    public void inputTasks() throws SQLException, ClassNotFoundException {
        //File file1 = new File("/media/guoxidong/TEST/guoxidong/create_database.sql");
        //File file2 = new File("/media/guoxidong/TEST/guoxidong/register_task.sql");
        //File file3 = new File("/media/guoxidong/TEST/guoxidong/edge_computing_service_part.sql");
        File file1 = new File("F:/guoxidong/create_database.sql");
        File file2 = new File("F:/guoxidong/register_task.sql");
        File file3 = new File("F:/guoxidong/edge_computing_service_part.sql");
        //SqlUtil.mybatisExec2();
        SqlUtil.mybatisExec2(file1);
        SqlUtil.mybatisExec(file2);
        SqlUtil.mybatisExec(file3);
    }
}
