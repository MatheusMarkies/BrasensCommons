package com.brasens.response;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

public class FFTResponse {

    public String key;
    
    int resolution;
    int samples;

    public List<java.lang.Double> fftValues;
    public List<java.lang.Double> fftFreqs;
    private ZonedDateTime added = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());

    public FFTResponse() {
    }

    public FFTResponse(String key, int resolution, int samples, List<Double> fftValues, ZonedDateTime added) {
        this.key = key;
        this.resolution = resolution;
        this.samples = samples;
        this.fftValues = fftValues;
        this.added = added;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public List<Double> getFftValues() {
        return fftValues;
    }

    public void setFftValues(List<Double> fftValues) {
        this.fftValues = fftValues;
    }

    public ZonedDateTime getAdded() {
        return added;
    }

    public void setAdded(ZonedDateTime added) {
        this.added = added;
    }

    public List<Double> getFftFreqs() {
        return fftFreqs;
    }

    public void setFftFreqs(List<Double> fftFreqs) {
        this.fftFreqs = fftFreqs;
    }
}
