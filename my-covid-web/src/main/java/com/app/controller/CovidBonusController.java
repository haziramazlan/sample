package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.error.ControllerException;
import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {
	
	private static final String GET_MY_BONUS = "/covid/get/bonus";
	
	private static final String ADD_COVID_BONUS = "/covid/add/bonus";

	private static final String DELETE_COVID_BONUS = "/covid/delete/bonus";
	
	private static final String PUT_API_BONUS = "/covid/put/bonus";
	
	private static final String POST_API_BONUS = "/covid/post/bonus";
	
	private static final String DELETE_COVID_SOAPUI_BONUS = "/covid/delete/soap/bonus";

	@Autowired
	CovidBonusService covidBonusService;
	
	@GetMapping(GET_MY_BONUS)
	public List<CovidCasesBonus> bonus() throws ControllerException {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			covidCasesBonus = covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new ControllerException("No bonus yet", null);
			}
		} catch (Exception e) {
			log.error("bonus() exception " + e.getMessage());
			throw new ControllerException(GET_MY_BONUS, e.getMessage());
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_COVID_BONUS)
	public CovidCasesBonus addCovidBonus(@RequestParam(required = true) String bonus) throws ControllerException {
		log.info("addCovidBonus() started={}", bonus);

		return covidBonusService.addCovidBonus(bonus);
	}

	@DeleteMapping(DELETE_COVID_BONUS)
	public int deleteCovidBonus(@RequestParam(required = true) long id) throws ControllerException {
		log.info("deleteCovidBonus() started id={}", id);

		return covidBonusService.deleteCovidBonus(id);
	}
	
	@PutMapping(PUT_API_BONUS)
	public CovidCasesBonus putCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws ControllerException {
		log.info("putCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		log.info("putCovidBonus() ends, covidCasesBonusSaved={}", covidCasesBonus);
		
		return covidBonusService.putCovidBonus(covidCasesBonus);
	}
	
	@PostMapping(POST_API_BONUS)
	public CovidCasesBonus postCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws ControllerException {
		log.info("postCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		
		return covidBonusService.postCovidBonus(covidCasesBonus);
	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI_BONUS)
	public int deleteCovidSoapBonus(@RequestParam(required = true) String bonus) throws ControllerException {
		log.info("deleteCovidSoapBonus() started bonus={}", bonus);
		log.info("deleteCovidSoapBonus() ended");
		return covidBonusService.deleteCovidSoapBonus(bonus);
	}

}
