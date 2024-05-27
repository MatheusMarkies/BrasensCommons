package com.brasens.response;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataResponse {
	
    private double rms_x;
    private double rms_y;
    private double rms_z;

    private double temperature;
    private boolean isAnalysed;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
	private ZonedDateTime added;

    private String key;

    public DataResponse() {
    }

    public DataResponse(double rms_x, double rms_y, double rms_z, double temperature, boolean isAnalysed, ZonedDateTime added, String key) {
        this.rms_x = rms_x;
        this.rms_y = rms_y;
        this.rms_z = rms_z;
        this.temperature = temperature;
        this.isAnalysed = isAnalysed;
        this.added = added;
        this.key = key;
    }

    public double getRms_x() {
        return rms_x;
    }

    public void setRms_x(double rms_x) {
        this.rms_x = rms_x;
    }

    public double getRms_y() {
        return rms_y;
    }

    public void setRms_y(double rms_y) {
        this.rms_y = rms_y;
    }

    public double getRms_z() {
        return rms_z;
    }

    public void setRms_z(double rms_z) {
        this.rms_z = rms_z;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isAnalysed() {
        return isAnalysed;
    }

    public void setAnalysed(boolean analysed) {
        isAnalysed = analysed;
    }

    public ZonedDateTime getAdded() {
        return added;
    }

    public void setAdded(ZonedDateTime added) {
        this.added = added;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
