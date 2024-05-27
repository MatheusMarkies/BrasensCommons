package com.brasens.response;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class VibrationDataResponse {
	private List<Double> X = new ArrayList<>();
	private List<Double> Y = new ArrayList<>();
	private List<Double> Z = new ArrayList<>();

	private  List<ZonedDateTime> added = new ArrayList<>();

	public VibrationDataResponse() {
	}

	public VibrationDataResponse(List<Double> x, List<Double> y, List<Double> z, List<ZonedDateTime> added) {
		X = x;
		Y = y;
		Z = z;
		this.added = added;
	}

	public List<Double> getX() {
		return X;
	}

	public void setX(List<Double> x) {
		X = x;
	}

	public List<Double> getY() {
		return Y;
	}

	public void setY(List<Double> y) {
		Y = y;
	}

	public List<Double> getZ() {
		return Z;
	}

	public void setZ(List<Double> z) {
		Z = z;
	}

	public List<ZonedDateTime> getAdded() {
		return added;
	}

	public void setAdded(List<ZonedDateTime> added) {
		this.added = added;
	}
}
