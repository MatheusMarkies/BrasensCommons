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
}
