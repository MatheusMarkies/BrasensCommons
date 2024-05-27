package com.brasens.dtos.enums;

public enum AlertLevel {
    NORMAl("Normal"),
    HIGH("Alto"),
    CRITICAL("Critico");

    private final String legend;

    AlertLevel(String legend) {
        this.legend = legend;
    }

    public static AlertLevel getAlertLevel(String level) {
        switch (level) {
            case "Normal":
                return AlertLevel.NORMAl;
            case "Alto":
                return AlertLevel.HIGH;
            case "Critico":
                return AlertLevel.CRITICAL;
            default:
                throw new IllegalArgumentException("Nivel desconhecido: " + level);
        }
    }
}
