package com.brasens.dtos.enums;

public enum AlertLevel {
    NORMAL("Normal"),
    ALERT("Em Alerta"),
    CRITICAL("Critico");

    private final String legend;

    AlertLevel(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }

    public static AlertLevel getAlertLevel(String level) {
        switch (level) {
            case "Normal":
                return AlertLevel.NORMAL;
            case "Em Alerta":
                return AlertLevel.ALERT;
            case "Critico":
                return AlertLevel.CRITICAL;
            default:
                throw new IllegalArgumentException("Nivel desconhecido: " + level);
        }
    }
}
