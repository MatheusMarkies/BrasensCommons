package com.brasens.dtos;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.brasens.dtos.enums.AssetState;
import com.brasens.dtos.enums.DowntimeType;
import com.brasens.dtos.enums.SensorState;
import com.brasens.response.AssetResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;

import static com.brasens.Commons.DEFAULT_TIMEZONE;

@Entity
@Table(name="asset")
@TypeDef(
	    name = "servicetype",
	    typeClass = PostgreSQLEnumType.class
)
@TypeDef(
	    name = "assetstate",
	    typeClass = PostgreSQLEnumType.class
)
@TypeDef(
	    name = "downtimetype",
	    typeClass = PostgreSQLEnumType.class
)
public class Asset {	
		@Id
	    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
	    @GeneratedValue(generator = "UUIDGenerator")
	    @Column(name = "id")
		@JsonIgnore
	    private UUID id;
	    
	    @Column(name = "name",unique = true)
	    private String name;
	    
	    @Column(name = "asset_key",unique = true)
	    private String key;

	    @Column(name = "manufacturer")
	    private String manufacturer;
	    
	    @Column(name = "asset_location")
	    private String asset_location;
	    
	    @Column(name = "downtime")
	    private double downtime;
	    
		@Basic
		@Temporal(TemporalType.DATE)
		@Column(name = "production_date")
		private java.util.Date production_date;

	    @Column(name = "downtime_type")
	    @Enumerated(EnumType.STRING)
		@Type( type = "downtimetype")
	    private DowntimeType downtime_type;
	    
	    @Column(name = "asset_state")
	    @Enumerated(EnumType.STRING)
		@Type( type = "assetstate")
	    private AssetState assetState;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
		@Column(name = "created_at", insertable = false, updatable = false)
	    private ZonedDateTime created_at;
	    
	  	@OneToMany(targetEntity=Events.class, mappedBy="asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	  	@JsonIgnore
	  	private List<Events> events = new ArrayList<>();
	  	
	  	@OneToMany(targetEntity=MachineIntervals.class, mappedBy="asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	  	private List<MachineIntervals> machineIntervals = new ArrayList<>();

	  	@OneToMany(targetEntity=Data.class, mappedBy="asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	  	@JsonIgnore
	  	private List<Data> dataList = new ArrayList<>();
	  	
	  	@OneToMany(targetEntity=VibrationPackage.class, mappedBy="asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	  	@JsonIgnore
	  	private List<VibrationPackage> vibrationPackages = new ArrayList<>();
	  	
	    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	    @JsonIgnore
	  	private FFTModel fFTModel;
	  	
	    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	    @JsonIgnore
	  	private VibrationChart vibrationChart;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
		@Column(name = "data_added", nullable = false)
		private ZonedDateTime dataAdded = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());

		@OneToMany(targetEntity=Alert.class, mappedBy="asset", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
		@JsonIgnore
		private List<Alert> alerts = new ArrayList<>();

		@ManyToOne
		@JoinColumn(name = "organization_id")
		private Organization organization;

		public Asset(String name, ClientModel clientModel) {
			super();
			Date date = new Date(System.currentTimeMillis());
			this.name = name;
			//this.clientModel = new ArrayList<>();
			//this.clientModel.add(clientModel);
			this.created_at = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());
		}

		public Asset(UUID id, String name, String key, Date created_at, ClientModel clientModel) {
			super();
			this.id = id;
			this.name = name;
			this.key = key;
			Date date = new Date(System.currentTimeMillis());
			this.created_at = ZonedDateTime.now(DEFAULT_TIMEZONE.toZoneId());
			//this.clientModel = new ArrayList<>();
			//this.clientModel.add(clientModel);
		}

		public AssetResponse toAssetResponse(double battery, SensorState sensorState) {
			return new AssetResponse(
					name,
					key,
					battery + "",
					manufacturer,
					asset_location,
					production_date,
					downtime,
					downtime_type,
					assetState,
					sensorState,
					created_at
			);
		}

	public Asset(UUID id, String name, String key, String manufacturer, String asset_location, double downtime, Date production_date, DowntimeType downtime_type, AssetState assetState, ZonedDateTime created_at, List<Events> events, List<MachineIntervals> machineIntervals, List<Data> dataList, List<VibrationPackage> vibrationPackages, FFTModel fFTModel, VibrationChart vibrationChart, ZonedDateTime dataAdded, List<Alert> alerts, Organization organization) {
		this.id = id;
		this.name = name;
		this.key = key;
		this.manufacturer = manufacturer;
		this.asset_location = asset_location;
		this.downtime = downtime;
		this.production_date = production_date;
		this.downtime_type = downtime_type;
		this.assetState = assetState;
		this.created_at = created_at;
		this.events = events;
		this.machineIntervals = machineIntervals;
		this.dataList = dataList;
		this.vibrationPackages = vibrationPackages;
		this.fFTModel = fFTModel;
		this.vibrationChart = vibrationChart;
		this.dataAdded = dataAdded;
		this.alerts = alerts;
		this.organization = organization;
	}

	public Asset() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAsset_location() {
		return asset_location;
	}

	public void setAsset_location(String asset_location) {
		this.asset_location = asset_location;
	}

	public double getDowntime() {
		return downtime;
	}

	public void setDowntime(double downtime) {
		this.downtime = downtime;
	}

	public Date getProduction_date() {
		return production_date;
	}

	public void setProduction_date(Date production_date) {
		this.production_date = production_date;
	}

	public DowntimeType getDowntime_type() {
		return downtime_type;
	}

	public void setDowntime_type(DowntimeType downtime_type) {
		this.downtime_type = downtime_type;
	}

	public AssetState getAssetState() {
		return assetState;
	}

	public void setAssetState(AssetState assetState) {
		this.assetState = assetState;
	}

	public ZonedDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(ZonedDateTime created_at) {
		this.created_at = created_at;
	}

	public List<Events> getEvents() {
		return events;
	}

	public void setEvents(List<Events> events) {
		this.events = events;
	}

	public List<MachineIntervals> getMachineIntervals() {
		return machineIntervals;
	}

	public void setMachineIntervals(List<MachineIntervals> machineIntervals) {
		this.machineIntervals = machineIntervals;
	}

	public List<Data> getDataList() {
		return dataList;
	}

	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}

	public List<VibrationPackage> getVibrationPackages() {
		return vibrationPackages;
	}

	public void setVibrationPackages(List<VibrationPackage> vibrationPackages) {
		this.vibrationPackages = vibrationPackages;
	}

	public FFTModel getfFTModel() {
		return fFTModel;
	}

	public void setfFTModel(FFTModel fFTModel) {
		this.fFTModel = fFTModel;
	}

	public VibrationChart getVibrationChart() {
		return vibrationChart;
	}

	public void setVibrationChart(VibrationChart vibrationChart) {
		this.vibrationChart = vibrationChart;
	}

	public ZonedDateTime getDataAdded() {
		return dataAdded;
	}

	public void setDataAdded(ZonedDateTime dataAdded) {
		this.dataAdded = dataAdded;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
