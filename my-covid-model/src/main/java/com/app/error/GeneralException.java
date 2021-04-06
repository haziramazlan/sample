package com.app.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GeneralException(String message) {
		super(com.app.util.CovidDefaultConstant.COVID_APP + " GeneralException exception--> " + message);
		log.error(" GeneralException = {}" + message);
	}
}
