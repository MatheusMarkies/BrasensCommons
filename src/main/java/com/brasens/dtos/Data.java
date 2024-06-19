package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="Data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "acceleration_rms_x")
    double accelerationRMS_X;
    @Column(name = "acceleration_rms_y")
    double accelerationRMS_Y;
    @Column(name = "acceleration_rms_z")
    double accelerationRMS_Z;

    @Column(name = "speed_rms_x")
    double speedRMS_X;
    @Column(name = "speed_rms_y")
    double speedRMS_Y;
    @Column(name = "speed_rms_z")
    double speedRMS_Z;

    @Column(name = "temperature")
    double temperature;
    @Column(name = "battery")
    double battery;

    @Column(name = "asset_key", unique = true)
    private String key;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", insertable = false, updatable = false)
    private ZonedDateTime added = ZonedDateTime.now().withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonIgnore
    private Asset asset;
}
