package com.techelevator.model.survey;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao {
	
JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public void saveSurvey(Survey survey) {
		String sqlSurveyPost = "INSERT INTO survey_result (parkCode, emailAddress, state, activityLevel) VALUES (?, ?, ?, ?);";
		jdbcTemplate.update(sqlSurveyPost, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		
	}

}
