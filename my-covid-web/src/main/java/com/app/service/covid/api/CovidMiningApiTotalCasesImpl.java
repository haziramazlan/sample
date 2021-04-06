package com.app.service.covid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.mapper.CovidCasesAreaMapper;
import com.app.repository.covid.CovidCasesRepository;
import com.app.entity.CovidCasesAreaEntity;
import com.app.error.GeneralException;
import com.app.model.CovidCasesArea;
import com.app.model.api.Covid19ApiModel;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CovidMiningApiTotalCasesImpl implements CovidMiningAPITotalCases {

	@Autowired
	CovidCasesRepository covidCasesRepository;

	private int getCasesDifferent(List<Covid19ApiModel> covid19ApiModels) {
		Covid19ApiModel first = covid19ApiModels.get(0);
		Covid19ApiModel last = covid19ApiModels.get(1);

		log.info("first cases ={}, last cases= {} ", first.getCases(), last.getCases());

		return last.getCases() - first.getCases();

	}

	@Override
	public List<CovidCasesArea> getLast5RecordsMY() throws GeneralException {
		List<CovidCasesAreaEntity> casesEntities = covidCasesRepository.listLast5RecordsHQL();

		CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();

		List<CovidCasesArea> casesPojos = new ArrayList<>();
		for (CovidCasesAreaEntity covidCasesAreaEntity : casesEntities) {
			CovidCasesArea covidCasesArea = mapper.asResource(covidCasesAreaEntity);
			casesPojos.add(covidCasesArea);
		}

		log.info("getLast5RecordsMY ends.  cases = {} ", casesPojos);
		return casesPojos;
	}
	
	@Override
	public List<CovidCasesArea> getLast5RecordsMYWithSize(int size) throws GeneralException {		
		
		CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();
		
		Pageable page = PageRequest.of(0, size);
		List<CovidCasesAreaEntity> list =
		covidCasesRepository.listLast5RecordsHQLWithSize(page);

		List<CovidCasesArea> casesPojos = new ArrayList<>();
		for (CovidCasesAreaEntity covidCasesAreaEntity : list) {
			CovidCasesArea covidCasesArea = mapper.asResource(covidCasesAreaEntity);
			casesPojos.add(covidCasesArea);
		}

		if (casesPojos.isEmpty()) {
			throw new GeneralException("query return nothing!");
		}
		
		log.info("getLast5RecordsMYWithSize ends.");
		return casesPojos;
	}

	@Override
	@Cacheable(value="getTotalfromDB")
	public String getTotalfromDB() throws GeneralException {
		log.info("getTotalfromDB starts. ");
		List<CovidCasesAreaEntity> casesEntities = covidCasesRepository.listLast2Records();
		log.info("getTotalfromDB casesEntities size ={} ", casesEntities.size());
		
		int totalCases = 0;
		String date = "";
		if (!casesEntities.isEmpty()) {
			List<Covid19ApiModel> covidApiModels = new ArrayList<>();

			CovidCasesAreaEntity covidCasesAreaEntity = casesEntities.get(1);
			log.info("getTotalfromDB Last covidCasesAreaEntity date={}, cases={}", covidCasesAreaEntity.getDate(),
					covidCasesAreaEntity.getCases());

			Covid19ApiModel covid19ApiModel = new Covid19ApiModel();
			covid19ApiModel.setCases(covidCasesAreaEntity.getCases());
			covidApiModels.add(covid19ApiModel);

			covidCasesAreaEntity = casesEntities.get(0);
			log.info("getTotalfromDB covidCasesAreaEntity date={}, cases={}", covidCasesAreaEntity.getDate(),
					covidCasesAreaEntity.getCases());
			date = covidCasesAreaEntity.getDate().toString();
			covid19ApiModel = new Covid19ApiModel();
			covid19ApiModel.setCases(covidCasesAreaEntity.getCases());
			covidApiModels.add(covid19ApiModel);
			totalCases = getCasesDifferent(covidApiModels);
		}

		
		
		log.info("getTotalfromDB ends.  totalCases = {} date={}", totalCases,date);
		return "Total Cases " + totalCases + " (" + date + ")";
	}
}
