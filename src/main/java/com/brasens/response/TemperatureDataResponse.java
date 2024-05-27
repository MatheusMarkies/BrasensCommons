package com.brasens.response;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TemperatureDataResponse {
	private List<Double> temperature = new ArrayList<>();

	private  List<ZonedDateTime> added = new ArrayList<>();

	public TemperatureDataResponse() {
	}

	public TemperatureDataResponse(List<Double> temperature, List<ZonedDateTime> added) {
		this.temperature = temperature;
		this.added = added;
	}

	public List<Double> getTemperature() {
		return temperature;
	}

	public void setTemperature(List<Double> temperature) {
		this.temperature = temperature;
	}

	public List<ZonedDateTime> getAdded() {
		return added;
	}

	public void setAdded(List<ZonedDateTime> added) {
		this.added = added;
	}
}
