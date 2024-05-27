package com.brasens.dtos;

import java.time.ZonedDateTime;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brasens.response.DataResponse;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name = "data")
public class Data {
    
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", nullable = false, unique = true)
    private UUID id;
    
    @Column(name = "rms_x")
    private Double rmsX;
    
    @Column(name = "rms_y")
    private Double rmsY;
    
    @Column(name = "rms_z")
    private Double rmsZ;
    
    @Column(name = "temperature")
    private Double temperature;
    
    @Column(name = "asset_key", length = 50)
    private String key;
    
    @Column(name = "is_analysed")
    private boolean isAnalysed;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added")
    private ZonedDateTime added = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "id_asset", nullable = false)
	@JsonIgnore
	private Asset asset;

    public Data(double rmsX, double rmsY, double rmsZ, double temperature) {
        this.rmsX = rmsX;
        this.rmsY = rmsY;
        this.rmsZ = rmsZ;
        this.temperature = temperature;
    }

    public DataResponse getDataResponse() {
        return new DataResponse(rmsX, rmsY, rmsZ,
    			temperature, isAnalysed, added, key);
    }

    @Override
    public String toString() {
        return "Data{" +
                "rmsX=" + rmsX +
                ", rmsY=" + rmsY +
                ", rmsZ=" + rmsZ +
                ", temperature=" + temperature +
                ", key='" + key + '\'' +
                ", added=" + added +
                '}';
    }

    public Data() {
    }

    public Data(UUID id, Double rmsX, Double rmsY, Double rmsZ, Double temperature, String key, boolean isAnalysed, ZonedDateTime added, Asset asset) {
        this.id = id;
        this.rmsX = rmsX;
        this.rmsY = rmsY;
        this.rmsZ = rmsZ;
        this.temperature = temperature;
        this.key = key;
        this.isAnalysed = isAnalysed;
        this.added = added;
        this.asset = asset;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getRmsX() {
        return rmsX;
    }

    public void setRmsX(Double rmsX) {
        this.rmsX = rmsX;
    }

    public Double getRmsY() {
        return rmsY;
    }

    public void setRmsY(Double rmsY) {
        this.rmsY = rmsY;
    }

    public Double getRmsZ() {
        return rmsZ;
    }

    public void setRmsZ(Double rmsZ) {
        this.rmsZ = rmsZ;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
