package com.brasens.response;

public class  WorkordersStatsIndicator {
	private int completed;
	private int planning;
	private int progress;
	private int opening;

	public WorkordersStatsIndicator(int completed, int planning, int progress, int opening) {
		this.completed = completed;
		this.planning = planning;
		this.progress = progress;
		this.opening = opening;
	}

	private double completedPercent = 0.0, planningPercent = 0.0, progressPercent = 0.0, openingPercent = 0.0;

	public WorkordersStatsIndicator() {
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getPlanning() {
		return planning;
	}

	public void setPlanning(int planning) {
		this.planning = planning;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getOpening() {
		return opening;
	}

	public void setOpening(int opening) {
		this.opening = opening;
	}

	public double getCompletedPercent() {
		return completedPercent;
	}

	public void setCompletedPercent(double completedPercent) {
		this.completedPercent = completedPercent;
	}

	public double getPlanningPercent() {
		return planningPercent;
	}

	public void setPlanningPercent(double planningPercent) {
		this.planningPercent = planningPercent;
	}

	public double getProgressPercent() {
		return progressPercent;
	}

	public void setProgressPercent(double progressPercent) {
		this.progressPercent = progressPercent;
	}

	public double getOpeningPercent() {
		return openingPercent;
	}

	public void setOpeningPercent(double openingPercent) {
		this.openingPercent = openingPercent;
	}
}
