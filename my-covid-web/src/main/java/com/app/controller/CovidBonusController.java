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

import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {
	
	private final static String GET_MY_BONUS = "/covid/get/bonus";
	
	private final static String ADD_COVID_BONUS = "/covid/add/bonus";

	private final static String DELETE_COVID_BONUS = "/covid/delete/bonus";
	
	private final static String PUT_API_BONUS = "/covid/put/bonus";
	
	private final static String POST_API_BONUS = "/covid/post/bonus";
	
	private final static String DELETE_COVID_SOAPUI_BONUS = "/covid/delete/soap/bonus";

	@Autowired
	CovidBonusService covidBonusService;
	
	@GetMapping(GET_MY_BONUS)
	List<CovidCasesBonus> bonus() throws Exception {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			covidCasesBonus = covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new Exception("No bonus yet");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("bonus() exception " + e.getMessage());
			throw new Exception(e);
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_COVID_BONUS)
	CovidCasesBonus addCovidBonus(@RequestParam(required = true) String bonus) throws Exception {
		log.info("addCovidBonus() started={}", bonus);

		return covidBonusService.addCovidBonus(bonus);
	}

	@DeleteMapping(DELETE_COVID_BONUS)
	List<CovidCasesBonus> deleteCovidBonus(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovidBonus() started id={}", id);

		return covidBonusService.deleteCovidBonus(id);
	}
	
	@PutMapping(PUT_API_BONUS)
	CovidCasesBonus putCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws RuntimeException {
		log.info("putCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		log.info("putCovidBonus() ends, covidCasesBonusSaved={}", covidCasesBonus);
		
		return covidBonusService.putCovidBonus(covidCasesBonus);
	}
	
	@PostMapping(POST_API_BONUS)
	CovidCasesBonus postCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {
		log.info("postCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		
		return covidBonusService.postCovidBonus(covidCasesBonus);
	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI_BONUS)
	int deleteCovidSoapBonus(@RequestParam(required = true) String bonus) throws Exception {
		log.info("deleteCovidSoapBonus() started bonus={}", bonus);
		log.info("deleteCovidSoapBonus() ended");
		return covidBonusService.deleteCovidSoapBonus(bonus);
	}

}
