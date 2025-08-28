package com.brasens.dtos;

import com.brasens.dtos.enums.AlertLevel;
import com.brasens.dtos.enums.AssetState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetSummaryDTO {
    private UUID id;
    private String name;
    private String key;
    private double rpm;
    private String location;
    private boolean isRigidBase;
    private String type;
    private int power;
    private int pasNumber;
    private ZonedDateTime lastCommunication;
    private ZonedDateTime added;
    private AlertLevel level;

    // These names must match the constructor parameters
    private String locationTreeName;
    private String organizationName;
    private String bearingName;
    private AssetState assetState;
}