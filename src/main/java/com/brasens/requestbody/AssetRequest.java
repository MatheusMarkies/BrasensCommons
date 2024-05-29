package com.brasens.requestbody;

import java.time.ZonedDateTime;
import java.util.Date;

import com.brasens.dtos.Asset;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = AssetRequest.class)
public class AssetRequest {

	    private String name;
	    private String key;
	    
	    @JsonProperty("manufacturer")
	    private String manufacturer;
	    @JsonProperty("asset_location")
	    private String asset_location;
	    
	    @JsonFormat(pattern="yyyy-MM-dd")
	    @JsonProperty("production_date")
		private Date productionDate;

		@Override
		public String toString() {
			return "SensorRequest [name=" + name + ", key=" + key + "]";
		}
	
		public Asset requestToAsset() {
			Asset asset = new Asset();
			asset.setName(name);
			asset.setKey(key);
			asset.setAsset_location(asset_location);
			asset.setManufacturer(manufacturer);
			asset.setProduction_date(productionDate);
			asset.setCreated_at(ZonedDateTime.now());
			return asset;
		}

	public AssetRequest() {
	}

	public AssetRequest(String name, String key, String manufacturer, String asset_location, Date productionDate) {
		this.name = name;
		this.key = key;
		this.manufacturer = manufacturer;
		this.asset_location = asset_location;
		this.productionDate = productionDate;
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

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
}
