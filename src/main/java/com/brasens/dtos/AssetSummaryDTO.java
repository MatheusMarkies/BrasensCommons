package com.brasens.dtos;

import com.brasens.dtos.enums.AlertLevel;
import com.brasens.dtos.enums.AssetState;
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
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="AssetSummaryDTO")
public class AssetSummaryDTO {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "asset_key", unique = true)
    private String key;
    @Column(name = "rpm", nullable = true)
    private double rpm;

    @Column(name = "location")
    private String location;
    @Column(name = "base")
    boolean isRigidBase;
    @Column(name = "type")
    private String type; //Pump, Motor, Fan
    private int power;
    private int pasNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "last_communication", insertable = false, updatable = false)
    private ZonedDateTime lastCommunication;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", insertable = false, updatable = false)
    private ZonedDateTime added;
    @Column(name = "alert_level")
    @Enumerated(EnumType.STRING)
    @Type( type = "alertlevel")
    private AlertLevel level;

    private String locationTreeName;
    private String organizationName;
}