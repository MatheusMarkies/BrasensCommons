package com.brasens.requestbody;

import com.brasens.dtos.Asset;
import com.brasens.dtos.VibrationPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VibrationPackageRequest implements Serializable {
    public String type;
    public String key;
    public List<Double> dataPackage;
    int start;
    int end;
    
	@Override
	public String toString() {
		return "VibrationPackage [type=" + type + ", key=" + key + ", dataPackage=" + dataPackage + ", start=" + start
				+ ", end=" + end + "]";
	}
	
	public VibrationPackage getVibrationPackage(Asset asset) {
		VibrationPackage vibrationPackage = new VibrationPackage();
		
		vibrationPackage.setKey(key);
		
		vibrationPackage.setAsset(asset);

		List<Double> dataPackageG = new ArrayList<>();

		for(Double d : dataPackage)
			dataPackageG.add(Math.floor((d/9.81) * 100)/100);

		vibrationPackage.setDataPackage(dataPackageG);
		vibrationPackage.setStart(start);
		vibrationPackage.setEnd(end);
		
		return vibrationPackage;
	}

	public VibrationPackageRequest() {
	}

	public VibrationPackageRequest(String type, String key, List<Double> dataPackage, int start, int end) {
		this.type = type;
		this.key = key;
		this.dataPackage = dataPackage;
		this.start = start;
		this.end = end;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
