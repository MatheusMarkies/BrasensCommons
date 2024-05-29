package com.brasens.response;

public class AlertResponse {
    private int id;
    private String value;
    private String current;
    private String target;

    public AlertResponse() {
    }

    public AlertResponse(int id, String value, String current, String target) {
        this.id = id;
        this.value = value;
        this.current = current;
        this.target = target;
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
}
