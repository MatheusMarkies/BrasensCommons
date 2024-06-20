package com.brasens.math;

import com.brasens.dtos.Vector;
import com.github.psambit9791.jdsp.filter.Butterworth;
import com.github.psambit9791.jdsp.transform.FastFourier;
import com.github.psambit9791.jdsp.transform.Hilbert;
import com.github.psambit9791.jdsp.transform._Fourier;
import org.jtransforms.fft.DoubleFFT_1D;

import java.util.ArrayList;
import java.util.List;

public class FFT {

    public static int SENSOR_RANGE = 25800;

    public static double[] normalize(Double[] valuesArray) {
        double[] normalizedData = new double[valuesArray.length];
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        for (double v : valuesArray) {
            if (v < minValue) {
                minValue = v;
            }
            if (v > maxValue) {
                maxValue = v;
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

        for (double v : valuesArray) {
            if (v < minValue) {
                minValue = v;
            }
            if (v > maxValue) {
                maxValue = v;
            }
        }

        double factor = maxValue - minValue;
        for (int i = 0; i < valuesArray.length; i++) {
            normalizedData[i] = (valuesArray[i] - minValue) / factor;
        }

        return normalizedData;
    }

    public static Vector2D[] fft(double[] valuesArray, int windowSize, int SAMPLE_RATE) {
        int n = windowSize;
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
            freq[j] = (double) j * (SAMPLE_RATE / n);
        }

        Vector2D[] fftResult = new Vector2D[n / 2];
        for (int i = 0; i < n / 2; i++) {
            fftResult[i] = new Vector2D(freq[i], fftValues[i]);
        }

        return fftResult;
    }

    public static double[] reindexValues(double[] values, int indexs) {
        double[] data = new double[indexs];
        System.arraycopy(values, 0, data, 0, indexs);
        return data;
    }

    public static double[][] fft3D(double[] xData, double[] yData, double[] zData, int windowSize) {
        xData = normalize(xData);
        yData = normalize(yData);
        zData = normalize(zData);

        double[][] result = new double[3][];

        DoubleFFT_1D fft = new DoubleFFT_1D(windowSize);

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

    public static List<Vector> calculatePSD(double[] fftValues, int n, int sampleRate) {
        List<Vector>  psd = new ArrayList<>();
        double freq;
        for (int i = 0; i < fftValues.length; i++) {
            double psdValue = (fftValues[i] * fftValues[i]) / (double) (n * sampleRate);
            freq = (double) i * (sampleRate / n);
            psd.add(new Vector2D(freq, psdValue).toVector());
        }
        return psd;
    }

    public static List<Vector> hilbertTransform(double[] valuesArray) {
        int n = valuesArray.length;
        double[] transformed = new double[n];

        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        double[] fftData = new double[2 * n];
        System.arraycopy(valuesArray, 0, fftData, 0, n);
        fft.realForwardFull(fftData);

        for (int i = 1; i < n / 2; i++) {
            fftData[2 * i] *= 2;
            fftData[2 * i + 1] *= 2;
        }
        for (int i = n / 2; i < n; i++) {
            fftData[2 * i] = 0;
            fftData[2 * i + 1] = 0;
        }

        fft.complexInverse(fftData, true);

        for (int i = 0; i < n; i++) {
            transformed[i] = fftData[2 * i + 1];
        }

        List<Vector> hilbertResult = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hilbertResult.add(new Vector2D((double) i / n, transformed[i]).toVector());
        }

        return hilbertResult;
    }

    public static double[] getPSDDoubleArray(double[] fftValues, int n, int sampleRate) {
        double[] psd = new double[fftValues.length];
        for (int i = 0; i < fftValues.length; i++) {
            psd[i] = (fftValues[i] * fftValues[i]) / (double) (n * sampleRate);
        }
        return psd;
    }

    public static double[] getHilbertTransformDoubleArray(double[] valuesArray) {
        int n = valuesArray.length;
        double[] transformed = new double[n];

        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        double[] fftData = new double[2 * n];
        System.arraycopy(valuesArray, 0, fftData, 0, n);
        fft.realForwardFull(fftData);

        for (int i = 1; i < n / 2; i++) {
            fftData[2 * i] *= 2;
            fftData[2 * i + 1] *= 2;
        }
        for (int i = n / 2; i < n; i++) {
            fftData[2 * i] = 0;
            fftData[2 * i + 1] = 0;
        }

        fft.complexInverse(fftData, true);

        for (int i = 0; i < n; i++) {
            transformed[i] = fftData[2 * i + 1];
        }

        return transformed;
    }

    public static List<Vector> envelope(double[] fft, int SENSOR_DATARATE, int SAMPLES) {
        Hilbert h = new Hilbert(fft);
        h.transform();
        double[][] analytical_signal = h.getOutput();
        double[] envelope = h.getAmplitudeEnvelope();

        double freqResolution = SAMPLES / SENSOR_DATARATE;

        List<Vector> vecs = new ArrayList<>();
        for(int i =0; i< envelope.length;i++){
            vecs.add(new Vector2D(freqResolution * i, envelope[i]).toVector());
        }

        return vecs;
    }

    public static List<Vector> jdspFFT(double[] signal, int SENSOR_DATARATE, int SAMPLES) {
        _Fourier ft = new FastFourier(signal);
        ft.transform();
        boolean onlyPositive = true;

        double freqResolution = SAMPLES/SENSOR_DATARATE;
        double[] out = ft.getMagnitude(onlyPositive);

        List<Vector> vecs = new ArrayList<>();
        for(int i =0; i< out.length;i++){
            vecs.add(new Vector2D(freqResolution * i, out[i]).toVector());
        }

        return vecs;
    }

    private static void applyWindow(double[] valuesArray) {
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
