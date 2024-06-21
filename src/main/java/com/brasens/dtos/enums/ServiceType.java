package com.brasens.dtos.enums;

public enum ServiceType {
	OUTSOURCED("Terceirizado"),
    OWN("Proprio");
    private final String legend;
    ServiceType(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }

    public static ServiceType getServiceType(String type) {
        switch (type) {
            case "Terceirizado":
                return ServiceType.OUTSOURCED;
            case "Proprio":
                return ServiceType.OWN;
            default:
                throw new IllegalArgumentException("Service Type desconhecido: " + type);
        }
    }
    }
