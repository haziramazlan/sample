package com.app.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CovidDefaultConstant {
	
	private CovidDefaultConstant() {
		log.info("Static Class not for instantiation");
	}
	
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String COVID_APP = " COVID-APP";
	
}
