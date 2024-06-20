package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="Vibration_Sensor_Reading")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VibrationSensorReading {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "temporal_array", columnDefinition = "double precision[]")
    @Type(type = "list-array")
    public List<Double> temporalValues;

    @Column(name = "data_array", columnDefinition = "double precision[]")
    @Type(type = "list-array")
    public List<Double> data;

    @Column(name = "asset_key", unique = true)
    private String key;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", insertable = false, updatable = false)
    private ZonedDateTime added = ZonedDateTime.now().withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());

    //@OneToOne(mappedBy = "Vibration_Sensor_Reading", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //@JsonIgnore
    //private VibrationSensorReadingStatisticalValues statisticalValues;

    @OneToOne(mappedBy = "Vibration_Sensor_Reading", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private VibrationSensorReadingStatisticalValues statisticalValues;

    @OneToOne(mappedBy = "Vibration_Sensor_Reading", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Distribution distribution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    @JsonIgnore
    private History history;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    @JsonIgnore
    private Asset asset;
}
