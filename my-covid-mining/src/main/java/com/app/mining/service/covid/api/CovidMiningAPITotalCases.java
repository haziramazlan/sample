package com.app.mining.service.covid.api;

import com.app.error.GeneralException;

public interface CovidMiningAPITotalCases {

	String doMining() throws GeneralException;

	String getTotalfromDB();

}
