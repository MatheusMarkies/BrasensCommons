package com.brasens.dtos.enums;

public enum AlertTarget {
    RMS("Rms"),
    FFT("FFT"),
    TIME_SIGNAL("Sinal Temporal"),
    TEMPERATURE("Temperatura");
    private final String legend;

    AlertTarget(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }

    public static AlertTarget getAlertTarget(String target) {
        switch (target) {
            case "Rms":
                return AlertTarget.RMS;
            case "FFT":
                return AlertTarget.FFT;
            case "Sinal Temporal":
                return AlertTarget.TIME_SIGNAL;
            case "Temperatura":
                return AlertTarget.TEMPERATURE;
            default:
                throw new IllegalArgumentException("Target desconhecido: " + target);
        }
    }
}
