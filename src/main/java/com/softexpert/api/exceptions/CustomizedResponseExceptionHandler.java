package com.softexpert.api.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.softexpert.api.exceptions.errors.HomeBadRequestException;
import com.softexpert.api.exceptions.errors.HomeNotFoundException;

@RestControllerAdvice
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(HomeNotFoundException.class)
	public final ResponseEntity<ErrorComposer> handleHomeNotFound(HomeNotFoundException exception, WebRequest request){
		ErrorComposer error = new ErrorComposer(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HomeBadRequestException.class)
	public final ResponseEntity<ErrorComposer> handleHomeBadRequest(HomeBadRequestException exception, WebRequest request){
		ErrorComposer error = new ErrorComposer(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
