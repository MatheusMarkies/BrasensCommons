package com.brasens.dtos;

import java.time.ZonedDateTime;
import java.util.Date;

import java.util.UUID;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.brasens.dtos.enums.Maintenance;
import com.brasens.dtos.enums.PriorityState;
import com.brasens.dtos.enums.WorkOrderState;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;

@Entity
@Table(name="workorder")
@TypeDefs({
@TypeDef(
	    name = "priority",
	    typeClass = PostgreSQLEnumType.class
),
@TypeDef(
	    name = "state",
	    typeClass = PostgreSQLEnumType.class
),
@TypeDef(
	    name = "maintenance",
	    typeClass = PostgreSQLEnumType.class
)
})

public class Workorder {	
		@Id
	    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
	    @GeneratedValue(generator = "UUIDGenerator")
	    @Column(name = "id")
		@JsonIgnore
	    private UUID id;
	    
	    @Column(name = "name",unique = true)
	    private String name;
	    
	    @Column(name = "owner",unique = true)
	    private String owner;
	    
	    @Column(name = "asset_key",unique = true)
	    private String asset;

		@Basic
		@Temporal(TemporalType.DATE)
		@Column(name = "created_date")
		private java.util.Date createdDate;
		
		@Basic
		@Temporal(TemporalType.DATE)
		@Column(name = "conclusion_date")
		private java.util.Date conclusionDate;
	    
		@Enumerated(EnumType.STRING)
		@Column(name = "state")
		@Type( type = "state")
		private WorkOrderState state;
		
		@Enumerated(EnumType.STRING)
		@Column(name = "maintenance")
		@Type( type = "maintenance")
		private Maintenance maintenance;
		
		@Enumerated(EnumType.STRING)
		@Column(name = "priority")
		@Type( type = "priority")
		private PriorityState priority;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
		@Column(name = "created_at", nullable = false)
		private ZonedDateTime created_at;

		@ManyToOne
		@JoinColumn(name = "organization_id")
		@JsonIgnore
		private Organization organization;

		public Workorder(String name, String owner, String asset, Date createdDate, Date conclusionDate,
						 WorkOrderState state, Maintenance maintenance, PriorityState priority) {
			this.name = name;
			this.owner = owner;
			this.asset = asset;
			this.createdDate = createdDate;
			this.conclusionDate = conclusionDate;
			this.state = state;
			this.maintenance = maintenance;
			this.priority = priority;
		}
		
		@Override
		public String toString() {
			return "Workorder [id=" + id + ", name=" + name + ", owner=" + owner + ", asset=" + asset + ", createdDate="
					+ createdDate + ", conclusionDate=" + conclusionDate + ", state=" + state + ", maintenance="
					+ maintenance + ", priority=" + priority + ", created_at=" + created_at + ", clientModel="
					+ ", sensor=" + "]";
		}

	public Workorder() {
	}

	public Workorder(UUID id, String name, String owner, String asset, Date createdDate, Date conclusionDate, WorkOrderState state, Maintenance maintenance, PriorityState priority, ZonedDateTime created_at, Organization organization) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.asset = asset;
		this.createdDate = createdDate;
		this.conclusionDate = conclusionDate;
		this.state = state;
		this.maintenance = maintenance;
		this.priority = priority;
		this.created_at = created_at;
		this.organization = organization;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(Date conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public WorkOrderState getState() {
		return state;
	}

	public void setState(WorkOrderState state) {
		this.state = state;
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

	public PriorityState getPriority() {
		return priority;
	}

	public void setPriority(PriorityState priority) {
		this.priority = priority;
	}

	public ZonedDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(ZonedDateTime created_at) {
		this.created_at = created_at;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
