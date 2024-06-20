package com.brasens.math;

import com.brasens.dtos.Vector;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class TrendCalculator {

    public interface StatisticalValues {
        ZonedDateTime getAdded();

        double getMean();
        double getMedian();
        double getMode();
        double getStandardDeviation();
        double getVariance();
        double getRms();
        double getRpm();
        double getPeakToPeak();
        double getSkewness();
        double getKurtosis();
        List<Double> getZScores();
    }

    @Getter @Setter @AllArgsConstructor
    public static class TrendParameter {
        private double value;
        private double percent;
    }

    @Getter @Setter @AllArgsConstructor
    public static class Trend {
        private final TrendParameter meanTrend;
        private final TrendParameter medianTrend;
        private final TrendParameter modeTrend;
        private final TrendParameter standardDeviationTrend;
        private final TrendParameter varianceTrend;
        private final TrendParameter rmsTrend;
        private final TrendParameter rpmTrend;
        private final TrendParameter peakToPeakTrend;
        private final TrendParameter skewnessTrend;
        private final TrendParameter kurtosisTrend;
    }

    private static <T extends StatisticalValues> Trend getTrendParameters(List<T> values) {
        if (values.size() < 2) {
            throw new IllegalArgumentException("Insufficient data to calculate trend.");
        }

        values.sort(Comparator.comparing(T::getAdded));

        T latest = values.get(values.size() - 1);
        T previous = values.get(0);

        return new Trend(
                calculateTrend(latest.getMean(), previous.getMean()),
                calculateTrend(latest.getMedian(), previous.getMedian()),
                calculateTrend(latest.getMode(), previous.getMode()),
                calculateTrend(latest.getStandardDeviation(), previous.getStandardDeviation()),
                calculateTrend(latest.getVariance(), previous.getVariance()),
                calculateTrend(latest.getRms(), previous.getRms()),
                calculateTrend(latest.getRpm(), previous.getRpm()),
                calculateTrend(latest.getPeakToPeak(), previous.getPeakToPeak()),
                calculateTrend(latest.getSkewness(), previous.getSkewness()),
                calculateTrend(latest.getKurtosis(), previous.getKurtosis())
        );
    }

    private static TrendParameter calculateTrend(double latest, double previous) {
        return new TrendParameter((latest - previous), ((latest/previous) * 100));
    }

    public static class FFTTrend {
        public interface FFTArrayValues {
            ZonedDateTime getAdded();

            List<Vector> getPeaks();

            List<Vector> getHarmonics();

            List<Vector> getOrders();

            Vector getNaturalFrequency();
        }

        @Getter @Setter @AllArgsConstructor
        public static class Trend {
            private final List<TrendParameter> peaks;
            private final List<TrendParameter> harmonics;
            private final List<TrendParameter> orders;

            private final TrendParameter naturalFrequency;
        }

        private static <T extends FFTArrayValues> Trend getTrendParameters(List<T> values) {
            if (values.size() < 2) {
                throw new IllegalArgumentException("Insufficient data to calculate trend.");
            }

            values.sort(Comparator.comparing(T::getAdded));

            T latest = values.get(values.size() - 1);
            T previous = values.get(0);

            return new Trend(
                    calculateTrend(latest.getPeaks(), previous.getPeaks()),
                    calculateTrend(latest.getHarmonics(), previous.getHarmonics()),
                    calculateTrend(latest.getOrders(), previous.getOrders()),
                    TrendCalculator.calculateTrend(latest.getNaturalFrequency().getY(), previous.getNaturalFrequency().getY())
            );
        }

        private static List<TrendParameter> calculateTrend(List<Vector> latest, List<Vector> previous) {
            List<TrendParameter> parameters = new ArrayList<>();

            for(int i = 0;i < previous.size();i++){
                if(latest.size() > i){
                    parameters.add(new TrendParameter((latest.get(i).getY() - previous.get(i).getY()), ((latest.get(i).getY() / previous.get(i).getY()) * 100)));
                }
            }

            return parameters;
        }
    }
}