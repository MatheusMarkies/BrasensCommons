package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="FFT_Statistical_Values")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FFTStatisticalValues {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @OneToMany(targetEntity = Vector.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "peaks_id")
    @JsonIgnore
    private List<Vector> peaks = new ArrayList<>();

    @OneToMany(targetEntity = Vector.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "harmonics_id")
    @JsonIgnore
    private List<Vector> harmonics = new ArrayList<>();

    @OneToMany(targetEntity = Vector.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    @JsonIgnore
    private List<Vector> orders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "natural_frequency_id", referencedColumnName = "id")
    @JsonIgnore
    private Vector naturalFrequency;

    double rpm;
    double rms;

    double mean;
    double mode;
    double median;

    @Column(name = "standard_deviation")
    double standardDeviation;
    double variance;

    @Column(name = "zscores_array", columnDefinition = "double precision[]")
    @Type(type = "list-array")
    public List<Double> zScores;

    @Column(name = "peak_to_peak")
    double peakToPeak;

    double skewness;
    double kurtosis;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", insertable = false, updatable = false)
    private ZonedDateTime added = ZonedDateTime.now().withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fft_id")
    @JsonIgnore
    private FFT fft;
}