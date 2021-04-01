package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesDesc> getCovidDesc();

	CovidCasesDesc addCovid(String desc);
	
	CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc);

	List<CovidCasesArea> deleteCovid(long id);

	CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc);

	int deleteCovidSoap(String desc);

	List<String> findDuplicateNdelete();

}
