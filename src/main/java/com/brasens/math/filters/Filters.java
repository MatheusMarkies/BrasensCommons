package com.brasens.math.filters;

import java.util.ArrayList;
import java.util.List;

public class Filters {
    public static class LowPassFilter {
        private final double alpha;

        public LowPassFilter(double alpha) {
            this.alpha = alpha;
        }

        public double[] apply(double[] data) {
            double[] output = new double[data.length];
            double[] input = data.clone();

            output[0] = alpha * input[1];

            for (int i = 1; i < data.length; ++i) {
                double filteredValue = alpha * input[i] + (1 - alpha) * output[i - 1];
                output[i] = filteredValue;
            }

            return output;
        }
        public List<Double> apply(List<Double> data) {
            List<Double> output = new ArrayList<>();

            output.add(alpha * data.get(1));

            for (int i = 1; i < data.size(); ++i) {
                double filteredValue = alpha * data.get(i) + (1 - alpha) * output.get(i - 1);
                output.add(filteredValue);
            }

            return output;
        }
    }

    public static class HighPassFilter {
        private final double alpha;

        public HighPassFilter(double alpha) {
            this.alpha = alpha;
        }

        public List<Double> apply(List<Double> data) {
            List<Double> output = new ArrayList<>();

            output.add(data.get(0));

            for (int i = 1; i < data.size(); ++i) {
                double filteredValue = alpha * output.get(i - 1) + alpha * (data.get(i) - data.get(i-1));
                output.add(filteredValue);
            }

            return output;
        }
    }
}