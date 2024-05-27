package com.brasens.dtos;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="vibration_chart_info")
public class VibrationChart {
	
	@Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", nullable = false, unique = true)
	private UUID id;

    @Column(name = "asset_key")
    public String key;
    
    @Column(name = "data_array", columnDefinition = "double precision[]")
    @Type(type = "list-array")
    public List<java.lang.Double> data;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", nullable = false)
    private ZonedDateTime added = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asset")
    @JsonIgnore
	private Asset asset;

    public VibrationChart() {
    }

    public VibrationChart(UUID id, String key, List<Double> data, ZonedDateTime added, Asset asset) {
        this.id = id;
        this.key = key;
        this.data = data;
        this.added = added;
        this.asset = asset;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}