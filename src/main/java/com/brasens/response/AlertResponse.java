package com.brasens.response;

import com.brasens.dtos.enums.AlertLevel;

public class AlertResponse {
    private int id;
    private String value;
    private String current;
    private String target;
    private String level;

    public AlertResponse() {
    }

    public AlertResponse(int id, String value, String current, String target, String level) {
        this.id = id;
        this.value = value;
        this.current = current;
        this.target = target;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
