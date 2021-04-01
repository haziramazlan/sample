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

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;
import com.app.service.covid.CovidService;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidController {

	private final static String GET_LATEST_COVID_FROM_DB = "/covid/get/latest";

	private final static String GET_COVID = "/covid/get";

	private final static String GET_COVID_DESC = "/covid/get/desc";

	private final static String ADD_COVID = "/covid/add";

	private final static String DELETE_COVID = "/covid/delete";

	private final static String GET_HELLO_API = "/covid/hello";

	private final static String GET_LOG_API = "/covid/logging";
	
	private final static String PUT_API = "/covid/put";
	
	private final static String POST_API = "/covid/post";
	
	private final static String DELETE_COVID_SOAPUI = "/covid/delete/soap";
	
	private final static String FIND_DUPLICATE_DELETE_COVID = "/covid/delete/duplicate";

	@Autowired
	private CovidService covidService;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_LATEST_COVID_FROM_DB)
	String getLatest() throws Exception {
		log.info("getLatest() started");
		String returnString = null;

		try {
			returnString = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" getLatest() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_LATEST_COVID_FROM_DB, e.getMessage());
		}
		return returnString;
	}

	@GetMapping(GET_COVID_DESC)
	List<CovidCasesDesc> findAllDesc() throws Exception {
		log.info("findAll() started");
		List<CovidCasesDesc> covidCasesdescs = null;
		try {
			covidCasesdescs = covidService.getCovidDesc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID_DESC + "  return = {}" + covidCasesdescs);
		return covidCasesdescs;
	}

	@GetMapping(GET_COVID)
	List<CovidCasesArea> findAll() throws Exception {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}

	@GetMapping(GET_HELLO_API)
	String getHello() throws Exception {
		log.info("getHello() started");

		return "Hello API";
	}

	@GetMapping(GET_LOG_API)
	String getLogging(@RequestParam String aNumberOnly) throws Exception {
		log.info("getLogging() started, requestParamvalue={}", aNumberOnly);

		if (aNumberOnly != null) {
			Integer.parseInt(aNumberOnly);
		}
		return "you have input =>" + aNumberOnly;
	}

	@GetMapping(ADD_COVID)
	CovidCasesDesc addCovid(@RequestParam(required = true) String desc) throws Exception {
		log.info("addCovid() started={}", desc);

		return covidService.addCovid(desc);
	}

	@DeleteMapping(DELETE_COVID)
	List<CovidCasesArea> deleteCovid(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovid() started id={}", id);

		return covidService.deleteCovid(id);
	}
	
	@PutMapping(PUT_API)
	CovidCasesDesc putCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws RuntimeException {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);
		log.info("putCovid() ends, covidCasesDescSaved={}", covidCasesDesc);
		
		return covidService.putCovid(covidCasesDesc);
	}
	
	@PostMapping(POST_API)
	CovidCasesDesc postCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws Exception {
		log.info("postCovid() started, covidCasesDesc={}", covidCasesDesc);
		
		return covidService.postCovid(covidCasesDesc);
	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI)
	int deleteCovidSoap(@RequestParam(required = true) String desc) throws Exception {
		log.info("deleteCovidSoap() started desc={}", desc);
		log.info("deleteCovidSoap() ended");
		return covidService.deleteCovidSoap(desc);
	}
	
	@DeleteMapping(FIND_DUPLICATE_DELETE_COVID)
	List<String> findDuplicateNdelete() throws Exception {
		log.info("findDuplicateNdelete() started");
		log.info("findDuplicateNdelete() ended");
		return covidService.findDuplicateNdelete();
	}
}
