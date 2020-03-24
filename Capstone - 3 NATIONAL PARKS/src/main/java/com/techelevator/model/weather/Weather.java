package com.techelevator.model.weather;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.*;

public class Weather {

	private String parkCode;
	private int fiveDayForecastValue;
	private Double low;
	private Double high;
	private Double lowCelcius;
	private Double highCelcius;

	private String forecast;
	
	
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public Double getLowCelcius() {
		lowCelcius = (low - 32) * 5/9;
		
		return lowCelcius;
	}
	
	public Double getHighCelcius() {
		highCelcius = (high - 32) * 5/9;
		
		return highCelcius;
	}
	
	
	public String getImageUrl() {
	String result = CaseUtils.toCamelCase(forecast, false, ' ');
	
	result = "/img/weather/" + result + ".png";
	
	return result;
	}
	
	public String getForecastMessage() {
		String result = "";
		if (forecast.equals("snow")) {
			result = ("It is predicted to snow, pack some snow shoes. ");
		} else if (forecast.equals("rain")) {
			result = ("It is predicted to rain, pack rain gear and water proof shoes. ");
		} else if (forecast.equals("thunderstorms")) {
			result = ("It is predicted that there will be thunderstorms in the area, please seek shelter and avoid hiking on exposed ridges. ");
		} else if (forecast.equals("sunny")) {
			result = ("It is predicted to be sunny, be prepared with some sunblock. ");
		}
		return result;
	}
	
	public String getTempMessage() {
		String result = "";
		if (high > 75) {
			result += "Bring an extra gallon of water. ";
		}
		
		if (low < 20) {
			result += "Temperatures will reach freezing please be careful and wear appropriate gear to reduce risk when exposed to frigid temperatures. ";
		}
		
		if (low < high - 20) {
			result += "Temperature is expected to fluctuate be prepared with breathable layers.";
		}
		
		
		
		return result;
	}
	
}
