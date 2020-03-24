package com.techelevator.model.weather;

public class WeatherForecast {
	
	public String summary;
	public double temperatureHigh;
	public double temperatureLow;
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public double getTemperatureHigh() {
		return temperatureHigh;
	}
	public void setTemperatureHigh(double temperatureHigh) {
		this.temperatureHigh = temperatureHigh;
	}
	public double getTemperatureLow() {
		return temperatureLow;
	}
	public void setTemperatureLow(double temperatureLow) {
		this.temperatureLow = temperatureLow;
	}

}
