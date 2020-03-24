package com.techelevator.model.weather;

import java.util.List;

public interface WeatherDao {
	
	public List<Weather> getWeatherByLongAndLat(double lat, double lon);

}
