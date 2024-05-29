package com.brasens.requestbody;

import java.util.Date;
import java.util.Objects;

import com.brasens.dtos.Workorder;
import com.brasens.dtos.enums.Maintenance;
import com.brasens.dtos.enums.PriorityState;
import com.brasens.dtos.enums.WorkOrderState;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = WorkorderRequest.class)
public class WorkorderRequest {

	private String name;
    private String owner;
    private String asset;
    
    @JsonFormat(pattern="yyyy-MM-dd")
	private java.util.Date createdDate;
    @JsonFormat(pattern="yyyy-MM-dd")
	private java.util.Date conclusionDate;

	private WorkOrderState state;
	private Maintenance maintenance;
	private PriorityState priority;
	
    public WorkorderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WorkorderRequest [name=" + name + ", owner=" + owner + ", asset=" + asset + ", createdDate=" + createdDate
				+ ", conclusionDate=" + conclusionDate + ", state=" + state + ", maintenance=" + maintenance
				+ ", priority=" + priority + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(conclusionDate, createdDate, asset, maintenance, name, owner, priority, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkorderRequest other = (WorkorderRequest) obj;
		return Objects.equals(conclusionDate, other.conclusionDate) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(asset, other.asset) && maintenance == other.maintenance
				&& Objects.equals(name, other.name) && Objects.equals(owner, other.owner) && priority == other.priority
				&& state == other.state;
	}

	public WorkorderRequest(String name, String owner, String asset, Date createdDate, Date conclusionDate, WorkOrderState state,
			Maintenance maintenance, PriorityState priority) {
		super();
		this.name = name;
		this.owner = owner;
		this.asset = asset;
		this.createdDate = createdDate;
		this.conclusionDate = conclusionDate;
		this.state = state;
		this.maintenance = maintenance;
		this.priority = priority;
	}

	public Workorder requestToWorkorder() {
        return new Workorder(name, owner, asset, createdDate, conclusionDate, state, maintenance, priority);
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
}
