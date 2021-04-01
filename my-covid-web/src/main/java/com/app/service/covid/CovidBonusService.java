package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

public interface CovidBonusService {
	
	List<CovidCasesBonus> bonus() throws Exception;

	CovidCasesBonus addCovidBonus(String desc);

	List<CovidCasesBonus> deleteCovidBonus(long id);

	CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus);

	CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus);

	int deleteCovidSoapBonus(String desc);

}
