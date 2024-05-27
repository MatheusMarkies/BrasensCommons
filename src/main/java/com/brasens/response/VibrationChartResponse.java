package com.brasens.response;

import java.time.ZonedDateTime;
import java.util.List;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

public class VibrationChartResponse {
    public List<java.lang.Double> data;
    private ZonedDateTime added = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());

    public VibrationChartResponse() {
    }

    public VibrationChartResponse(List<Double> data, ZonedDateTime added) {
        this.data = data;
        this.added = added;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public ZonedDateTime getAdded() {
        return added;
    }

    public void setAdded(ZonedDateTime added) {
        this.added = added;
    }
}
