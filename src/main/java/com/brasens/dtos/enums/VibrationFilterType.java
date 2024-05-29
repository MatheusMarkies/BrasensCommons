package com.brasens.dtos.enums;

public enum VibrationFilterType {
    HIGHPASS("Passa Alta"),
    LOWPASS("Passa Baixa"),
    NONE("Nenhum");

    private final String legend;

    public String getLegend() {
        return legend;
    }

    VibrationFilterType(String legend) {
        this.legend = legend;
    }

    public static VibrationFilterType getDataPeriod(String period) {
        switch (period) {
            case "Passa Alta":
                return VibrationFilterType.HIGHPASS;
            case "Passa Baixa":
                return VibrationFilterType.LOWPASS;
            case "Nenhum":
                return VibrationFilterType.NONE;
            default:
                throw new IllegalArgumentException("Filtro desconhecido: " + period);
        }
    }
}