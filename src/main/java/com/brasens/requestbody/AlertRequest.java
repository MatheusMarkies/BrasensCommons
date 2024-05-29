package com.brasens.requestbody;

import com.brasens.dtos.Asset;
import com.brasens.dtos.enums.AlertLevel;
import com.brasens.dtos.enums.AlertTarget;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

public class AlertRequest implements Serializable {

    private double value;
    private AlertTarget target;
    private AlertLevel level;
    private String tags;
    @JsonProperty("key")
    private String assetKey;

    public AlertRequest() {
    }

    public AlertRequest(double value, AlertTarget target, AlertLevel level, String tags, String assetKey) {
        this.value = value;
        this.target = target;
        this.level = level;
        this.tags = tags;
        this.assetKey = assetKey;
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

    public String getAssetKey() {
        return assetKey;
    }

    public void setAssetKey(String assetKey) {
        this.assetKey = assetKey;
    }
}
