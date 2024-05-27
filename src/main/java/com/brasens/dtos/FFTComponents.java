package com.brasens.dtos;

import com.brasens.math.Vector2D;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fftcomponents")
public class FFTComponents {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    @JsonIgnore
    private UUID id;

    @OneToMany(targetEntity = Vector.class,cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fftcomponents_id") // This will be the foreign key column in the Vector table
    @JsonIgnore
    private List<Vector> peak = new ArrayList<>();

    @OneToMany(targetEntity = Vector.class,cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fftcomponents_id")
    @JsonIgnore
    private List<Vector> valley = new ArrayList<>();

    @OneToOne(targetEntity = Vector.class,cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fftcomponents_id")
    @JsonIgnore
    private Vector fn;

    @OneToMany(targetEntity = Vector.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fftcomponents_id")
    @JsonIgnore
    private List<Vector> harmonics = new ArrayList<>();

    @ManyToOne(targetEntity = FFTModel.class, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "id_cluster", nullable = false)
    @JsonIgnore
    private FFTModel fftModel;

    public FFTComponents(List<Vector2D> peak, List<Vector2D> valley, Vector2D fnPeak, List<Vector2D> harmonics) {
        this.peak.clear();
        this.valley.clear();
        this.harmonics.clear();

        for (Vector2D v : peak)
            this.peak.add(v.toVector());

        for (Vector2D v : valley)
            this.valley.add(v.toVector());

        for (Vector2D v : harmonics)
            this.harmonics.add(v.toVector());

        this.fn = fnPeak.toVector();
    }

    public FFTComponents() {
    }

    public FFTComponents(UUID id, List<Vector> peak, List<Vector> valley, Vector fn, List<Vector> harmonics, FFTModel fftModel) {
        this.id = id;
        this.peak = peak;
        this.valley = valley;
        this.fn = fn;
        this.harmonics = harmonics;
        this.fftModel = fftModel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Vector> getPeak() {
        return peak;
    }

    public void setPeak(List<Vector> peak) {
        this.peak = peak;
    }

    public List<Vector> getValley() {
        return valley;
    }

    public void setValley(List<Vector> valley) {
        this.valley = valley;
    }

    public Vector getFn() {
        return fn;
    }

    public void setFn(Vector fn) {
        this.fn = fn;
    }

    public List<Vector> getHarmonics() {
        return harmonics;
    }

    public void setHarmonics(List<Vector> harmonics) {
        this.harmonics = harmonics;
    }

    public FFTModel getFftModel() {
        return fftModel;
    }

    public void setFftModel(FFTModel fftModel) {
        this.fftModel = fftModel;
    }
}