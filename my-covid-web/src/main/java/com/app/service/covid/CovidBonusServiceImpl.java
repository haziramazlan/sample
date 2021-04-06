package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.GeneralException;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CovidBonusServiceImpl implements CovidBonusService {
	
	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;

	@Override
	public List<CovidCasesBonus> bonus() throws GeneralException {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");
		
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {
			covidCasesBonus = new ArrayList<>();
			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonus.add(model);
				log.info("entity total bonus={}", entity.getDescription());
			}
			log.info(" getCovidBonus() return Size={}", covidCaseBonusEntities.size());
		}
		for (CovidCasesBonus b :covidCasesBonus) {
			log.info("b--->" + b.getDescription());
			}

		log.info("bonus() ends");
		return covidCasesBonus;
	}
	
	@Override
	public CovidCasesBonus addCovidBonus(String bonus) {
		log.info("addCovidBonus started");
		CovidCasesBonusEntity covidCasesBonusEntity = new CovidCasesBonusEntity();

		covidCasesBonusEntity.setDescription(bonus);

		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

		return mapper.asResource(savedEntity);

	 }

	@Override
	public int deleteCovidBonus(long id) {
		log.info("deleteCovidBonus started");
		
		Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

		log.info("Entity found == " + entityOptional.isPresent());

		if (entityOptional.isPresent()) {
			CovidCasesBonusEntity covidCasesBonusEntity = entityOptional.get();
			covidCasesBonusRepository.delete(covidCasesBonusEntity);
			return 1;
		}

		return 0;

	}
	
	@Override
	public CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus) {
		log.info("putCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		CovidCasesBonusEntity covidCasesBonusEntity = mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
		covidCasesBonus = mapper.asResource(savedEntity);
		return covidCasesBonus;
	}
	
	@Override
	public CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus) {
		log.info("postCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		CovidCasesBonusEntity covidCasesBonusEntity = mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
		covidCasesBonus = mapper.asResource(savedEntity);
		return covidCasesBonus;
	}
	
	@Override
	public int deleteCovidSoapBonus(String bonus) {
		log.info("deleteCovidSoapBonus started");
		
		return covidCasesBonusRepository.deleteBonusWithCondition(bonus);

	}

}
