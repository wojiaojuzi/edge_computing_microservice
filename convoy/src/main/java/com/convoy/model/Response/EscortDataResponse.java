package com.convoy.model.Response;

import java.util.List;

public class EscortDataResponse {
    private String taskNo;

    private String carType;

    private String carNo;

    private List<String> prisonerNames;

    private List<String> policeNames;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public List<String> getPrisonerNames() {
        return prisonerNames;
    }

    public void setPrisonerNames(List<String> prisonerNames) {
        this.prisonerNames = prisonerNames;
    }

    public List<String> getPoliceNames() {
        return policeNames;
    }

    public void setPoliceNames(List<String> policeNames) {
        this.policeNames = policeNames;
    }
}
