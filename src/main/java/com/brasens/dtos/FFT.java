package com.brasens.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="FFT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FFT {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @OneToMany(targetEntity = Vector.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "values_id")
    @JsonIgnore
    private List<Vector> values = new ArrayList<>();

    @Column(name = "asset_key", unique = true)
    private String key;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", insertable = false, updatable = false)
    private ZonedDateTime added = ZonedDateTime.now().withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());

    @OneToOne(mappedBy = "FFT", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private FFTStatisticalValues statisticalValues;

    @OneToOne(mappedBy = "FFT", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Envelope envelope;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    @JsonIgnore
    private Asset asset;
}
