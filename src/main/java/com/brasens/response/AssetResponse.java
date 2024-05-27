package com.brasens.response;

import com.brasens.dtos.enums.AssetState;
import com.brasens.dtos.enums.DowntimeType;
import com.brasens.dtos.enums.SensorState;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public class AssetResponse {
    private String name;
    private String key;
    private String battery;

    private String manufacturer;

    private String asset_location;
    
	private java.util.Date production_date;
	
	private double downtime;
    private DowntimeType downtime_type;
    
    private AssetState assetState;

    private SensorState sensorState;
    
    private ZonedDateTime created_at;

    public AssetResponse() {

    }

    public AssetResponse(String name, String key, String battery, String manufacturer, String asset_location, Date production_date, double downtime, DowntimeType downtime_type, AssetState assetState, SensorState sensorState, ZonedDateTime created_at) {
        this.name = name;
        this.key = key;
        this.battery = battery;
        this.manufacturer = manufacturer;
        this.asset_location = asset_location;
        this.production_date = production_date;
        this.downtime = downtime;
        this.downtime_type = downtime_type;
        this.assetState = assetState;
        this.sensorState = sensorState;
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAsset_location() {
        return asset_location;
    }

    public void setAsset_location(String asset_location) {
        this.asset_location = asset_location;
    }

    public Date getProduction_date() {
        return production_date;
    }

    public void setProduction_date(Date production_date) {
        this.production_date = production_date;
    }

    public double getDowntime() {
        return downtime;
    }

    public void setDowntime(double downtime) {
        this.downtime = downtime;
    }

    public DowntimeType getDowntime_type() {
        return downtime_type;
    }

    public void setDowntime_type(DowntimeType downtime_type) {
        this.downtime_type = downtime_type;
    }

    public AssetState getAssetState() {
        return assetState;
    }

    public void setAssetState(AssetState assetState) {
        this.assetState = assetState;
    }

    public SensorState getSensorState() {
        return sensorState;
    }

    public void setSensorState(SensorState sensorState) {
        this.sensorState = sensorState;
    }

    public ZonedDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(ZonedDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssetResponse)) return false;
        AssetResponse that = (AssetResponse) o;
        return Double.compare(getDowntime(), that.getDowntime()) == 0 && Objects.equals(getName(), that.getName()) && Objects.equals(getKey(), that.getKey()) && Objects.equals(getBattery(), that.getBattery()) && Objects.equals(getManufacturer(), that.getManufacturer()) && Objects.equals(getAsset_location(), that.getAsset_location()) && Objects.equals(getProduction_date(), that.getProduction_date()) && getDowntime_type() == that.getDowntime_type() && getAssetState() == that.getAssetState() && getSensorState() == that.getSensorState() && Objects.equals(getCreated_at(), that.getCreated_at());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getKey(), getBattery(), getManufacturer(), getAsset_location(), getProduction_date(), getDowntime(), getDowntime_type(), getAssetState(), getSensorState(), getCreated_at());
    }
}
