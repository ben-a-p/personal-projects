package com.techelevator.model.weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAnySetter;

@Component
public class RestfulWeatherDao implements WeatherDao {
	
	private static class DarkSkyDataPoint {
		public String icon;
		public Double temperatureHigh;
		public Double temperatureLow;
	}
	private static class DarkSkyDataBlock {
		public List <DarkSkyDataPoint> data;
	}
	private static class DarkSkyDataForecast {
		public DarkSkyDataBlock daily;
	}
	
	private static final String BASE_URL = "https://api.darksky.net/forecast/57e194b30a9127223bf1d661e3d7d12a/";
	
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Weather> getWeatherByLongAndLat(double lat, double lon) {
		String latitude = Double.toString(lat);
		String longitude = Double.toString(lon);
		String url = BASE_URL + latitude + "," + longitude;
		
		DarkSkyDataForecast forecast;
		
		try {
			forecast = restTemplate.getForObject(url, DarkSkyDataForecast.class);
		}
		catch (HttpClientErrorException e) {
			forecast = new DarkSkyDataForecast();
		}
		
		List<Weather> results = new ArrayList<>();
		for(DarkSkyDataPoint dp : forecast.daily.data) {
			Weather w = new Weather();
			w.setHigh(dp.temperatureHigh);
			w.setLow(dp.temperatureLow);
			w.setForecast(dp.icon);
			results.add(w);
		}
		
		return results;
	}

	
}
