package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.error.GeneralException;
import com.app.model.CovidCasesArea;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyCovidController {

	private static final String GET_MY_LAST_5_COVID = "/covid/get5/my";
	
	private static final String GET_MY_LAST_5_COVID_PARAM = "/covid/get5/withsize";

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_MY_LAST_5_COVID)
	public List<CovidCasesArea> getLast5Records() throws GeneralException {
		log.info("getLast5Records() started");

		log.info(
				"getLast5Records() ends. It supposes to return last 5 records from listLast5Records(). (CovidCasesRepository)");
		return covidMiningAPITotalCases.getLast5RecordsMY();
	}
	
	@GetMapping(GET_MY_LAST_5_COVID_PARAM)
	public List<CovidCasesArea> getLast5RecordsWithParam(@RequestParam int size) throws GeneralException {
		log.info("getLast5RecordsWithParam() started size ={}", size);

		log.info(
				"getLast5RecordsWithParam() ends. It supposes to return last 5 records from listLast5Records(). (CovidCasesRepository)");
		return covidMiningAPITotalCases.getLast5RecordsMYWithSize(size);
	}
}
