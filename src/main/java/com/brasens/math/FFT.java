package com.brasens.math;

import org.jtransforms.fft.DoubleFFT_1D;

public class FFT {

    public static int SENSOR_RANGE = 25800;

    public static double[] normalize(Double[] valuesArray) {
        double[] normalizedData = new double[valuesArray.length];
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        for (int i = 0; i < valuesArray.length; i++) {
            if (valuesArray[i] < minValue) {
                minValue = valuesArray[i];
            }
            if (valuesArray[i] > maxValue) {
                maxValue = valuesArray[i];
            }
        }

        double factor = maxValue - minValue;
        for (int i = 0; i < valuesArray.length; i++) {
            normalizedData[i] = (valuesArray[i] - minValue) / factor;
        }

        return normalizedData;
    }

    public static double[] normalize(double[] valuesArray) {
        double[] normalizedData = new double[valuesArray.length];
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        for (int i = 0; i < valuesArray.length; i++) {
            if (valuesArray[i] < minValue) {
                minValue = valuesArray[i];
            }
            if (valuesArray[i] > maxValue) {
                maxValue = valuesArray[i];
            }
        }

        double factor = maxValue - minValue;
        for (int i = 0; i < valuesArray.length; i++) {
            normalizedData[i] = (valuesArray[i] - minValue) / factor;
        }

        return normalizedData;
    }

    public static Vector2D[] fft(double[] valuesArray, int windowSize) {
        int n = nearestLowerPowerOfTwo(valuesArray.length);
        double[] reindexedValues = reindexValues(valuesArray, n);
        double[] normalizedData = normalize(reindexedValues);

        applyWindow(normalizedData);

        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        fft.realForward(normalizedData);

        double[] fftValues = new double[n / 2];
        double[] freq = new double[n / 2];

        for (int j = 0; j < n / 2; j++) {
            double re = normalizedData[2 * j];
            double im = normalizedData[2 * j + 1];
            fftValues[j] = Math.sqrt(re * re + im * im);
            freq[j] = (double) j / ((double) n /2);//SENSOR_RANGE
        }

        Vector2D[] fftResult = new Vector2D[n / 2];
        for (int i = 0; i < n / 2; i++) {
            fftResult[i] = new Vector2D(freq[i], fftValues[i]);
        }

        return fftResult;
    }

    public static double[] reindexValues(double[] values, int indexs){
        double[] data = new double[indexs];
        for(int i =0; i<indexs;i++){
            data[i] = values[i];
        }
        return data;
    }

    public static double[][] fft3D(double[] xData, double[] yData, double[] zData, int windowSize) {
        // Normalize the data
        xData = normalize(xData);
        yData = normalize(yData);
        zData = normalize(zData);

        // Create an array to hold the results
        double[][] result = new double[3][];

        // Perform FFT on each axis
        DoubleFFT_1D fft = new DoubleFFT_1D(windowSize);

        // FFT on x-axis
        double[] xFFTData = new double[2 * windowSize];
        System.arraycopy(xData, 0, xFFTData, 0, windowSize);
        fft.realForward(xFFTData);
        result[0] = new double[windowSize / 2 + 1];
        result[0][0] = Math.abs(xFFTData[0]) / (double) windowSize;
        for (int i = 1; i <= windowSize / 2; i++) {
            double re = xFFTData[2 * i - 1];
            double im = xFFTData[2 * i];
            result[0][i] = Math.sqrt(re * re + im * im) / (double) windowSize * 2;
        }

        // FFT on y-axis
        double[] yFFTData = new double[2 * windowSize];
        System.arraycopy(yData, 0, yFFTData, 0, windowSize);
        fft.realForward(yFFTData);
        result[1] = new double[windowSize / 2 + 1];
        result[1][0] = Math.abs(yFFTData[0]) / (double) windowSize;
        for (int i = 1; i <= windowSize / 2; i++) {
            double re = yFFTData[2 * i - 1];
            double im = yFFTData[2 * i];
            result[1][i] = Math.sqrt(re * re + im * im) / (double) windowSize * 2;
        }

        // FFT on z-axis
        double[] zFFTData = new double[2 * windowSize];
        System.arraycopy(zData, 0, zFFTData, 0, windowSize);
        fft.realForward(zFFTData);
        result[2] = new double[windowSize / 2 + 1];
        result[2][0] = Math.abs(zFFTData[0]) / (double) windowSize;
        for (int i = 1; i <= windowSize / 2; i++) {
            double re = zFFTData[2 * i - 1];
            double im = zFFTData[2 * i];
            result[2][i] = Math.sqrt(re * re + im * im) / (double) windowSize * 2;
        }

        return result;
    }

    private static void applyWindow(Double[] valuesArray) {
        // Apply a Hamming window to the data
        int n = valuesArray.length;
        for (int i = 0; i < n; i++) {
            valuesArray[i] *= 0.54 - 0.46 * Math.cos(2 * Math.PI * i / (n - 1));
        }
    }

    private static void applyWindow(double[] valuesArray) {
        // Aplicar uma janela de Hamming aos dados
        int n = valuesArray.length;
        for (int i = 0; i < n; i++) {
            valuesArray[i] *= 0.54 - 0.46 * Math.cos(2 * Math.PI * i / (n - 1));
        }
    }

    public static int nearestLowerPowerOfTwo(int n) {
        int powerOfTwo = 1;
        while (powerOfTwo <= n) {
            powerOfTwo <<= 1;
        }
        return powerOfTwo >> 1;
    }
}
