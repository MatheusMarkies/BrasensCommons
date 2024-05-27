package com.brasens.math.downtime;

import java.time.*;
import java.util.Comparator;
import java.util.List;

import com.brasens.dtos.Asset;
import com.brasens.dtos.MachineIntervals;
import com.brasens.dtos.enums.AssetState;
import com.brasens.dtos.enums.DataPeriod;
import com.brasens.response.AnalyzedData;

public class DowntimeCalculator {

    public static double calculateTotalIdleTime(Asset asset) {
        AnalyzedData analyzedData = AssetStatusAnalyzer.processData(asset.getMachineIntervals(), DataPeriod.MONTHLY);
        return analyzedData.getDowntime();
    }
    public static double calculateTotalIdleTime(Asset asset, DataPeriod dataPeriod) {
        AnalyzedData analyzedData = AssetStatusAnalyzer.processData(asset.getMachineIntervals(), dataPeriod);
        return analyzedData.getDowntime();
    }
    public static double calculateByMachineInterval(Asset asset, DataPeriod dataPeriod) {
        double downtime = 0;
        ZonedDateTime reference = ZonedDateTime.now().minusMonths(1);
        boolean getter = false;
        ZonedDateTime A = ZonedDateTime.now().minusMonths(1);

        Comparator<MachineIntervals> comparator = Comparator.comparing(MachineIntervals::getAdded);
        List<MachineIntervals> list = asset.getMachineIntervals();
        list.sort(comparator);

        for(MachineIntervals interval : list)
            System.out.println(interval.getAdded());

        for(MachineIntervals interval : list) {
            if (interval.getAdded().isAfter(reference))
                if (!getter && interval.getAssetState() == AssetState.IDLE) {
                    A = interval.getAdded();
                    getter = true;
                } else if(interval.getAssetState() == AssetState.WORKING){
                    downtime += Math.abs(Duration.between(A, interval.getAdded()).toMinutes()/60.0);
                    A = interval.getAdded();
                    getter = false;
                }
        }
        return downtime;
    }
}
