package com.convoy.model.Response;

import com.convoy.model.Car;
import com.convoy.model.VideoAnomaly;

public class CarAndVideoAnomaly {
    private Car car;

    private VideoAnomaly videoAnomaly;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public VideoAnomaly getVideoAnomaly() {
        return videoAnomaly;
    }

    public void setVideoAnomaly(VideoAnomaly videoAnomaly) {
        this.videoAnomaly = videoAnomaly;
    }
}
