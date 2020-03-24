package com.techelevator.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.eNum.PhysicalActivity;
import com.techelevator.model.eNum.State;
import com.techelevator.model.park.ParkDao;
import com.techelevator.model.survey.Survey;
import com.techelevator.model.survey.SurveyDao;
import com.techelevator.model.weather.WeatherDao;



@Controller
public class NationalParksController {

	@Autowired
	private ParkDao parkDao;
	
	@Autowired
	private WeatherDao weatherDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	@GetMapping(path = 	{"/","/home"})
	public String homePage(ModelMap modelMap) {
		modelMap.put("parks",parkDao.getAllParks());
		return "home";
	}
	
	@GetMapping(path = "/parkDetails")
	public String parkDetails(@RequestParam String code, @RequestParam Double lat, @RequestParam Double lon, ModelMap modelMap, HttpSession session) {
		modelMap.put("park",parkDao.getParkByParkCode(code));
		modelMap.put("dailyWeather", weatherDao.getWeatherByLongAndLat(lat, lon));
		
		String temp = (String)session.getAttribute("temperature");
		if (temp == null) {
			temp = "farenheit";
			session.setAttribute("temperature", temp);
		} 
		
		modelMap.put("temperature", temp);
		return "parkDetails";
	}
	
	@PostMapping(path = "/parkDetails")
	public String parkDetailTempChange(@RequestParam String code, @RequestParam String temperature, 
										ModelMap modelMap, HttpSession session) {
		
		session.setAttribute("temperature", temperature);
		return "redirect:/parkDetails?code=" + code;
	}
	
	@GetMapping(path = "/parkSurvey")
	public String parkSurveyForm(ModelMap modelMap) {
		modelMap.put("parks", parkDao.getAllParks());
		modelMap.put("states", State.values());
		modelMap.put("activityLevel", PhysicalActivity.values());
		
		if (modelMap.containsAttribute("surveyData") == false) {
			Survey empty = new Survey();
			modelMap.put("surveyData", empty);
		}
		
		
		return "parkSurvey";
	}
	
	@PostMapping(path = "/parkSurvey")
	public String postParkSurvey(@Valid @ModelAttribute Survey survey,
								BindingResult result, RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			ra.addFlashAttribute("surveyData", survey);
			ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "surveyData", result);
			
		return "redirect:/parkSurvey";
		}
		surveyDao.saveSurvey(survey);
		
		return "redirect:/surveyResults";
	}
	
	@GetMapping(path= "/surveyResults")
	public String displaySurveyResults(ModelMap modelMap) {
		modelMap.put("parks", parkDao.getParksByVotes());
		
		
		return "surveyResults";
	}
}
