package com.brasens.dtos;

import java.time.ZonedDateTime;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name = "vibration_package")
@TypeDef(
	    name = "list-array",
	    typeClass = ListArrayType.class
)

public class VibrationPackage{
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", nullable = false, unique = true)
	private UUID id;

    @Column(name = "asset_key")
    public String key;

    @Column(name = "data_array", columnDefinition = "double precision[]")
    @Type(type = "list-array")
    public List<java.lang.Double> dataPackage;
    
    @Column(name = "start_sample")
    int start;
    @Column(name = "end_sample")
    int end;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "added", nullable = false)
    private ZonedDateTime added = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());
    
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "id_asset", nullable = false)
	@JsonIgnore
	private Asset asset;
    
	@Override
	public String toString() {
		return "VibrationPackage [key=" + key + ", dataPackage=" + dataPackage + ", start=" + start
				+ ", end=" + end + "]";
	}

    public VibrationPackage() {
    }

    public VibrationPackage(UUID id, String key, List<Double> dataPackage, int start, int end, ZonedDateTime added, Asset asset) {
        this.id = id;
        this.key = key;
        this.dataPackage = dataPackage;
        this.start = start;
        this.end = end;
        this.added = added;
        this.asset = asset;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Double> getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(List<Double> dataPackage) {
        this.dataPackage = dataPackage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ZonedDateTime getAdded() {
        return added;
    }

    public void setAdded(ZonedDateTime added) {
        this.added = added;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
