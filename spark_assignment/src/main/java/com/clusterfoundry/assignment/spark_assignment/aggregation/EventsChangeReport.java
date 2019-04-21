package com.clusterfoundry.assignment.spark_assignment.aggregation;

public class EventsChangeReport {
	private int monthYear;
	private long numberOfEvents;
	private double changePercentage;
	
	public int getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(int monthYear) {
		this.monthYear = monthYear;
	}
	public long getNumberOfEvents() {
		return numberOfEvents;
	}
	public void setNumberOfEvents(long numberOfEvents) {
		this.numberOfEvents = numberOfEvents;
	}
	public double getChangePercentage() {
		return changePercentage;
	}
	public void setChangePercentage(double changePercentage) {
		this.changePercentage = changePercentage;
	}
	@Override
	public String toString() {
		return "EventsChangeReport [monthYear=" + monthYear + ", numberOfEvents=" + numberOfEvents
				+ ", changePercentage=" + changePercentage + "]";
	}
}
