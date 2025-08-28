package com.brasens.dtos;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.brasens.dtos.enums.AlertLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="Asset")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(
		name = "alertlevel",
		typeClass = PostgreSQLEnumType.class
)
public class Asset {
	@Id
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGenerator")
	@Column(name = "id")
	@JsonIgnore
	private UUID id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "asset_key", unique = true)
	private String key;

	//Add Asset Information

	@Column(name = "rpm", nullable = true)
	private double rpm;

	@Column(name = "location")
	private String location;

	//Add Asset Information
	@Column(name = "base")
	boolean isRigidBase;
	@Column(name = "type")
	private String type; //Pump, Motor, Fan

	int power;
	int pasNumber = 0;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
	@Column(name = "last_communication", nullable = false)
	private ZonedDateTime lastCommunication = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
	@Column(name = "added", insertable = false, updatable = false)
	private ZonedDateTime added = ZonedDateTime.now().withZoneSameInstant(DEFAULT_TIMEZONE.toZoneId());

	@OneToMany(targetEntity = Events.class, mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Events> events = new ArrayList<>();

	@OneToMany(targetEntity = MachineIntervals.class, mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<MachineIntervals> machineIntervals = new ArrayList<>();

	@OneToMany(targetEntity = Data.class, mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Data> dataList = new ArrayList<>();

	@OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<FFT> ffts = new ArrayList<>();

	@OneToOne(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	@JsonIgnore
	private Downtime downtime;

	@OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<VibrationSensorReading> sensorReading = new ArrayList<>();

	@Column(name = "alert_level")
	@Enumerated(EnumType.STRING)
	@Type( type = "alertlevel")
	private AlertLevel level;

	@OneToMany(targetEntity = Alert.class, mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Alert> alerts = new ArrayList<>();

	@OneToMany(targetEntity = CriticalValues.class, mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<CriticalValues> criticalValues = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "asset_tree_id")
	private AssetTree assetTree;

	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false)
	private LocationTree locationTree;

	@OneToMany(targetEntity = ValuesHistoryForTrend.class, mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ValuesHistoryForTrend> valuesHistoryForTrends = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "bearing_id", nullable = true)
	private Bearings bearing;

	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
}