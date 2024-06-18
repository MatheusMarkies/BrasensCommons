package com.brasens.response;

public class AnalyzedData {
    public String[] timeTags;
    public double[] offlineTime;
    public double[] onlineTime;
    public double downtime;
    public double ontime;

    public AnalyzedData() {
    }

    public AnalyzedData(String[] timeTags, double[] offlineTime, double[] onlineTime, double downtime, double ontime) {
        this.timeTags = timeTags;
        this.offlineTime = offlineTime;
        this.onlineTime = onlineTime;
        this.downtime = downtime;
        this.ontime = ontime;
    }

    public String[] getTimeTags() {
        return timeTags;
    }

    public void setTimeTags(String[] timeTags) {
        this.timeTags = timeTags;
    }

    public double[] getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(double[] offlineTime) {
        this.offlineTime = offlineTime;
    }

    public double[] getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(double[] onlineTime) {
        this.onlineTime = onlineTime;
    }

    public double getDowntime() {
        return downtime;
    }

    public void setDowntime(double downtime) {
        this.downtime = downtime;
    }

    public double getOntime() {
        return ontime;
    }

    public void setOntime(double ontime) {
        this.ontime = ontime;
    }
}