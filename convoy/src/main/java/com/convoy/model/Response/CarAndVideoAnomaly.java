package com.convoy.model.Response;

import com.convoy.model.Camera;
import com.convoy.model.Car;
import com.convoy.model.VideoAnomaly;

import java.util.List;

public class CarAndVideoAnomaly {
    private Car car;

    private Camera camera;

    private List<VideoAnomaly> videoAnomalies;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<VideoAnomaly> getVideoAnomalies() {
        return videoAnomalies;
    }

    public void setVideoAnomalies(List<VideoAnomaly> videoAnomalies) {
        this.videoAnomalies = videoAnomalies;
    }































}
