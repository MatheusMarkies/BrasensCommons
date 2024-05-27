package com.brasens.dtos;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="fft")
public class FFTModel {
	
	@Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", nullable = false, unique = true)
	private UUID id;

    @Column(name = "asset_key")
    public String key;

    @Column(name = "resolution")
    int resolution;
    
    @Column(name = "samples")
    int samples;

    @OneToMany(targetEntity = Vector.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_values")
    @JsonIgnore
    private List<Vector> fftValues = new ArrayList<>();
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", nullable = false)
    private ZonedDateTime added = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asset")
    @JsonIgnore
	private Asset asset;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fftcomponents_id")
    @JsonIgnore
    private List<FFTComponents> clusteringHistory = new ArrayList<>();

    public FFTModel() {
    }

    public FFTModel(UUID id, String key, int resolution, int samples, List<Vector> fftValues, ZonedDateTime added, Asset asset, List<FFTComponents> clusteringHistory) {
        this.id = id;
        this.key = key;
        this.resolution = resolution;
        this.samples = samples;
        this.fftValues = fftValues;
        this.added = added;
        this.asset = asset;
        this.clusteringHistory = clusteringHistory;
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

    public List<Vector> getFftValues() {
        return fftValues;
    }

    public void setFftValues(List<Vector> fftValues) {
        this.fftValues = fftValues;
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

    public List<FFTComponents> getClusteringHistory() {
        return clusteringHistory;
    }

    public void setClusteringHistory(List<FFTComponents> clusteringHistory) {
        this.clusteringHistory = clusteringHistory;
    }
}
