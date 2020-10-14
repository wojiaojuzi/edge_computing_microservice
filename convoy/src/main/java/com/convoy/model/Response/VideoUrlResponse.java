package com.convoy.model.Response;

import com.convoy.model.CarInner;
import com.convoy.model.CarOuter;

public class VideoUrlResponse {
    private CarInner carInner;

    private CarOuter carOuter;

    public CarInner getCarInner() {
        return carInner;
    }

    public void setCarInner(CarInner carInner) {
        this.carInner = carInner;
    }

    public CarOuter getCarOuter() {
        return carOuter;
    }

    public void setCarOuter(CarOuter carOuter) {
        this.carOuter = carOuter;
    }
}
