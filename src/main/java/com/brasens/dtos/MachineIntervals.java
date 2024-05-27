package com.brasens.dtos;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brasens.dtos.enums.AssetState;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;

@Entity
@Table(name = "machineintervals")
@TypeDef(
	    name = "assetstate",
	    typeClass = PostgreSQLEnumType.class
)
public class MachineIntervals{

	@Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
	@JsonIgnore
    private UUID id;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", nullable = false)
	private ZonedDateTime added;
    
    @Column(name = "asset_state")
    @Enumerated(EnumType.STRING)
	@Type( type = "assetstate")
    private AssetState assetState;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "id_asset", nullable = false)
	@JsonIgnore
	private Asset asset;

    public MachineIntervals(ZonedDateTime added, AssetState assetState){
        this.added = added;
        this.assetState = assetState;
    }

    @Override
    public String toString() {
        return "MachineIntervals{" +
                "added=" + added +
                ", assetStatus=" + assetState +
                '}';
    }

    public MachineIntervals() {
    }

    public MachineIntervals(UUID id, ZonedDateTime added, AssetState assetState, Asset asset) {
        this.id = id;
        this.added = added;
        this.assetState = assetState;
        this.asset = asset;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getAdded() {
        return added;
    }

    public void setAdded(ZonedDateTime added) {
        this.added = added;
    }

    public AssetState getAssetState() {
        return assetState;
    }

    public void setAssetState(AssetState assetState) {
        this.assetState = assetState;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
