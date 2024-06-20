package com.brasens.dtos;

import com.brasens.dtos.Asset;
import com.brasens.dtos.Downtime;
import com.brasens.dtos.FFT;
import com.brasens.dtos.VibrationSensorReading;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class History {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "asset_key", unique = true)
    private String key;

    @OneToMany(targetEntity = FFT.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FFT> fftAcceleration_X = new ArrayList<>();

    @OneToMany(targetEntity = FFT.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FFT> fftAcceleration_Y = new ArrayList<>();

    @OneToMany(targetEntity = FFT.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FFT> fftAcceleration_Z = new ArrayList<>();

    @OneToMany(targetEntity = FFT.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FFT> fftSpeeds_X = new ArrayList<>();

    @OneToMany(targetEntity = FFT.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FFT> fftSpeeds_Y = new ArrayList<>();

    @OneToMany(targetEntity = FFT.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FFT> fftSpeeds_Z = new ArrayList<>();

    @OneToMany(targetEntity = VibrationSensorReading.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VibrationSensorReading> sensorReadings_X = new ArrayList<>();

    @OneToMany(targetEntity = VibrationSensorReading.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VibrationSensorReading> sensorReadings_Y = new ArrayList<>();

    @OneToMany(targetEntity = VibrationSensorReading.class, mappedBy = "History", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VibrationSensorReading> sensorReadings_Z = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    @JsonIgnore
    private Asset asset;
}
