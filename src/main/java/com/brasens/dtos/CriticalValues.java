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

// Novos Enums para a refatoração
enum ValueTag {
    ACCELERATION,
    TEMPERATURE,
    FFT,
    WAVEFORM,
    RPM
}

enum ValueAxis {
    X,
    Y,
    Z,
    VECTOR,
    NONE
}

enum ValueMetric {
    RMS,
    MEAN,
    PEAK_TO_PEAK,
    SKEWNESS,
    KURTOSIS,
    STANDARD_DEVIATION,
    VARIANCE,
    CREST_FACTOR,
    K_FACTOR,
    NONE
}

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

    @Column(name = "value_tag")
    @Enumerated(EnumType.STRING)
    private ValueTag tag;

    @Column(name = "value_axis")
    @Enumerated(EnumType.STRING)
    private ValueAxis axis;

    @Column(name = "value_metric")
    @Enumerated(EnumType.STRING)
    private ValueMetric metric;

    @Column(name = "asset_key")
    private String key;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonIgnore
    private Asset asset;

    public double getTargetValue() {
        switch (this.tag) {
            case ACCELERATION:
                return fetchAccelerationValue(this.axis);
            case TEMPERATURE:
                // TODO: Implementar a busca da temperatura do Asset.
                return 0;
            case RPM:
                return asset.getRpm();
            case FFT:
                return fetchFFTValue(this.axis, this.metric);
            case WAVEFORM:
                return fetchWaveformValue(this.axis, this.metric);
            default:
                throw new IllegalArgumentException("Tag desconhecida: " + this.tag);
        }
    }

    private double fetchAccelerationValue(ValueAxis axis) {
        switch (axis) {
            case X:
                // TODO: Implementar a busca do RMS de aceleração X do Asset.
                return 0;
            case Y:
                // TODO: Implementar a busca do RMS de aceleração Y do Asset.
                return 0;
            case Z:
                // TODO: Implementar a busca do RMS de aceleração Z do Asset.
                return 0;
            default:
                throw new IllegalArgumentException("Eixo desconhecido: " + axis);
        }
    }

    private double fetchFFTValue(ValueAxis axis, ValueMetric metric) {
        switch (axis) {
            case X:
                return fetchFFTMetric(asset.getFftAcceleration_X().getStatisticalValues(), metric);
            case Y:
                return fetchFFTMetric(asset.getFftAcceleration_Y().getStatisticalValues(), metric);
            case Z:
                return fetchFFTMetric(asset.getFftAcceleration_Z().getStatisticalValues(), metric);
            default:
                throw new IllegalArgumentException("Eixo desconhecido: " + axis);
        }
    }

    private double fetchWaveformValue(ValueAxis axis, ValueMetric metric) {
        switch (axis) {
            case X:
                return fetchWaveformMetric(asset.getSensorReading_X().getStatisticalValues(), metric);
            case Y:
                return fetchWaveformMetric(asset.getSensorReading_Y().getStatisticalValues(), metric);
            case Z:
                return fetchWaveformMetric(asset.getSensorReading_Z().getStatisticalValues(), metric);
            default:
                throw new IllegalArgumentException("Eixo desconhecido: " + axis);
        }
    }

    private double fetchFFTMetric(FFTStatisticalValues fftValues, ValueMetric metric) {
        switch (metric) {
            case RMS:
                return fftValues.getRms();
            case MEAN:
                return fftValues.getMean();
            case STANDARD_DEVIATION:
                return fftValues.getStandardDeviation();
            case VARIANCE:
                return fftValues.getVariance();
            case PEAK_TO_PEAK:
                return fftValues.getPeakToPeak();
            case SKEWNESS:
                return fftValues.getSkewness();
            case KURTOSIS:
                return fftValues.getKurtosis();
            default:
                throw new IllegalArgumentException("Métrica desconhecida: " + metric);
        }
    }

    private double fetchWaveformMetric(VibrationSensorReadingStatisticalValues waveformValues, ValueMetric metric) {
        switch (metric) {
            case CREST_FACTOR:
                return waveformValues.getCrestFactor();
            case K_FACTOR:
                return waveformValues.getKFactor();
            case RMS:
                return waveformValues.getRms();
            case MEAN:
                return waveformValues.getMean();
            case STANDARD_DEVIATION:
                return waveformValues.getStandardDeviation();
            case VARIANCE:
                return waveformValues.getVariance();
            case PEAK_TO_PEAK:
                return waveformValues.getPeakToPeak();
            case SKEWNESS:
                return waveformValues.getSkewness();
            case KURTOSIS:
                return waveformValues.getKurtosis();
            default:
                throw new IllegalArgumentException("Métrica desconhecida: " + metric);
        }
    }
}