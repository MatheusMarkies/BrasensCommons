package com.brasens.math.statistic;

import java.util.List;

public class Statistic {
    public static double Average(List<Double> data) {
        double sum = 0;
        for (double d : data)
            sum += d / data.size();
        return sum;
    }

    public static double StandardDeviation(List<Double> data, double average) {
        double sum = 0;
        for (double d : data)
            sum += (d - average) * (d - average);
        return Math.sqrt(sum/(data.size()-1));
    }
}
