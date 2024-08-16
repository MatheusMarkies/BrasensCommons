package com.brasens.dtos.enums;

public enum AlertState {
    NORMAl("Normal"),
    ALERT("Em Alerta"),
    CRITICAL("Critico");

    private final String legend;

    AlertState(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }

    public static AlertState getAlertState(String level) {
        switch (level) {
            case "Normal":
                return AlertState.NORMAl;
            case "Em Alerta":
                return AlertState.ALERT;
            case "Critico":
                return AlertState.CRITICAL;
            default:
                throw new IllegalArgumentException("Nivel desconhecido: " + level);
        }
    }
}
