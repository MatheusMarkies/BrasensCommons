package com.brasens.requestbody;

import java.io.Serializable;
import java.util.List;

import com.brasens.dtos.Data;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(as = DataRequest.class)
public class DataRequest implements Serializable{
	
	public String type;
	public String key;
	public List<Double> rms;
	public double temperature;
    
	public Data getData() {
		Data data = new Data();
		
		data.setRmsX((Math.floor((rms.get(0)/9.81) * 100)/100));
		data.setRmsY((Math.floor((rms.get(1)/9.81) * 100)/100));
		data.setRmsZ((Math.floor((rms.get(2)/9.81) * 100)/100));

		data.setTemperature(temperature);
		
		data.setKey(key);
		
		return data;
	}
	
	@Override
	public String toString() {
		return "DataRequest [type=" + type + ", key=" + key + ", rms=" + rms + ", temperature=" + temperature + "]";
	}
}
