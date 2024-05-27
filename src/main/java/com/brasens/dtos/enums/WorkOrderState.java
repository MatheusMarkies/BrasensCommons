package com.brasens.dtos.enums;

public enum WorkOrderState {
    COMPLETED("Completo"),
    PLANNING("Planejado"),
    PROGRESS("Em Progresso"),
    OPENING("Aberto");

    private final String legend;

    WorkOrderState(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }

    public static WorkOrderState getWorkOrderState(String period) {
        switch (period) {
            case "Completo":
                return WorkOrderState.COMPLETED;
            case "Planejado":
                return WorkOrderState.PLANNING;
            case "Em Progresso":
                return WorkOrderState.PROGRESS;
            case "Aberto":
                return WorkOrderState.OPENING;
            default:
                throw new IllegalArgumentException("WorkOrderState desconhecido: " + period);
        }
    }
}
