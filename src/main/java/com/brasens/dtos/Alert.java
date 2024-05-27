package com.brasens.dtos;

import com.brasens.dtos.enums.AlertLevel;
import com.brasens.dtos.enums.AlertTarget;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="alert")
@TypeDef(
        name = "alerttarget",
        typeClass = PostgreSQLEnumType.class
)
@TypeDef(
        name = "alertlevel",
        typeClass = PostgreSQLEnumType.class
)

public class Alert {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "alert_value")
    private double value;

    @Column(name = "alert_target")
    @Enumerated(EnumType.STRING)
    @Type( type = "alerttarget")
    private AlertTarget target;

    @Column(name = "alert_level")
    @Enumerated(EnumType.STRING)
    @Type( type = "alertlevel")
    private AlertLevel level;

    @Column(name = "alert_tags")
    private String tags;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "id_asset", nullable = false)
    @JsonIgnore
    private Asset asset;

    public Alert() {

    }

    public Alert(UUID id, double value, AlertTarget target, AlertLevel level, String tags, Asset asset) {
        this.id = id;
        this.value = value;
        this.target = target;
        this.level = level;
        this.tags = tags;
        this.asset = asset;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public AlertTarget getTarget() {
        return target;
    }

    public void setTarget(AlertTarget target) {
        this.target = target;
    }

    public AlertLevel getLevel() {
        return level;
    }

    public void setLevel(AlertLevel level) {
        this.level = level;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
