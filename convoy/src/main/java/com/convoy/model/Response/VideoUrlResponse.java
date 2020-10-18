package com.convoy.model.Response;

import com.convoy.model.CarInner;
import com.convoy.model.CarOuter;

import java.util.ArrayList;
import java.util.List;

public class VideoUrlResponse {
    private List<CarInner> carInner;

    private List<CarOuter> carOuter;

    public VideoUrlResponse() {
        this.carInner = new ArrayList<>();
        this.carOuter = new ArrayList<>();
    }

    public List<CarInner> getCarInner() {
        return carInner;
    }

    public void setCarInner(List<CarInner> carInner) {
        this.carInner = carInner;
    }

    public List<CarOuter> getCarOuter() {
        return carOuter;
    }

    public void setCarOuter(List<CarOuter> carOuter) {
        this.carOuter = carOuter;
    }
}
