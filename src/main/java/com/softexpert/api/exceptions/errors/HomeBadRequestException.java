package com.softexpert.api.exceptions.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HomeBadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7585282680299276260L;

	public HomeBadRequestException(String exception) {
		super(exception);
	}
	
}
