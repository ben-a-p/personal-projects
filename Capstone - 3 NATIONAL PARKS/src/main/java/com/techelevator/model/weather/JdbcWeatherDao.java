package com.techelevator.model.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


public class JdbcWeatherDao implements WeatherDao {

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Weather> getWeatherByParkCode(String code) {
		List<Weather> selectedWeather = new ArrayList<>();
		String sqlSelectWeather = "SELECT * FROM weather WHERE parkCode = ? ORDER BY fiveDayForecastValue;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectWeather, code);
		while(results.next()) {
			selectedWeather.add(mapRowToWeather(results));
		}
		return selectedWeather;
	}
	
	private Weather mapRowToWeather(SqlRowSet row) {
		Weather weather = new Weather();
		weather.setParkCode(row.getString("parkCode"));
		weather.setFiveDayForecastValue(row.getInt("fiveDayForecastValue"));
		weather.setLow(row.getInt("low"));
		weather.setHigh(row.getInt("high"));
		weather.setForecast(row.getString("forecast"));
		return weather;
	}

}
