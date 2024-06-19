package com.brasens.math.statistic;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DescriptiveStatistics {

    public static List<Double> createNormalDistribution(double mean, double standardDeviation, int size) {
        NormalDistribution normalDistribution = new NormalDistribution(mean, standardDeviation);
        List<Double> normalData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            normalData.add(normalDistribution.sample());
        }
        return normalData;
    }

    public static double Mean(List<Double> data) {
        double sum = 0;
        for (double d : data)
            sum += d;
        return sum / data.size();
    }

    public static double Median(List<Double> data) {
        List<Double> clonedList = new ArrayList<>(data);
        Collections.sort(clonedList);
        int size = clonedList.size();
        if (size % 2 != 0)
            return clonedList.get(size / 2);
        else {
            return (clonedList.get((size / 2) - 1) + clonedList.get(size / 2)) / 2.0;
        }
    }

    public static double Mode(List<Double> data) {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (Double d : data) {
            frequencyMap.put(d, frequencyMap.getOrDefault(d, 0) + 1);
        }

        double mode = data.get(0);
        int maxCount = 0;
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }

    public static double RMS(List<Double> data) {
        double sum = 0;
        for (double d : data)
            sum += d * d;
        return Math.sqrt(sum / data.size());
    }

    public static double Peak(List<Double> data) {
        return Collections.max(data);
    }

    public static double PeakToPeak(List<Double> data) {
        return Collections.max(data) - Collections.min(data);
    }

    public static double Variance(List<Double> data, double mean) {
        double sum = 0;
        for (double d : data)
            sum += (d - mean) * (d - mean);
        return sum / (data.size() - 1);
    }

    public static double StandardDeviation(List<Double> data, double mean) {
        return Math.sqrt(Variance(data, mean));
    }

    public static double Kurtosis(List<Double> distribution, double distributionMean, double distributionStd) {
        double sum = 0;
        for (double d : distribution)
            sum += Math.pow((d - distributionMean), 4);
        return sum / ((distribution.size() - 1) * Math.pow(distributionStd, 4));
    }

    public static double Skewness(List<Double> distribution, double distributionMean, double distributionStd) {
        double sum = 0;
        for (double d : distribution)
            sum += Math.pow((d - distributionMean), 3);
        return sum / ((distribution.size() - 1) * Math.pow(distributionStd, 3));
    }
}
