package com.softexpert.api.exceptions.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HomeNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -927413165414989609L;

	public HomeNotFoundException(String exception) {
		super(exception);
	}
	
}
