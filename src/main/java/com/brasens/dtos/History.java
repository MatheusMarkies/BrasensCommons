package com.brasens.dtos;

import com.brasens.dtos.Asset;
import com.brasens.dtos.Downtime;
import com.brasens.dtos.FFT;
import com.brasens.dtos.VibrationSensorReading;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="History")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @Column(name = "asset_key", unique = true)
    private String key;

    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Downtime downtime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    @JsonIgnore
    private Asset asset;
}
