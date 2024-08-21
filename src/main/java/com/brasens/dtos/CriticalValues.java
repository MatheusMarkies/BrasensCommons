package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="Critical_Values")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CriticalValues {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "alert_value")
    private double value;
    @Column(name = "alert_critical_value")
    private double criticalValue;

    @Column(name = "alert_tags")
    private String tags;

    @Column(name = "asset_key")
    private String key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonIgnore
    private Asset asset;

    public double getTargetValue() {
        String[] parts = tags.split(" ");
        String tag = parts[0];
        String axis = parts.length > 1 ? parts[1] : null;
        String metric = parts.length > 2 ? parts[2] : null;

        return fetchValueFromAsset(tag, axis, metric);
    }

    private double fetchValueFromAsset(String tag, String axis, String metric) {
        switch (tag) {
            case "Aceleração":
                return fetchAccelerationValue(axis);
            case "Temperatura":
                return 0;//asset.getTemperature();
            case "RPM":
                return asset.getRpm();
            case "Frequência":
                return 0;//asset.getNaturalFrequency(axis);
            case "FFT":
                return fetchFFTValue(axis, metric);
            case "Forma de Onda":
                return fetchWaveformValue(axis, metric);
            default:
                throw new IllegalArgumentException("Tag desconhecida: " + tag);
        }
    }

    private double fetchAccelerationValue(String axis) {
        switch (axis) {
            case "X":
                return 0;//asset.getRMSAccelerationX();
            case "Y":
                return 0;//asset.getRMSAccelerationY();
            case "Z":
                return 0;//asset.getRMSAccelerationZ();
            default:
                throw new IllegalArgumentException("Eixo desconhecido: " + axis);
        }
    }

    private double fetchFFTValue(String axis, String metric) {
        switch (axis) {
            case "X":
                return fetchFFTMetric(asset.getFftAcceleration_X().getStatisticalValues(), metric);
            case "Y":
                return fetchFFTMetric(asset.getFftAcceleration_Y().getStatisticalValues(), metric);
            case "Z":
                return fetchFFTMetric(asset.getFftAcceleration_Z().getStatisticalValues(), metric);
            default:
                throw new IllegalArgumentException("Eixo desconhecido: " + axis);
        }
    }

    private double fetchWaveformValue(String axis, String metric) {
        switch (axis) {
            case "X":
                return fetchWaveformMetric(asset.getSensorReading_X().getStatisticalValues(), metric);
            case "Y":
                return fetchWaveformMetric(asset.getSensorReading_Y().getStatisticalValues(), metric);
            case "Z":
                return fetchWaveformMetric(asset.getSensorReading_Z().getStatisticalValues(), metric);
            default:
                throw new IllegalArgumentException("Eixo desconhecido: " + axis);
        }
    }

    private double fetchFFTMetric(FFTStatisticalValues fftValues, String metric) {
        switch (metric) {
            case "RMS":
                return fftValues.getRms();
            case "Média":
                return fftValues.getMean();
            case "Padrão":
                return fftValues.getStandardDeviation();
            case "Variância":
                return fftValues.getVariance();
            case "Pico a Pico":
                return fftValues.getPeakToPeak();
            case "Skewness":
                return fftValues.getSkewness();
            case "Curticose":
                return fftValues.getKurtosis();
            default:
                throw new IllegalArgumentException("Métrica desconhecida: " + metric);
        }
    }

    private double fetchWaveformMetric(VibrationSensorReadingStatisticalValues waveformValues, String metric) {
        switch (metric) {
            case "Fator de Crista":
                return waveformValues.getCrestFactor();
            case "Fator K":
                return waveformValues.getKFactor();
            case "RMS":
                return waveformValues.getRms();
            case "Média":
                return waveformValues.getMean();
            case "Padrão":
                return waveformValues.getStandardDeviation();
            case "Variância":
                return waveformValues.getVariance();
            case "Pico a Pico":
                return waveformValues.getPeakToPeak();
            case "Skewness":
                return waveformValues.getSkewness();
            case "Curticose":
                return waveformValues.getKurtosis();
            default:
                throw new IllegalArgumentException("Métrica desconhecida: " + metric);
        }
    }
}
