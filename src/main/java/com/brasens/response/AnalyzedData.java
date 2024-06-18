package com.brasens.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AnalyzedData {
    public String[] timeTags;
    public double[] offlineTime;
    public double[] onlineTime;
    public double downtime;
    public double ontime;
}