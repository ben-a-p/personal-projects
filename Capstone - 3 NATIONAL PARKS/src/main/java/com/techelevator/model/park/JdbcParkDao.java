package com.techelevator.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

	JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT * FROM park;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while (results.next()) {
			allParks.add(mapRowToPark(results));
		}

		return allParks;
	}

	@Override
	public List<Park> getParksByVotes() {
		List<Park> allParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT park.*, COUNT(survey_result.*) FROM park "
				+ "JOIN survey_result ON park.parkCode = survey_result.parkCode GROUP BY park.parkCode "
				+ "ORDER BY COUNT (survey_result.*) DESC, park.parkName;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while (results.next()) {
			allParks.add(mapRowToParkWithVotes(results));
		}
		return allParks;
	}

	private Park mapRowToParkWithVotes(SqlRowSet row) {
		Park park = new Park();

		park.setParkCode(row.getString("parkCode"));
		park.setParkName(row.getString("parkName"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevation(row.getInt("elevationInFeet"));
		park.setMilesOfTrail(row.getDouble("milesOfTrail"));
		park.setNumberOfCampsites(row.getInt("numberOfCampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearFounded"));
		park.setAnnualVisitorCount(row.getInt("annualVisitorCount"));
		park.setInspirationalQuote(row.getString("inspirationalQuote"));
		park.setQuoteSource(row.getString("inspirationalQuoteSource"));
		park.setParkDescription(row.getString("parkDescription"));
		park.setEntryFee(row.getInt("entryFee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberOfAnimalSpecies"));
		park.setNumberOfVotes(row.getInt("count"));

		return park;
	}

	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkCode(row.getString("parkCode"));
		park.setParkName(row.getString("parkName"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevation(row.getInt("elevationInFeet"));
		park.setMilesOfTrail(row.getDouble("milesOfTrail"));
		park.setNumberOfCampsites(row.getInt("numberOfCampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearFounded"));
		park.setAnnualVisitorCount(row.getInt("annualVisitorCount"));
		park.setInspirationalQuote(row.getString("inspirationalQuote"));
		park.setQuoteSource(row.getString("inspirationalQuoteSource"));
		park.setParkDescription(row.getString("parkDescription"));
		park.setEntryFee(row.getInt("entryFee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberOfAnimalSpecies"));
		park.setLatitude(row.getDouble("latitude"));
		park.setLongitude(row.getDouble("longitude"));

		return park;
	}

	@Override
	public Park getParkByParkCode(String code) {
		Park selectedPark = new Park();
		String sqlSelectPark = "SELECT * FROM park WHERE parkCode = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectPark, code);
		if (results.next()) {
			selectedPark = mapRowToPark(results);
		}
		return selectedPark;
	}
	


}
